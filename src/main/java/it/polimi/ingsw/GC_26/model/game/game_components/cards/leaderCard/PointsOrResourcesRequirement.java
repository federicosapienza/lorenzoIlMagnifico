package it.polimi.ingsw.GC_26.model.game.game_components.cards.leaderCard;

import it.polimi.ingsw.GC_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class implements the Requirement interface and represents the requirement that checks if the player has
 * enough resources or points 
 *
 */
public class PointsOrResourcesRequirement implements Requirement{
	private final ResourcesOrPoints needed;
	
	/**
	 * Constructor: it creates the requirement with the needed resources and points contained in the parameter
	 * @param needed the resources or points that the player must have to satisfy the requirement
	 */
	public PointsOrResourcesRequirement(ResourcesOrPoints needed) {
		this.needed= needed;
	}
	
	/**
	 * Method that checks if the player contained in the parameter has enough resources and points in order to satisfy 
	 * the requirement
	 */
	@Override
	public boolean checkRequirement(Player player) {
		return player.getWarehouse().areResourcesEnough(needed);
	}
	
	/**
	 * Method that explains the requirement as a string
	 */
	@Override
	public String toString() {
		return "Player needs "+needed;
	}

		

}
