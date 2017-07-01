package it.polimi.ingsw.GC_26_server;

import java.util.ArrayList;

import java.util.List;

import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_controllers.ActionController;
import it.polimi.ingsw.GC_26_controllers.ChoiceController;
import it.polimi.ingsw.GC_26_controllers.EndTurnController;
import it.polimi.ingsw.GC_26_gameLogic.Game;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_serverView.ClientMainServerView;

public class GameInitialiserAndController{
	private Game game;
	private List<ClientMainServerView> clients= new ArrayList<>() ;
	private int numOfPlayer=0 ; //new match are created when a player is in 
	private int nameRepeated=1;
	
	public  GameInitialiserAndController(Cards cards, BonusInterface bonus, TimerValuesInterface times){
		game = new Game( cards,  bonus,  times);
	}
	
	
	public void addClient(ClientMainServerView client){
		clients.add(client);
		changeNameIfNameAlreadyExists(client);
		game.addPlayer(client.getName());
		numOfPlayer++;
	}
	
	public int getNumOfPlayer() {
		return numOfPlayer;
	}
	
	private void changeNameIfNameAlreadyExists(ClientMainServerView view){
		List<Player> players= game.getPlayers();
		for(Player player: players)
			if(player.getName().equals(view.getName())){
				String temp= String.valueOf(nameRepeated);
				view.setName(view.getName().concat(temp));
				nameRepeated++;
			}
				
	}
	
	
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
							if(p.getName()==view.getName()){
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
