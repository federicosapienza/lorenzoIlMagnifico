package it.polimi.ingsw.GC_26_utilities;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;

public class PersonalBoardChangeNotificationTest {

	@Test (expected = NullPointerException.class)
	public void testNotNullPlayerName() {
		GameStatus gStatus = GameStatus.PLAYING;
		String username = null; 
		CardDescriber card = null;
		String boardTileValues = "boardtilevalues";
		PersonalBoardChangeNotification notificationError = new PersonalBoardChangeNotification(gStatus, username, card, boardTileValues);
		notificationError.toString();
	}
	
	@Test (expected = NullPointerException.class)
	public void testNotNullStatus() {
		GameStatus gStatus = null;
		String username = "Maverick"; 
		CardDescriber card = null;
		String boardTileValues = "boardtilevalues";
		PersonalBoardChangeNotification notificationError = new PersonalBoardChangeNotification(gStatus, username, card, boardTileValues);
		notificationError.toString();
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
			notification2.getGameStatus().equals(gStatus);
		} catch (NullPointerException e) {
			thrownNullExcep = true;
		}
		assertFalse(thrownNullExcep);
	}

}
