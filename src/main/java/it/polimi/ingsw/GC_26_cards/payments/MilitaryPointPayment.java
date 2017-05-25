package it.polimi.ingsw.GC_26_cards.payments;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class MilitaryPointPayment implements Payment{
	private final int toSpend;  // the amount player has  really to spend
	private final int needed;  // the requirement
	
	public MilitaryPointPayment(int toSpend, int needed) {
		this.toSpend=toSpend;
		this.needed= needed;
	}
	
	private MilitaryPointPayment(MilitaryPointPayment other) {// constructor used for deep cloning. See copy() here and in ResourcesAndPoints
		this.toSpend = other.getToSpend();
		this.needed = other.getNeeded();
	}
	
	public int getToSpend() {
		return toSpend;
	}
	
	public int getNeeded() {
		return needed;
	}
	
	@Override
	public boolean canPlayerGetThis(Player player) {
		if(player.getWarehouse().getMilitaryPoints()<= needed || 
				player.getPermanentModifiers().isMilitaryPointRequirementNotNeeded()==true)// Cesare Borgia effect
			return false;
		else if(player.getWarehouse().getMilitaryPoints()<toSpend)  //useless if isMilitaryPointRequirementNotNeeded()==false
			return false;
		else return true;
		
	}

	@Override
	public void pay(Player player) {
		player.getWarehouse().spendResources(ResourcesOrPoints.newPoints(0,toSpend,0,0));
	}

	@Override
	public Payment copy() {   // passes an object which is identical to the first: allows deep cloning
		return new MilitaryPointPayment(this);
		
	}
	
	@Override
	public String toString(){
		return "military points payment: needed: "+needed +", to consume: "+ toSpend;
		
	}
	
}
