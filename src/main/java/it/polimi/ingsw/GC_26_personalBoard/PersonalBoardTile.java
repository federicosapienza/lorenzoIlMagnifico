package it.polimi.ingsw.GC_26_personalBoard;

import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class PersonalBoardTile {
	private ResourcesOrPoints resourcesOrPointsProduction;
	private ResourcesOrPoints resourcesOrPointsHarvest;
	private final int value=GameParameters.getStandardPersonalBonusTileValue();
	
	public PersonalBoardTile(ResourcesOrPoints resourcesOrPointsProoduction,ResourcesOrPoints resourcesOrPointsHarvest){
		this.resourcesOrPointsProduction=resourcesOrPointsProoduction;
		this.resourcesOrPointsHarvest=resourcesOrPointsHarvest;
	}
	
	public void giveHarvestBonus(Player player){
		player.getWarehouse().add(resourcesOrPointsHarvest);
	}
	
	public void giveProductionBonus(Player player){
		player.getWarehouse().add(resourcesOrPointsProduction);
	}
	
	public ResourcesOrPoints getResourcesOrPointsProduction(){
		return resourcesOrPointsProduction;
	}
	
	public ResourcesOrPoints getResourcesOrPointsHarvest(){
		return resourcesOrPointsHarvest;
	}
	
	public int getValue(){
		return value;
	}
}
