package it.polimi.ingsw.GC_26_actionsHandlers;

import it.polimi.ingsw.GC_26_cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.Info;
import it.polimi.ingsw.GC_26_utilities.PersonalBoardChangeNotification;

/*Handles only the cases in which the player can choose:
 *  choice values: 0 for excommunication , any other value for paying faith points
 */


public class VaticanReportHandler {

	private GameElements gameElements;

	public VaticanReportHandler(GameElements gameElements) {
		this.gameElements =gameElements;
	}
	
	
	public void perform(Player player , int choice){  
		if(choice == 0) {// player has decided to be excommunicated.
			ExcommunicationTile tile = gameElements.getGame().getThisRoundExcommunicationTiles();
			tile.runEffect(player);
			gameElements.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+ "is excommunicated"));
	    	gameElements.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.PLAYING, player.getName(), tile,
					 tile.toString(), null));
			return;
		}
		 if(player.getWarehouse().getFaithPoints()< GameParameters.getFaithPointNeeded(gameElements.getGame().getPeriod())){
		    	throw new IllegalStateException();
		    	//the player should not be here  	
		    //TODO notificare i giocatori
		}
		 
		 //TODO dargli punti vittoria!!!
		 player.getWarehouse().resetFaithPoints();
	}
}
