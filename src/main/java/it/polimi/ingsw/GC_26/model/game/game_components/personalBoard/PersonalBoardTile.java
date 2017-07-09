package it.polimi.ingsw.GC_26.model.game.game_components.personalBoard;

import it.polimi.ingsw.GC_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.game.game_logic.GameParameters;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This class represents the personal bonus tile at the left of each personal board. 
 */
public class PersonalBoardTile {
	
	// resources or points given when production is done.
	private ResourcesOrPoints resourcesOrPointsProduction;
	
	// resources or points given when harvest is done.
	private ResourcesOrPoints resourcesOrPointsHarvest;
	
	// required value to activate the personal bonus tile
	private final int value=GameParameters.getStandardPersonalBonusTileValue();
	
	/**
	 * Constructor of the personal bonus tile: parameters denote whether the rules are basic or advanced.
	 * @param resourcesOrPointsProduction It represents the resources or points given when production action is performed.
	 * @param resourcesOrPointsHarvest It represents the resources or points given when harvest action is performed.
	 */
	public PersonalBoardTile(ResourcesOrPoints resourcesOrPointsProduction,ResourcesOrPoints resourcesOrPointsHarvest){
		this.resourcesOrPointsProduction=resourcesOrPointsProduction;
		this.resourcesOrPointsHarvest=resourcesOrPointsHarvest;
	}
	
	/**
	 * Method that gives the harvest bonus to the player, when he performs a harvest action
	 * @param player It's the player that has performed a harvest action and so activates the harvest bonus
	 */
	public void giveHarvestBonus(Player player){
		player.getWarehouse().add(resourcesOrPointsHarvest);
	}
	
	/**
	 * Method that gives the production bonus to the player, when he performs a production action
	 * @param player It's the player that has performed a production action and so activates the production bonus
	 */
	public void giveProductionBonus(Player player){
		player.getWarehouse().add(resourcesOrPointsProduction);
	}
	
	/**
	 * Method that returns the resources or points given by this personal bonus tile when harvest action is performed 
	 * @return resourcesOrPointsHarvest It represents the resources or points given by this personal bonus tile when harvest action is performed 
	 */
	public ResourcesOrPoints getResourcesOrPointsHarvest(){
		return resourcesOrPointsHarvest;
	}
	
	/**
	 * Method that returns the resources or points given by this personal bonus tile when production action is performed 
	 * @return resourcesOrPointsProduction It represents the resources or points given by this personal bonus tile when production action is performed 
	 */
	public ResourcesOrPoints getResourcesOrPointsProduction(){
		return resourcesOrPointsProduction;
	}
	
	public void setResourcesOrPointsHarvest(ResourcesOrPoints resourcesOrPoints){
		resourcesOrPointsHarvest= resourcesOrPoints;
	}
	
	public void setResourcesOrPointsProduction(ResourcesOrPoints resourcesOrPoints){
		resourcesOrPointsProduction= resourcesOrPoints;
	}
	
	/**
	 * Method that returns the required value to activate this personal bonus tile
	 * @return value It's the required value to activate this personal bonus tile
	 */
	public int getValue(){
		return value;
	}
	/**
	 * 
	 * @return a stirng describing the bonuses of personal board tile
	 */
	
	@Override
	public String toString() {
		return "Harvest Bonus: "+ resourcesOrPointsHarvest.toString()+ " , Production Bonus: "+resourcesOrPointsProduction.toString();
	}
}
