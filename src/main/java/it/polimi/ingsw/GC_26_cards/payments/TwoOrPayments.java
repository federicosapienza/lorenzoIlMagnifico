package it.polimi.ingsw.GC_26_cards.payments;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.Request;

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
	public synchronized boolean canPlayerGetThis(Player player, DevelopmentCardTypes type) {
		//returns true as long as one payment is ok!
		if(mode1.canPlayerGetThis(player,type) || mode2.canPlayerGetThis(player, type))
			return true;
		else return false;
	}

	@Override
	public synchronized void pay(Player player,  DevelopmentCardTypes type) {
		boolean pay1 =mode1.canPlayerGetThis(player, type);
		boolean pay2 = mode2.canPlayerGetThis(player,type);
		
		if(pay1&& pay2)
		synchronized (player) {
			player.setStatus(new Request(PlayerStatus.CHOOSINGPAYMENT, null, new CardDescriber(player.getCardUsed())));
			return;
		}
		if(pay1){
				mode1.pay(player,type);
				return;
		}
		if(pay2){
				mode2.pay(player,type);
				return;
		}
		throw new IllegalArgumentException("not enough resources");
		
	}
	
	@Override
	public String toString() {
		return "choice over two payment: (1) "+  mode1.toString()+" or (2) "+mode2.toString();
	}

}
