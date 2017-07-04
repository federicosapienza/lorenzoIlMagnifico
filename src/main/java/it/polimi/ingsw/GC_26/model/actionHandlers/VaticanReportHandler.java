package it.polimi.ingsw.GC_26.model.actionHandlers;

import java.util.Map;

import it.polimi.ingsw.GC_26.messages.Info;
import it.polimi.ingsw.GC_26.messages.PersonalBoardChangeNotification;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.game.gameLogic.GameElements;
import it.polimi.ingsw.GC_26.model.game.gameLogic.GameParameters;
import it.polimi.ingsw.GC_26.model.game.gameLogic.GameStatus;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.utilities.exceptions.IllegalActionException;

/*Handles only the cases in which the player can choose:
 *  choice values: 0 for excommunication , any other value for paying faith points
 */


public class VaticanReportHandler extends Handler{

	private GameElements gameElements;

	public VaticanReportHandler(GameElements gameElements) {
		super(gameElements.getPlayers());
		this.gameElements =gameElements;
	}
	
	
	public void perform(Player player , int choice){  
		if(choice == 0) {// player has decided to be excommunicated.
			ExcommunicationTile tile = gameElements.getGame().getThisRoundExcommunicationTiles();
			tile.runEffect(player);
			super.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+ "is excommunicated"));
			super.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.PLAYING, player.getName(), new CardDescriber(tile) ,null));
			return;
		}
		
		if(player.getWarehouse().getFaithPoints()< GameParameters.getFaithPointNeeded(gameElements.getGame().getPeriod())){
			throw new IllegalActionException("player should not be asked for vatican report choice");
			//the player should not be here  	
		}
		 
		 Map<Integer, Integer> faithPointsTrack=gameElements.getFaithPointsTrack();
		 int temp= faithPointsTrack.get(player.getWarehouse().getFaithPoints());
		 //giving the player more bonus points if associated permanent effects is on
		 temp += player.getPermanentModifiers().getAdditionalVP();
		 
		 super.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), 
				 				player.getName()+" support the Church and gains "+ temp+" victory points" ));
		 player.getWarehouse().add(ResourcesOrPoints.newPoints(temp, 0, 0, 0));
		 player.getWarehouse().resetFaithPoints();
	}
}
