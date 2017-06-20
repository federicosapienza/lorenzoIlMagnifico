package it.polimi.ingsw.GC_26_gameLogic;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_readJson.BonusImplementation;
import it.polimi.ingsw.GC_26_readJson.CardsImplementation;
import it.polimi.ingsw.GC_26_readJson.TimerValueImplementation;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class GameTest {
	
	@Test
	public void testInitialPeriod() {
		
		Game game1 = new Game(new CardsImplementation(), new BonusImplementation(), new TimerValueImplementation());
		assertEquals(1, game1.getPeriod());
	}
	
	@Test
	public void testPlayersList() {
		Player player1 = new Player("Bill", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
		Player player2 = new Player("Steve", ResourcesOrPoints.newResourcesOrPoints(6, 3, 2, 2, 0, 0, 0, 0));
		Game game2 = new Game(new CardsImplementation(), new BonusImplementation(), new TimerValueImplementation());
		assertFalse(game2.getPlayers().contains(player1));
		game2.getPlayers().add(player1);
		assertTrue(game2.getPlayers().contains(player1));
		assertFalse(game2.getPlayers().contains(player2));
		game2.getPlayers().add(player2);
		assertTrue(game2.getPlayers().contains(player2));
	}
	
	@Test
	public void testNotNullStatus() {
		boolean thrownNullPointerException = false;
		GameStatus status = null;
		Game game3 = new Game(new CardsImplementation(), new BonusImplementation(), new TimerValueImplementation());
		try {
			game3.setGameStatus(status);
		} catch (NullPointerException e) {
			thrownNullPointerException = true;
		}
		assertTrue(thrownNullPointerException);
	}
	
	@Test
	public void testCorrectStatus() {
		GameStatus correctStatus = GameStatus.PLAYING;
		boolean thrownNullPointerException = false;
		
		Game game4 = new Game(new CardsImplementation(), new BonusImplementation(), new TimerValueImplementation());
		try {
			game4.setGameStatus(correctStatus);
		} catch (NullPointerException e) {
			thrownNullPointerException = true;
		}
		
		assertTrue(!thrownNullPointerException && game4.getGameStatus() == GameStatus.PLAYING);
	}

}
