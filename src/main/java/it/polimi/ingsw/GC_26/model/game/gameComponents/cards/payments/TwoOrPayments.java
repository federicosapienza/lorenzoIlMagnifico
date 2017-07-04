package it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments;

import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.model.player.PlayerStatus;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class implements the Payment interface and represents the cards that give players the possibility to choose 
 * between two different payments. This implies that a specific handler has to be called to interact with the player
 *
 */



public class TwoOrPayments implements Payment{ 
	Payment mode1;
	Payment mode2;
	
	/**
	 * Constructor: it creates a payment that contains two different payments
	 * @param one It's the first payment
	 * @param two It's the second payment
	 */
	public TwoOrPayments(Payment one, Payment two) { // constructor used for deep cloning. See copy() here and in ResourcesAndPoints
		mode1=one;
		mode2=two;
	}
	
	/**
	 * Method that returns the first payment
	 * @return the first payment
	 */
	public Payment getMode1() {
		return mode1;
	}
	
	/**
	 * Method that returns the second payment
	 * @return the second payment
	 */
	public Payment getMode2() {
		return mode2;
	}
	
	/**
	 * Method that checks if the player contained in the parameter can perform at least one of the two payments in order 
	 * to get the type of card contained in the parameter.
	 * @return true if the player can perform at least one of the two payments; false if the player can't perform any payment 
	 */
	@Override
	public synchronized boolean canPlayerGetThis(Player player, DevelopmentCardTypes type) {
		//returns true as long as one payment is ok!
		return mode1.canPlayerGetThis(player,type) || mode2.canPlayerGetThis(player, type);
	}

	/**
	 * Method that makes the player contained in the parameter perform the payment: if he can perform both the 
	 * payment1 and payment2, he can choose which one to perform; else, the only possible one is automatically performed;
	 * if neither can be performed, an illegal argument exception will be thrown
	 */
	@Override
	public synchronized void pay(Player player,  DevelopmentCardTypes type) {
		boolean pay1 = mode1.canPlayerGetThis(player, type);
		boolean pay2 = mode2.canPlayerGetThis(player,type);
		
		if(pay1&& pay2  && player.getStatus()!=PlayerStatus.WAITINGHISTURN &&  player.getStatus()!=PlayerStatus.SUSPENDED){
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
	
	/**
	 * Method that describes the conditions of the payment as a string
	 */
	@Override
	public String toString() {
		return "(1) "+  mode1.toString()+" or (2) "+mode2.toString();
	}

}
