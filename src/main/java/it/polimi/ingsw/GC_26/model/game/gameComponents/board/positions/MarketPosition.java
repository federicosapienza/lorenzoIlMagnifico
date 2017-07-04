package it.polimi.ingsw.GC_26.model.game.gameComponents.board.positions;

import it.polimi.ingsw.GC_26.model.game.gameComponents.board.positions.common.SinglePosition;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Class that represents every action space of the Market 
 */

public class MarketPosition extends SinglePosition {
	private final int number;
	private final ResourcesOrPoints resourcesOrPointsinPosition;
	
	/**
	 * Constructor: it creates an action space of the Market with the values contained in the parameters
	 * @param number It's the number of position of the action space
	 * @param resourcesOrPointsinPosition are the resources or points given as bonuses to the player who puts a family
	 * member in this space
	 * @param valueOfPosition It's the minimum required value that the family member must have to be put in this action
	 * space of the Market 
	 */
	public MarketPosition(int number,ResourcesOrPoints resourcesOrPointsinPosition,int valueOfPosition){
		super(valueOfPosition);
		this.number=number;
		this.resourcesOrPointsinPosition=resourcesOrPointsinPosition;
	}

	
	/**
	 * Method that gives the bonus resources or points present in the action space to the player who has put a family member
	 * in this action space of the Market 
	 * @param player It's the player who has put a family member in this action space of the Market 
	 */
	public void addResources(Player player){
		player.getWarehouse().add(resourcesOrPointsinPosition);
	}
	
	/**
	 * Method that returns the resources or points that this action space of the Market give as a bonus to the player who
	 * has put a family member here 
	 * @return
	 */
	public ResourcesOrPoints getResourcesOrPointsinPosition(){
		return resourcesOrPointsinPosition;
	}
	
	/**
	 * Method that returns the value of the market position
	 * @return
	 */
	public int getNumber() {
		return number;
	}
}
