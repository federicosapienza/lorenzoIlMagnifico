package it.polimi.ingsw.GC_26.messages;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.player.PlayerStatus;

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
