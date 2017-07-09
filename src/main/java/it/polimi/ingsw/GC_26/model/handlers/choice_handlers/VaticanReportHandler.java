package it.polimi.ingsw.GC_26.model.handlers.choice_handlers;

import java.util.Map;

import it.polimi.ingsw.GC_26.messages.Info;
import it.polimi.ingsw.GC_26.messages.PersonalBoardChangeNotification;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.game.game_logic.GameElements;
import it.polimi.ingsw.GC_26.model.game.game_logic.GameParameters;
import it.polimi.ingsw.GC_26.model.game.game_logic.GameStatus;
import it.polimi.ingsw.GC_26.model.handlers.Handler;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.utilities.exceptions.IllegalActionException;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This class represents the handler for the Vatican Report phase of the game. It handles only the cases in which the player can 
 * decide if he wants to support the Church or not.
 * 
 */



public class VaticanReportHandler extends Handler{

	private GameElements gameElements;

	/**
	 * Constructor: it creates a handler for the Vatican Report phase, based on the game elements of the game that calls this
	 * class. 
	 * @param gameElements the game elements of the game that calls this class. 
	 */
	public VaticanReportHandler(GameElements gameElements) {
		super(gameElements.getPlayers());
		this.gameElements =gameElements;
	}
	
	/**
	 * Method called to update the progress of the game, depending on the choice selected by the player.
	 * Choice values are the following: 0 means no support to the Church, any other value means support to the Church
	 * @param player It's the player that has to decide if he wants to support the Church or not
	 * @param choice It's the choice selected by the player
	 */
	public void perform(Player player, int choice){  
		if(choice == 0) {// player has decided to be excommunicated.
			ExcommunicationTile tile = gameElements.getGame().getThisRoundExcommunicationTiles();
			tile.runEffect(player);
			super.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+ " is excommunicated"));
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
