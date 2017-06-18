package it.polimi.ingsw.GC_26_utilities;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

public class RequestTest {

	@Test
	public void testConstructor() {
		PlayerStatus pStatus = PlayerStatus.PLAYING;
		String message = "Ciao";
		CardDescriber card = null;
		Request request = new Request(pStatus, message, card);
		assertEquals("Ciao", request.getMessage());
		assertEquals(PlayerStatus.PLAYING, request.getStatus());
		assertNull(request.getCard());
	}

}
