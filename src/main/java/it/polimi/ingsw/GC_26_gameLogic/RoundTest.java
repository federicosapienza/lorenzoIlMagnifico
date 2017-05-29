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
	Player barone;
	Player onesto;
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
		gameElements = new GameElements(players, 2, resourcesOrPointsList);
		round.start();
		assertTrue(onesto.getStatus() == PlayerStatus.PLAYING);
		assertTrue(barone.getStatus() == PlayerStatus.SUSPENDED);
	}

}
