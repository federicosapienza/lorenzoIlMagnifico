package it.polimi.ingsw.GC_26_utilities;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

public class PersonalBoardChangeNotificationTest {

	@Test
	public void testNotNullPlayerName() {
		boolean thrownNullExcep = false;
		GameStatus gStatus = GameStatus.PLAYING;
		String username = null; 
		CardDescriber card = null;
		String boardTileValues = "boardtilevalues";
		try {
			PersonalBoardChangeNotification notificationError = new PersonalBoardChangeNotification(gStatus, username, card, boardTileValues);
		} catch (NullPointerException e) {
			thrownNullExcep = true;
		}
		assertTrue(thrownNullExcep);
	}
	
	@Test
	public void testNotNullStatus() {
		boolean thrownNullExcep = false;
		GameStatus gStatus = null;
		String username = "Maverick"; 
		CardDescriber card = null;
		String boardTileValues = "boardtilevalues";
		try {
			PersonalBoardChangeNotification notificationError = new PersonalBoardChangeNotification(gStatus, username, card, boardTileValues);
		} catch (NullPointerException e) {
			thrownNullExcep = true;
		}
		assertTrue(thrownNullExcep);
	}
	
	@Test
	public void testCorrectConstructor() {
		boolean thrownNullExcep = false;
		GameStatus gStatus = GameStatus.PLAYING;
		String username = "Valentino"; 
		CardDescriber card = null;
		String boardTileValues = "boardtilevalues";
		PersonalBoardChangeNotification notification = new PersonalBoardChangeNotification(gStatus, username, card, boardTileValues);
		assertEquals("Valentino", notification.getPlayerName());
		assertEquals(GameStatus.PLAYING, notification.getGameStatus());
		assertNull(card);
		String nullBoardTileValues = null;
		try {
			PersonalBoardChangeNotification notification2 = new PersonalBoardChangeNotification(gStatus, username, card, nullBoardTileValues);
		} catch (NullPointerException e) {
			thrownNullExcep = true;
		}
		assertFalse(thrownNullExcep);
	}

}
