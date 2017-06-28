package it.polimi.ingsw.GC_26_cards.payments;


import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the interface that represents all the payments
 *
 */
public interface Payment {
	boolean canPlayerGetThis(Player player, DevelopmentCardTypes type);
	void pay(Player player,  DevelopmentCardTypes type); //type is needed to know if discounts are present
	String toString();
}