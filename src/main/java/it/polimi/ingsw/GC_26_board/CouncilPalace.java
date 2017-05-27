package it.polimi.ingsw.GC_26_board;


import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class CouncilPalace extends MultiplePosition {
	
	private ResourcesOrPoints ResourcesOrPointsInPosition;
	
	public CouncilPalace(ResourcesOrPoints resourcesOrPoints,int valueOfPosition){
		super(valueOfPosition);
		this.ResourcesOrPointsInPosition=resourcesOrPoints;
	}
	
	public void addBonusResources(Player player){
		player.getWarehouse().add(ResourcesOrPointsInPosition);
	}
	
	public ResourcesOrPoints getResourcesOrPointsInPosition() {
		return ResourcesOrPointsInPosition;
	}
	/*public String PrintList(){
		//TODO
	}*/
	
	/*public ArrayList getRanking(){
		//TODO
	}*/
}
