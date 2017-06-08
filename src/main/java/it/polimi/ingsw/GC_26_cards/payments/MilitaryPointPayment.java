package it.polimi.ingsw.GC_26_cards.payments;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class MilitaryPointPayment implements Payment{
	private final int toSpend;  // the amount player has  really to spend
	private final int needed;  // the requirement
	
	public MilitaryPointPayment(int toSpend, int needed) {
		this.toSpend=toSpend;
		this.needed= needed;
	}
	
	
	public int getToSpend() {
		return toSpend;
	}
	
	public int getNeeded() {
		return needed;
	}
	
	@Override
	public synchronized boolean canPlayerGetThis(Player player, DevelopmentCardTypes type) {
		if(player.getWarehouse().getMilitaryPoints()<= needed )
			return false;
		if(player.getWarehouse().getMilitaryPoints()<toSpend)  //useless if isMilitaryPointRequirementNotNeeded()==false
			return false;
		else return true;
		
	}

	@Override
	public synchronized void pay(Player player, DevelopmentCardTypes type) {
		player.getWarehouse().spendResources(ResourcesOrPoints.newPoints(0,toSpend,0,0));
	}

	
	@Override
	public String toString(){
		return "military points payment: needed: "+needed +", to consume: "+ toSpend;
		
	}
	
}
