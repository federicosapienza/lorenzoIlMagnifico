package it.polimi.ingsw.GC_26_gameLogic;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class RoundTest {
	Player barone = new Player("barone", ResourcesOrPoints.newResources(5, 3, 2, 2));
	Player onesto = new Player("onesto", ResourcesOrPoints.newResources(6, 3, 2, 2));
	ArrayList<Player> players;
	Round round;
	ResourcesOrPoints resourcesOrPoints;
	GameElements gameElements;
	Iterator<Player> iter = gameElements.getPlayers().iterator();
	java.util.List<ResourcesOrPoints[]> resourcesOrPointsList; 
	
	@Test
	public void test() {
		
		barone.setStatus(PlayerStatus.SUSPENDED);
		onesto.setStatus(PlayerStatus.WAITINGHISTURN);
		players.add(barone);
		players.add(onesto);
		gameElements = new GameElements(players, 2, null);
		round.start();
		assertTrue(barone.getStatus() == PlayerStatus.SUSPENDED);
	}

}
