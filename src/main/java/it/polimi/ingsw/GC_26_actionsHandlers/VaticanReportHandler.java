package it.polimi.ingsw.GC_26_actionsHandlers;

import it.polimi.ingsw.GC_26_cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;

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
			ExcommunicationTile tile = gameElements.getGame().getPeriod().getExcommunicationTile();
			tile.runEffect(player);
			//TODO notificare i giocatori
			return;
		}
		 if(player.getWarehouse().getFaithPoints()< GameParameters.getFaithPointNeeded(gameElements.getGame().getPeriodNumber())){
		    	throw new IllegalStateException();
		    	//the player should not be here  	
		    //TODO notificare i giocatori
		}
		 
		 //TODO dargli punti vittoria!!!
		 player.getWarehouse().resetFaithPoints();
	}
}
