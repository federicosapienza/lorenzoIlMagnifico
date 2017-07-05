package it.polimi.ingsw.GC_26.model.actionHandlers;


import java.util.List;

import it.polimi.ingsw.GC_26.messages.PersonalBoardChangeNotification;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments.Payment;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments.TwoOrPayments;
import it.polimi.ingsw.GC_26.model.game.gameLogic.GameStatus;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the handler that handles the cases in which the player is asked to choose between two different payments:
 * the player is asked to choose only if both methods are possible, so there is no need to check if the player has enough resources
 * because it has been checked before
 * 
 * Choice rules: 1 means first payment, any other value means second payment.
 */


public class TwoPaymentsHandler extends Handler{


	/**
	 * Constructor: it creates a TwoPaymentsHandler for the list of players contained in the parameter 
	 * @param players It's the list of players that can perform the payments which contain two different exclusive payments
	 */
	public TwoPaymentsHandler(List<Player> players) {
		super(players);
	}

	/**
	 * Method called to perform the payment chosen by the player contained in the parameter
	 * @param player It's the player that has chosen the payment 
	 * @param choice It's the payment which has been chosen by the player
	 */
	public void perform(Player player, int choice){
		
		// we will need to recast  DevelopmentCard to  DevelopmentCardImplementation and to recast Payment to DoublePayment;
		DevelopmentCard temp=  player.getCardUsed();
		DevelopmentCardImplementation card= (DevelopmentCardImplementation) temp;
		Payment payment = card.getPayment();
		TwoOrPayments twoOrPayments =(TwoOrPayments) payment;
		//paying
		if(choice==1){
			twoOrPayments.getMode1().pay(player,card.getType());
		}
		else {
			twoOrPayments.getMode2().pay(player,card.getType());
		}
		card.runImmediateEffect(player);
		// character cards' permanent effect is immediately activated 
		if(card.getType() == DevelopmentCardTypes.CHARACTERCARD)
			card.runPermanentEffect(player);
		
		
		player.setCardUsed(null);  //cleaning parameter of the card no more used
		
		/**
		 * adding the card to the personal board
		 */
		
		player.getPersonalBoard().add(card);
			
		
		/**
		 * Notifying players about changes to the personal board
		 */
		super.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.PLAYING, player.getName(), new CardDescriber(card), null));
		
	}
	
					
				
				
	

}
