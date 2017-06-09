package it.polimi.ingsw.GC_26_serverView;

import java.util.ArrayList;

import java.util.List;

import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_controllers.ActionController;
import it.polimi.ingsw.GC_26_controllers.ChoiceController;
import it.polimi.ingsw.GC_26_gameLogic.Game;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;

public class GameInitialiserAndController implements Runnable{
	private Game game;
	private boolean started;
	private List<ClientMainServerView> clients= new ArrayList<>() ;
	private int numOfPlayer ; 
	private Server server;
	
	public  GameInitialiserAndController(Cards cards, BonusInterface bonus, TimerValuesInterface times){
		game = new Game( cards,  bonus,  times);
	}
	
	
	public void addClient(ClientMainServerView client){
		clients.add(client);
		game.addPlayer(client.getName());
	}
	public boolean isPlayerHere(String playerName){
		for(Player p: game.getPlayers()){
			if(playerName == p.getName())
				return true;
		}
		 return false;
	}
	
	
	public void removeClient(){
		//TODO
	}
	public void addClientAgain(ClientMainServerView views){
		//TODO
	}
	private void initialiseGame(){
		game.initialiseGame();
		GameElements gameElements = game.getGameElements();
		List<Player> players= game.getPlayers();
		//views' observers of model
		for(Player player: players){
			for(ClientMainServerView view : clients){
				if(player.getName().equals(view.getName())){ // for unicast messages
					player.registerObserver(view.getMessageView());
					player.getPersonalBoard().registerObserver(view.getCardDescriberView());
				}
				//for broadcast messages
				player.getWarehouse().registerObserver(view.getPlayerWalletView());
				game.registerObserver(view.getCardDescriberView());
				gameElements.getGameMemory().registerObserver(view.getActionView());
				gameElements.getBoard().registerObserver(view.getPositionView());
				player.getFamilyMembers().registerObserver(view.getFamilyMembersView());
			}
		}
		//controllers' observers of view
				MainActionHandler handlers = gameElements.getHandlers();  //instead of giving all the model to the controllers we give handlers: 
				for(Player player: players){
					for(ClientMainServerView view : clients){
						new ActionController(player, handlers);
						new ChoiceController(player, handlers);
					}
				}
			game.startGame();
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
