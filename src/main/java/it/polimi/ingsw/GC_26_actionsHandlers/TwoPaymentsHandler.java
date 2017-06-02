package it.polimi.ingsw.GC_26_actionsHandlers;


import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelpmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.effects.TradeEffect;
import it.polimi.ingsw.GC_26_cards.payments.Payment;
import it.polimi.ingsw.GC_26_cards.payments.TwoOrPayments;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_player.Player;


/* Handles the cases in which the player is asked to choose between two different payments:
 * the player is asked to choose only if both methods are possibles: so there is no need to check if the player has enough resources:
 * it has been checked before
 * 
 * 
 * Choice method: 1 stands for first method, any other value for second.
 */

public class TwoPaymentsHandler {
	GameElements gameElements;

	public TwoPaymentsHandler(GameElements gameElements) {
		this.gameElements= gameElements;
	}
	
	public void perform(Player player, int choice){
		// we will need to recast  DevelopmentCard to  DevelopmentCardImplementation and to recast Payment to DoublePayment;
				DevelopmentCard temp=  player.getCardUsed();
				DevelpmentCardImplementation  card= (DevelpmentCardImplementation) temp;
				Payment payment = card.getPayment();
				TwoOrPayments twoOrPayments =(TwoOrPayments) payment;
		//paying
				if(choice==1){
					twoOrPayments.getMode1().pay(player);
				}
				else {
					twoOrPayments.getMode2().pay(player);
					}
				}
					
				
				
	

}
