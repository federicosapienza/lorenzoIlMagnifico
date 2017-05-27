package it.polimi.ingsw.GC_26_cards.payments;

import it.polimi.ingsw.GC_26_player.Player;

/*This class handles the cards in which choice between two different way of paying is possible */
//it needs to call a specific handler for the interaction with user  


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
	

	@Override
	public boolean canPlayerGetThis(Player player) {
		//returns true as long as one payment is ok!
		if(mode1.canPlayerGetThis(player) || mode2.canPlayerGetThis(player))
			return false;
		else return true;
	}

	@Override
	public void pay(Player player) {
		// TODO chiamare l handler!!!
		
		
	}

}
