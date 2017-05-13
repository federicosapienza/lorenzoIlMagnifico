package it.polimi.ingsw.GC_26_cards.payments;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

/*This class handles the cards in which choice between two different way of paying is possible */
//it needs to call a specific handler for the interaction with user  
//TODO chiediamo al prof

public class TwoOrPayments implements Payment{ 
	Payment mode1;
	Payment mode2;
	
	public TwoOrPayments(Payment one, Payment two) { // constructor used for deep cloning. See copy() here and in ResourcesAndPoints
		mode1=one;
		mode2=two;
	}
	
	public Payment getMode1() {
		return mode1;
	}
	public Payment getMode2() {
		return mode2;
	}
	
	private TwoOrPayments(TwoOrPayments other) {
		mode1= other.getMode1().copy();
		mode2=other.getMode2().copy();
	}

	@Override
	public boolean canPlayerGetThis(Player player, ResourcesOrPoints resourcesUsedUntilNow) {
		//returns true as long as one payment is ok!
		if(mode1.canPlayerGetThis(player, resourcesUsedUntilNow) || mode2.canPlayerGetThis(player, resourcesUsedUntilNow))
			return false;
		else return true;
	}

	@Override
	public void pay(Player player) {
		// TODO chiamare l handler!!!
		
		
	}

	@Override
	public Payment copy() {  // passes an object which is identical to the first: allows deep cloning
		return new TwoOrPayments(this);
	}

}
