package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.payments.MilitaryPointPayment;
import it.polimi.ingsw.GC_26_gameLogic.Game;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import junit.framework.Test;

 public class MainObserver {
	Game game = new Game(null, null, null, null); }
 
 
 
	//ci sarà l identificatore della connessione
/*	private void addClient(Game game,String name){
		Player player = game.addPlayer(name);
		
		MainServerView serverView= new MainServerView(name); 
		player.getWarehouse().registerObserver(serverView.getPlayerWalletView());
		player.registerObserver(serverView.getStringView());
		game.registerObserver(serverView.getCardDescriberView());;
	} 

	private void initiliasizeGame(){
		;
	}
	
	
	

	

	public void start() {
		
		addClient(game, "tizio");
		addClient(game, "caio");
		
		
		
		Player player = game.addPlayer("name");
		
		
		MainServerView serverView= new MainServerView("name"); 
		player.getWarehouse().registerObserver(serverView.getPlayerWalletView());
		player.registerObserver(serverView.getStringView());
		game.registerObserver(serverView.getCardDescriberView());; 
		
		
		player.getWarehouse().add(ResourcesOrPoints.newResources(1, 0, 0, 0));
		player.notifyObservers("funzioni?");
		game.notifyObservers(DevelpmentCardImplementation.ventureCard("prova", 1, new MilitaryPointPayment(0, 0), null, null, 0));
		game.getGameElements().notifyObservers("test22");
		
	}
	
}

*/
