package it.polimi.ingsw.GC_26_gameLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_readJson.BonusImplementation;
import it.polimi.ingsw.GC_26_readJson.CardsImplementation;
import it.polimi.ingsw.GC_26_readJson.TimerValueImplementation;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class GameElementsTest {

	@Test
	public void testNullGameException() {
		boolean thrownNullExcep = false;
		Game nullGame = null;
		int numberOfPlayers = 2;
		List<ResourcesOrPoints[]> resourcesOrPointsList = null;
		Map<Integer, Integer> faithPointsTrack = null;
		Player player1 = new Player("Andrea", ResourcesOrPoints.newResources(5, 3, 2, 2));
		Player player2 = new Player("Lorelai", ResourcesOrPoints.newResources(6, 3, 2, 2));
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		try {
			GameElements nullGameElements = new GameElements(nullGame, players, numberOfPlayers, resourcesOrPointsList, faithPointsTrack);
		} catch (NullPointerException e) {
			thrownNullExcep = true;
		}
		assertTrue(thrownNullExcep);
	}
	
	@Test
	public void testIllegalNumberOfPlayers() {
		boolean thrownNumberOfPlayersExcep = false;
		Game game = new Game(new CardsImplementation(), new BonusImplementation(), new TimerValueImplementation());
		int numberOfPlayers = 5;
		List<ResourcesOrPoints[]> resourcesOrPointsList = null;
		Map<Integer, Integer> faithPointsTrack = null;
		Player player1 = new Player("Lisa", ResourcesOrPoints.newResources(5, 3, 2, 2));
		Player player2 = new Player("Phil", ResourcesOrPoints.newResources(6, 3, 2, 2));
		Player player3 = new Player("Jin", ResourcesOrPoints.newResources(7, 3, 2, 2));
		Player player4 = new Player("Kim", ResourcesOrPoints.newResources(8, 3, 2, 2));
		Player player5 = new Player("Emma", ResourcesOrPoints.newResources(8, 3, 2, 2));
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		try {
			GameElements nullGameElements = new GameElements(game, players, numberOfPlayers, resourcesOrPointsList, faithPointsTrack);
		} catch (IllegalArgumentException e) {
			thrownNumberOfPlayersExcep = true;
		}
		assertTrue(thrownNumberOfPlayersExcep);
	}

	/*
	@Test
	public void testCorrectGameElements() {
		boolean thrownNumberOfPlayersExcep = false;
		Game game = new Game(new CardsImplementation(), new BonusImplementation(), new TimerValueImplementation());
		int numberOfPlayers = 4;
		List<ResourcesOrPoints[]> resourcesOrPointsList = null;
		Map<Integer, Integer> faithPointsTrack = null;
		Player player1 = new Player("Lisa", ResourcesOrPoints.newResources(5, 3, 2, 2));
		Player player2 = new Player("Phil", ResourcesOrPoints.newResources(6, 3, 2, 2));
		Player player3 = new Player("Jin", ResourcesOrPoints.newResources(7, 3, 2, 2));
		Player player4 = new Player("Kim", ResourcesOrPoints.newResources(8, 3, 2, 2));
		List<Player> players = new ArrayList<Player>();
		List<Player> players2 = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		GameElements gameElements = new GameElements(game, players, players.size(), resourcesOrPointsList, faithPointsTrack);
		assertEquals(4, gameElements.getPlayers().size());
	}
	*/
}
