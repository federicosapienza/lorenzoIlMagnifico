package it.polimi.ingsw.GC_26_utilities;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

public class RequestTest {


	@Test
	public void testCardInConstructor() {
		boolean thrownException = false;
		PlayerStatus status = PlayerStatus.PLAYING;
		String message = "Ciao";
		CardDescriber nullCard = null;
		try {
			Request nullRequest = new Request(status, message, nullCard);
		} catch (NullPointerException e) {
			thrownException = true;
		}
		assertTrue(thrownException);
	}
	
	

}
