package it.polimi.ingsw.GC_26_cards.effects;


import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class ReceiveResourcesOrPointsEffect implements Effect{
	ResourcesOrPoints resources; 
	
	public ReceiveResourcesOrPointsEffect(ResourcesOrPoints resources) {
		this.resources=resources;
	}
	
	public ResourcesOrPoints getResources() {
		return resources;
	}
	
	@Override
	public void doEffect(Player player, boolean immediate) {
		player.getWarehouse().add(resources);
		if(player.getPermanentModifiers().IsresourcesMalusOn())
			player.getPermanentModifiers().runMalus(resources);
		
		if(immediate && player.getPermanentModifiers().isDoubleEarningOn())  //Santa Rita Effect
			player.getPermanentModifiers().DoDoubleEarning(resources);
			
		
	}

	
	@Override 
	public String toString(){
		return " receive: "+ resources.toString(); 
	}
	

}
