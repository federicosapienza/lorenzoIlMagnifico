package it.polimi.ingsw.GC_26_board;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class ProductionZoneTest {

	@Test
	public void testCorrectNumberOfProductionSpaces() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 3, 2, 2);
		ResourcesOrPoints startingResources2 = ResourcesOrPoints.newResources(6, 3, 2, 2);
		Player player1 = new Player("Bob", startingResources);
		Player player2 = new Player("Raphael", startingResources2);
		List<Player> playersList = new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		ProductionZone productionZone = new ProductionZone(1, 1, playersList.size());
		assertEquals(1, productionZone.getPositionsActivated());
	}
	
	@Test
	public void testAddPlayerHere() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 3, 2, 2);
		ResourcesOrPoints startingResources2 = ResourcesOrPoints.newResources(6, 3, 2, 2);
		Player player1 = new Player("Bob", startingResources);
		Player player2 = new Player("Raphael", startingResources2);
		List<Player> playersList = new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		ProductionZone productionZone = new ProductionZone(1, 1, playersList.size());
		productionZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.BLACK));
		assertTrue(productionZone.playerAlreadyHere(player1.getFamilyMembers().getfamilyMember(Colour.WHITE)));
	}
	
	@Test
	public void testCannotAddPlayerHere() {
		boolean thrownIllegalAddPlayerHere = false;
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 3, 2, 2);
		ResourcesOrPoints startingResources2 = ResourcesOrPoints.newResources(6, 3, 2, 2);
		Player player1 = new Player("Bob", startingResources);
		Player player2 = new Player("Raphael", startingResources2);
		List<Player> playersList = new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		ProductionZone productionZone = new ProductionZone(1, 1, playersList.size());
		productionZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.BLACK));
		try {
			productionZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.ORANGE));
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
		ProductionZone productionZone = new ProductionZone(1, 1, playersList.size());
		productionZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.BLACK));
		try {
			productionZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.NEUTRAL));
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
		ProductionZone productionZone = new ProductionZone(1, 1, playersList.size());
		productionZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.NEUTRAL));
		assertFalse(productionZone.playerAlreadyHere(player1.getFamilyMembers().getfamilyMember(Colour.WHITE)));
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
		ProductionZone productionZone = new ProductionZone(1, 1, playersList.size());
		productionZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.NEUTRAL));
		try {
			productionZone.addPlayerHere(player1.getFamilyMembers().getfamilyMember(Colour.ORANGE));
		} catch (IllegalArgumentException e) {
			thrownIllegalAddPlayerHere = true;
		}
		assertTrue(productionZone.playerAlreadyHere(player1.getFamilyMembers().getfamilyMember(Colour.BLACK)) && !thrownIllegalAddPlayerHere);
		
	}
}
