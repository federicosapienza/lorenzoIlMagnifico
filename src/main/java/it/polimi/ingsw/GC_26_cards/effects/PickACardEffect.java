package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class PickACardEffect implements Effect{
	private DevelopmentCardTypes type;
	private int actionValue;
	private ResourcesOrPoints discount;
	
	public PickACardEffect(DevelopmentCardTypes type, int actionValue, ResourcesOrPoints discount) {
		this.type=type;
		this.actionValue=actionValue;
		this.discount=discount;
	}
	
	public int getActionValue() {
		return actionValue;
	}
	public ResourcesOrPoints getDiscount() {
		return discount;
	}
	public DevelopmentCardTypes getType() {
		return type;
	}
	
	

	@Override
	public void doEffect(Player player, boolean immediate) {
		//TODO chiama l handler
		// TODO Auto-generated method stub
		
	}

	
}
