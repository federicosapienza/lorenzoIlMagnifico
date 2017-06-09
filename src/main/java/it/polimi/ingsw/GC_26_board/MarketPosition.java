package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class MarketPosition extends SinglePosition {
	private final int number;
	private final ResourcesOrPoints resourcesOrPointsinPosition;
	
	public MarketPosition(int number,ResourcesOrPoints resourcesOrPointsinPosition,int valueOfPosition){
		super(valueOfPosition);
		this.number=number;
		this.resourcesOrPointsinPosition=resourcesOrPointsinPosition;
	}

	@Override
	public void clear() {
		super.clear();
	}
	
	public void addResources(Player player){
		player.getWarehouse().add(resourcesOrPointsinPosition);
	}
	
	public ResourcesOrPoints getResourcesOrPointsinPosition(){
		return  resourcesOrPointsinPosition;
	}
}
