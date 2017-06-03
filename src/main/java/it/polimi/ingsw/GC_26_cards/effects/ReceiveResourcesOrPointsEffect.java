package it.polimi.ingsw.GC_26_cards.effects;


import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class ReceiveResourcesOrPointsEffect implements Effect{
	private final ResourcesOrPoints resources; 
	
	public ReceiveResourcesOrPointsEffect(ResourcesOrPoints resources) {
		this.resources=resources;
	}
	
	public ResourcesOrPoints getResources() {
		return resources;
	}
	
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getWarehouse().add(resources);
		
		if(immediate && player.getPermanentModifiers().isDoubleEarningOn())  //Santa Rita Effect
			player.getWarehouse().add(resources);;
			
		
	}

	
	@Override 
	public String toString(){
		return " receive: "+ resources.toString(); 
	}
	

}
