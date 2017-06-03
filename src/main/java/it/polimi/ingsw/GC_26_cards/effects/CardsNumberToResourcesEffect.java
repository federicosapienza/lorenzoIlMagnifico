package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class CardsNumberToResourcesEffect implements Effect{
	private final DevelopmentCardTypes type;
	private final ResourcesOrPoints resourcesOrPoints;
	
	public CardsNumberToResourcesEffect(DevelopmentCardTypes type, ResourcesOrPoints resources) {
		this.type= type;
		this.resourcesOrPoints= resources;
	}
	
	public ResourcesOrPoints getResourcesOrPoints() {
		return resourcesOrPoints;
	}
	public DevelopmentCardTypes getType() {
		return type;
	}
	

	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		int number = player.getPersonalBoard().getNumberOfCardPerType(type);
		for(int i=0; i <number; i++){
			player.getWarehouse().add(resourcesOrPoints);
		}
		
	}


	@Override
	public String toString(){
		return " gives "+ resourcesOrPoints+ " for any "+resourcesOrPoints.toString(); 
	}
		

}
