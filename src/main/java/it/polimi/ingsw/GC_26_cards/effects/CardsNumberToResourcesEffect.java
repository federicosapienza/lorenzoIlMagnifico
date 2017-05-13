package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class CardsNumberToResourcesEffect implements Effect{
	private DevelopmentCardTypes type;
	private ResourcesOrPoints resourcesOrPoints;
	
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
	
	private CardsNumberToResourcesEffect(CardsNumberToResourcesEffect other) {
		type= other.getType();
		resourcesOrPoints= other.getResourcesOrPoints().copy();
	}

	@Override
	public void doEffect(Player player, boolean immediate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Effect copy() {
		return new CardsNumberToResourcesEffect(this);
	}
	
	@Override
	public String toString(){
		return " gives "+ resourcesOrPoints+ " for any "+resourcesOrPoints.toString(); 
	}
		

}
