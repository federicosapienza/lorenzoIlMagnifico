package it.polimi.ingsw.GC_26_cards.payments;

/*This class handles the cards in which choice between two different way of paying is possible */
//it needs to call a specific handler for the interaction with user  
//TODO chiediamo al prof

public class TwoOrPayments implements TwoPayments{ 
	OnePayment mode1;
	OnePayment mode2;

	@Override
	public boolean canPlayerGetThis(Player player, ResourcesOrPoints resourcesUsedUntilNow) {
		//return true if at least one of the two methods is ok;
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pay(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Payment copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
