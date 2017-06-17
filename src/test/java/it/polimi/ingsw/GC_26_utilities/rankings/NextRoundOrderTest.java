package it.polimi.ingsw.GC_26_utilities.rankings;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class NextRoundOrderTest {
	
	Player player1 = new Player("Aldo", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
	Player player2 = new Player("Bob", ResourcesOrPoints.newResourcesOrPoints(6, 3, 2, 2, 0, 0, 0, 0));
	List<Player> playersList = new ArrayList<Player>();
	NextRoundOrder nextRoundOrder;
	Player player3 = new Player("Charles", ResourcesOrPoints.newResourcesOrPoints(7, 3, 2, 2, 0, 0, 0, 0));
	Player player4 = new Player("Greg", ResourcesOrPoints.newResourcesOrPoints(8, 3, 2, 2, 0, 0, 0, 0));
	Player player5 = new Player("Matt", ResourcesOrPoints.newResourcesOrPoints(9, 3, 2, 2, 0, 0, 0, 0));
	
	@Test
	public void testNotNull() {
		playersList.add(player1);
		playersList.add(player2);
		
		nextRoundOrder = new NextRoundOrder(playersList);
		assertNotNull(nextRoundOrder);
	}
	
	@Test
	public void testNullPlayersList(){
		boolean thrownNullExcep = false;
		List<Player> nullPlayersList = null;
		try {
			nextRoundOrder = new NextRoundOrder(nullPlayersList);
		} catch (NullPointerException e) {
			thrownNullExcep = true;
		}
		assertTrue(thrownNullExcep);
	}
	
	@Test
	public void testSizePlayersList() {
		boolean thrownIllegalExcep = false;
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		assertEquals(5, players.size());
		try {
			nextRoundOrder = new NextRoundOrder(players);
		} catch (IllegalArgumentException e) {
			thrownIllegalExcep = true;
		}
		assertTrue(thrownIllegalExcep);
	}
	
	@Test
	public void testNextRoundOrder(){
		playersList.add(player1);
		playersList.add(player2);
		
		nextRoundOrder = new NextRoundOrder(playersList);
		nextRoundOrder.nextRoundChanging(player1);
		nextRoundOrder.nextRoundChanging(player2);
		assertTrue(nextRoundOrder.getNextRoundOrder().contains(player1.getName()));
		assertTrue(nextRoundOrder.getNextRoundOrder().contains(player2.getName()));
		assertFalse(nextRoundOrder.getNextRoundOrder().contains(player3.getName()));
		nextRoundOrder.nextRoundChanging(player3);
		assertTrue(nextRoundOrder.getNextRoundOrder().contains(player3.getName()));
		
	}

}
