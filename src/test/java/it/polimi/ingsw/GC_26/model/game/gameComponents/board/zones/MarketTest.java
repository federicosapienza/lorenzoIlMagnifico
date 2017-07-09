package it.polimi.ingsw.GC_26.model.game.gameComponents.board.zones;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26.model.game.game_components.board.positions.MarketPosition;
import it.polimi.ingsw.GC_26.model.game.game_components.board.zones.Market;
import it.polimi.ingsw.GC_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;

public class MarketTest {

	ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 3, 2, 2);
	ResourcesOrPoints startingResources2 = ResourcesOrPoints.newResources(6, 3, 2, 2);
	ResourcesOrPoints startingResources3 = ResourcesOrPoints.newResources(7, 3, 2, 2);
	ResourcesOrPoints startingResources4 = ResourcesOrPoints.newResources(8, 3, 2, 2);
	
	ResourcesOrPoints[] marketResOrPts = new ResourcesOrPoints[4];
	
	ResourcesOrPoints marketResOrPts1 = ResourcesOrPoints.newResources(5, 0, 0, 0);
	ResourcesOrPoints marketResOrPts2 = ResourcesOrPoints.newResources(0, 5, 0, 0);
	ResourcesOrPoints marketResOrPts3 = ResourcesOrPoints.newResourcesOrPoints(2, 0, 0, 0, 0, 3, 0, 0);
	ResourcesOrPoints marketResOrPts4 = ResourcesOrPoints.newPoints(0, 0, 0, 2);
	
	@Test
	public void testCorrectNumberOfPositionsActivated() {
		boolean thrownIllegalPositionExcep = false;
		int positionsActivated = 0;
		marketResOrPts[0] = marketResOrPts1;
		marketResOrPts[1] = marketResOrPts2;
		marketResOrPts[2] = marketResOrPts3;
		marketResOrPts[3] = marketResOrPts4;
		Player player1 = new Player("John", startingResources);
		Player player2 = new Player("Matt", startingResources2);
		List<Player> playersList = new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		Market market = new Market(playersList.size(), marketResOrPts);
		try {
			positionsActivated = market.getPositionsActivated();
		} catch (IllegalArgumentException e) {
			thrownIllegalPositionExcep = true;
		}
		
		assertTrue(positionsActivated == 2 && !thrownIllegalPositionExcep);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testGetIllegalPosition() {
		MarketPosition marketPosition;
		marketResOrPts[0] = marketResOrPts1;
		marketResOrPts[1] = marketResOrPts2;
		marketResOrPts[2] = marketResOrPts3;
		marketResOrPts[3] = marketResOrPts4;
		Player player1 = new Player("John", startingResources);
		Player player2 = new Player("Matt", startingResources2);
		Player player3 = new Player("Luke", startingResources3);
		List<Player> playersList = new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		playersList.add(player3);
		Market market = new Market(playersList.size(), marketResOrPts);
		marketPosition = market.getPosition(3);
		marketPosition.clear();
	}
	
	@Test
	public void test4PlayersMarket() {
		boolean thrownIllegalPositionExcep = false;
		MarketPosition marketPosition;
		marketResOrPts[0] = marketResOrPts1;
		marketResOrPts[1] = marketResOrPts2;
		marketResOrPts[2] = marketResOrPts3;
		marketResOrPts[3] = marketResOrPts4;
		Player player1 = new Player("John", startingResources);
		Player player2 = new Player("Matt", startingResources2);
		Player player3 = new Player("Luke", startingResources3);
		Player player4 = new Player("Mark", startingResources4);
		List<Player> playersList = new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		playersList.add(player3);
		playersList.add(player4);
		Market market = new Market(playersList.size(), marketResOrPts);
		
		try {
			marketPosition = market.getPosition(3);
			marketPosition.clear();
		} catch (IllegalArgumentException e) {
			thrownIllegalPositionExcep = true;
		}
		assertTrue(market.getPositionsActivated() == 4 && !thrownIllegalPositionExcep);
	}
}
