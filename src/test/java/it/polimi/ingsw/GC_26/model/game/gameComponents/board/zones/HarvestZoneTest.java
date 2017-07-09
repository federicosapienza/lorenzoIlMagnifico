package it.polimi.ingsw.GC_26.model.game.gameComponents.board.zones;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26.model.game.game_components.board.zones.HarvestZone;
import it.polimi.ingsw.GC_26.model.game.game_components.dices.Colour;
import it.polimi.ingsw.GC_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;

public class HarvestZoneTest {

	@Test
	public void testCorrectNumberOfHarvestSpaces() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 3, 2, 2);
		ResourcesOrPoints startingResources2 = ResourcesOrPoints.newResources(6, 3, 2, 2);
		ResourcesOrPoints startingResources3 = ResourcesOrPoints.newResources(7, 3, 2, 2);
		Player player1 = new Player("John", startingResources);
		Player player2 = new Player("Matt", startingResources2);
		Player player3 = new Player("Luke", startingResources3);
		List<Player> playersList = new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		playersList.add(player3);
		HarvestZone harvestZone = new HarvestZone(1, 1, playersList.size());
		assertEquals(2, harvestZone.getPositionsActivated());
	}
	
	@Test
	public void testAddPlayerInHarvestZone() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 3, 2, 2);
		ResourcesOrPoints startingResources2 = ResourcesOrPoints.newResources(6, 3, 2, 2);
		ResourcesOrPoints startingResources3 = ResourcesOrPoints.newResources(7, 3, 2, 2);
		Player player1 = new Player("John", startingResources);
		Player player2 = new Player("Matt", startingResources2);
		Player player3 = new Player("Luke", startingResources3);
		List<Player> playersList = new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		playersList.add(player3);
		HarvestZone harvestZone = new HarvestZone(1, 1, playersList.size());
		harvestZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.BLACK));
		harvestZone.addPlayerHere(player2.getFamilyMembers().getfamilyMember(Colour.ORANGE));
		harvestZone.addPlayerHere(player3.getFamilyMembers().getfamilyMember(Colour.WHITE));
		assertTrue(harvestZone.playerAlreadyHere(player3.getFamilyMembers().getfamilyMember(Colour.ORANGE)));
	}
	
	@Test
	public void testCannotAddPlayerInHarvestZone() {
		boolean thrownIllegalAddPlayerHere = false;
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 3, 2, 2);
		ResourcesOrPoints startingResources2 = ResourcesOrPoints.newResources(6, 3, 2, 2);
		ResourcesOrPoints startingResources3 = ResourcesOrPoints.newResources(7, 3, 2, 2);
		Player player1 = new Player("John", startingResources);
		Player player2 = new Player("Matt", startingResources2);
		Player player3 = new Player("Luke", startingResources3);
		List<Player> playersList = new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		playersList.add(player3);
		HarvestZone harvestZone = new HarvestZone(1, 1, playersList.size());
		harvestZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.BLACK));
		try {
			harvestZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.ORANGE));
		} catch (IllegalArgumentException e) {
			thrownIllegalAddPlayerHere = true;
		}
		assertTrue(thrownIllegalAddPlayerHere);
	}

	@Test
	public void testAddNeutralMemberHere() {
		boolean thrownIllegalAddPlayerHere = false;
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 3, 2, 2);
		ResourcesOrPoints startingResources2 = ResourcesOrPoints.newResources(6, 3, 2, 2);
		Player player1 = new Player("Bob", startingResources);
		Player player2 = new Player("Raphael", startingResources2);
		List<Player> playersList = new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		HarvestZone harvestZone = new HarvestZone(1, 1, playersList.size());
		harvestZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.BLACK));
		try {
			harvestZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.NEUTRAL));
		} catch (IllegalArgumentException e) {
			thrownIllegalAddPlayerHere = true;
		}
		assertFalse(thrownIllegalAddPlayerHere);
	}
	
	@Test
	public void testNeutralNotAddPlayerHere() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 3, 2, 2);
		ResourcesOrPoints startingResources2 = ResourcesOrPoints.newResources(6, 3, 2, 2);
		Player player1 = new Player("Bob", startingResources);
		Player player2 = new Player("Raphael", startingResources2);
		List<Player> playersList = new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		HarvestZone harvestZone = new HarvestZone(1, 1, playersList.size());
		harvestZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.NEUTRAL));
		assertFalse(harvestZone.playerAlreadyHere(player1.getFamilyMembers().getfamilyMember(Colour.WHITE)));
	}
	
	@Test
	public void testAddPlayerHereAfterNeutral() {
		boolean thrownIllegalAddPlayerHere = false;
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 3, 2, 2);
		ResourcesOrPoints startingResources2 = ResourcesOrPoints.newResources(6, 3, 2, 2);
		Player player1 = new Player("Bob", startingResources);
		Player player2 = new Player("Raphael", startingResources2);
		List<Player> playersList = new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		HarvestZone harvestZone = new HarvestZone(1, 1, playersList.size());
		harvestZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.NEUTRAL));
		try {
			harvestZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.ORANGE));
		} catch (IllegalArgumentException e) {
			thrownIllegalAddPlayerHere = true;
		}
		assertTrue(harvestZone.playerAlreadyHere(player1.getFamilyMembers().getfamilyMember(Colour.BLACK)) && !thrownIllegalAddPlayerHere);
		
	}
	
	@Test
	public void testHarvestClear() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 3, 2, 2);
		ResourcesOrPoints startingResources2 = ResourcesOrPoints.newResources(6, 3, 2, 2);
		ResourcesOrPoints startingResources3 = ResourcesOrPoints.newResources(7, 3, 2, 2);
		Player player1 = new Player("John", startingResources);
		Player player2 = new Player("Matt", startingResources2);
		Player player3 = new Player("Luke", startingResources3);
		List<Player> playersList = new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		playersList.add(player3);
		HarvestZone harvestZone = new HarvestZone(1, 1, playersList.size());
		harvestZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.BLACK));
		harvestZone.addPlayerHere(player2.getFamilyMembers().getfamilyMember(Colour.ORANGE));
		harvestZone.addPlayerHere(player3.getFamilyMembers().getfamilyMember(Colour.WHITE));
		harvestZone.clear();
		assertTrue(harvestZone.getMultipleHarvest().getCounter() == 0 && harvestZone.getMultipleHarvest().isPositionFree() && harvestZone.getSingleHarvest().IsPositionFree());
	}
	
	@Test
	public void testPlayerHereAfterClear() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 3, 2, 2);
		ResourcesOrPoints startingResources2 = ResourcesOrPoints.newResources(6, 3, 2, 2);
		ResourcesOrPoints startingResources3 = ResourcesOrPoints.newResources(7, 3, 2, 2);
		Player player1 = new Player("John", startingResources);
		Player player2 = new Player("Matt", startingResources2);
		Player player3 = new Player("Luke", startingResources3);
		List<Player> playersList = new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		playersList.add(player3);
		HarvestZone harvestZone = new HarvestZone(1, 1, playersList.size());
		harvestZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.BLACK));
		harvestZone.addPlayerHere(player2.getFamilyMembers().getfamilyMember(Colour.ORANGE));
		harvestZone.addPlayerHere(player3.getFamilyMembers().getfamilyMember(Colour.WHITE));
		harvestZone.clear();
		assertFalse(harvestZone.playerAlreadyHere(player1.getFamilyMembers().getfamilyMember(Colour.BLACK)));
	}

}
