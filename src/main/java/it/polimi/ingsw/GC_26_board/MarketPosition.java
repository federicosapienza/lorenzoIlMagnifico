package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_player.Player;


import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

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
	 * Method used at the end of every turn to remove the eventual family member from the action space 
	 */
	@Override
	public void clear() {
		super.clear();
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
}
