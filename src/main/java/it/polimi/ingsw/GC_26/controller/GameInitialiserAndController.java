package it.polimi.ingsw.GC_26.controller;

import java.util.ArrayList;

import java.util.List;

import it.polimi.ingsw.GC_26.json_reader.BonusInterface;
import it.polimi.ingsw.GC_26.json_reader.Cards;
import it.polimi.ingsw.GC_26.json_reader.TimerValuesInterface;
import it.polimi.ingsw.GC_26.model.game.game_logic.Game;
import it.polimi.ingsw.GC_26.model.game.game_logic.GameElements;
import it.polimi.ingsw.GC_26.model.handlers.action_handlers.MainActionHandler;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.view.ClientMainServerView;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that initializes the game and checks if there's already a player whose name is the same to the one who is joining
 * the game
 *
 */
public class GameInitialiserAndController{
	private Game game;
	private List<ClientMainServerView> clients= new ArrayList<>() ;
	private int numOfPlayer=0 ; //new match are created when a player is in 
	private int nameRepeated=1;
	
	/**
	 * Constructor: it creates a new game with the cards, bonuses and timers contained in the parameters
	 * @param cards the cards of the game
	 * @param bonus the bonuses of the game
	 * @param timers the timers of the game
	 */
	public GameInitialiserAndController(Cards cards, BonusInterface bonus, TimerValuesInterface timers){
		game = new Game(cards, bonus, timers);
	}
	
	/**
	 * Method that adds a client to the game
	 * @param client It's the client that has to be added to the game
	 */
	public void addClient(ClientMainServerView client){
		clients.add(client);
		changeNameIfNameAlreadyExists(client);
		game.addPlayer(client.getName());
		numOfPlayer++;
	}
	
	/**
	 * Method that returns the number of the players who have joined this game
	 * @return the number of the players who have joined this game
	 */
	public int getNumOfPlayer() {
		return numOfPlayer;
	}
	
	/**
	 * Method that checks if the name of a player who has already joined the game is equal to the name of the new client who is 
	 * joining the game: if the check is positive, the new client's name is automatically changed to his prevoius name with a number 
	 * appended at the end, which is: 2 if there's already a player with the same name, 3 if there are already two players with same
	 * name etc
	 * @param view
	 */
	private void changeNameIfNameAlreadyExists(ClientMainServerView view){
		List<Player> players= game.getPlayers();
		for(Player player: players)
			if(player.getName().equals(view.getName())){
				String temp= String.valueOf(nameRepeated);
				view.setName(view.getName().concat(temp));
				nameRepeated++;
			}
				
	}
	
	/**
	 * Method that initializes correctly the game
	 */
	public void initialiseGame(){
		game.initialiseGame();
		GameElements gameElements = game.getGameElements();
		List<Player> players= game.getPlayers();
		//views' observers of model
		for(Player player: players){
			for(ClientMainServerView view : clients){
				if(player.getName().equals(view.getName())){ // for unicast messages
					player.registerObserver(view.getMessageView());
					player.getPersonalBoard().registerObserver(view.getCardDescriberView());
					game.registerObserver(view.getCardDescriberView());
					gameElements.getBoard().registerObserver(view.getPositionView());
					gameElements.registerObserver(view.getActionView());
				}
				//for broadcast messages
				player.getWarehouse().registerObserver(view.getPlayerWalletView());
				player.getFamilyMembers().registerObserver(view.getFamilyMembersView());

			}
		}
		//controllers' observers of view
		MainActionHandler handlers = gameElements.getHandlers();  //instead of giving all the model to the controllers we give handlers: 
		for(ClientMainServerView view : clients){
			Player player=null;
			//finds correspondent name
			for(Player p: players){
				if(p.getName().equals(view.getName())){
					player=p;
					break;}
				}
			ActionController actionController= new ActionController(player, handlers);
			ChoiceController choiceController=  new ChoiceController(player, handlers);
			EndTurnController endTurnController = new EndTurnController(player, handlers);
		
			view.getActionInputView().registerObserver(actionController);
			view.getStringInputView().registerObserver(choiceController);
			view.getEndTurnView().registerObserver(endTurnController);
			
			if(player==null){
				throw new IllegalArgumentException();
			}
		}
		
		game.startGame();
	}
	
}
