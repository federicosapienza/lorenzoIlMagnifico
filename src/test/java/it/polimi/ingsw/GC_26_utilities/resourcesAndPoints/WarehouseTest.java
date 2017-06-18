package it.polimi.ingsw.GC_26_utilities.resourcesAndPoints;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_player.Player;

public class WarehouseTest {

	@Test
	public void testNullPlayerException() {
		boolean thrownNullPlayerExcep = false;
		Player nullPlayer = null;
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0);
		try {
			Warehouse warehouse = new Warehouse(nullPlayer, startingResources);
		} catch (NullPointerException e) {
			thrownNullPlayerExcep = true;
		}
		assertTrue(thrownNullPlayerExcep);
		
	}
	
	@Test
	public void testCorrectGetStatus() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0);
		Player player = new Player("Jess", startingResources);
		Warehouse warehouse = new Warehouse(player, startingResources);
		assertEquals("Jess", warehouse.getPlayerName());
		assertEquals(5, warehouse.getCoins());
		assertEquals(3, warehouse.getServants());
		assertEquals(2, warehouse.getStone());
		assertEquals(2, warehouse.getWood());
		assertEquals(0, warehouse.getVictoryPoints());
		assertEquals(0, warehouse.getFaithPoints());
		assertEquals(0, warehouse.getMilitaryPoints());
		assertEquals(0, warehouse.getCouncilPrivileges());
		
	}
	
	@Test
	public void testNullWarehouse() {
		boolean thrownNullExcep = false;
		Warehouse other = null;
		try {
			Warehouse warehouse = new Warehouse(other);
		} catch (NullPointerException e) {
			thrownNullExcep = true;
		}
		assertTrue(thrownNullExcep);
	}
	
	@Test
	public void testSameWarehouse() {
		ResourcesOrPoints res1 = ResourcesOrPoints.newResourcesOrPoints(6, 3, 2, 2, 0, 0, 0, 0);
		Player player = new Player("James", res1);
		Warehouse warehouse1 = new Warehouse(player, res1);
		Warehouse warehouse2 = new Warehouse(warehouse1);
		assertEquals(warehouse1.getPlayerName(), warehouse2.getPlayerName());
		assertEquals(warehouse1.getFaithPoints(), warehouse2.getFaithPoints());
		warehouse1.resetFaithPoints();
		assertEquals(0, warehouse1.getFaithPoints());
		
	}
	
	@Test
	public void testSpendResources() {
		ResourcesOrPoints resources = ResourcesOrPoints.newResourcesOrPoints(1, 1, 1, 1, 0, 0, 0, 0);
		ResourcesOrPoints res1 = ResourcesOrPoints.newResourcesOrPoints(6, 3, 2, 2, 0, 0, 0, 0);
		Player player = new Player("Fred", res1);
		Warehouse warehouse = new Warehouse(player, res1);
		warehouse.spendResources(resources);
		assertEquals(5, warehouse.getCoins());
		assertEquals(2, warehouse.getServants());
		assertEquals(1, warehouse.getWood());
	}
	
	@Test
	public void testIllegalSpendResources() {
		boolean thrownIllegalArgExcep = false;
		ResourcesOrPoints resources = ResourcesOrPoints.newResourcesOrPoints(7, 1, 1, 1, 0, 0, 0, 0);
		ResourcesOrPoints res1 = ResourcesOrPoints.newResourcesOrPoints(6, 3, 2, 2, 0, 0, 0, 0);
		Player player = new Player("Ben", res1);
		Warehouse illegalWarehouse = new Warehouse(player, res1);
		try {
			illegalWarehouse.spendResources(resources);
			
		} catch (IllegalArgumentException e) {
			thrownIllegalArgExcep = true;
		}
		assertTrue(thrownIllegalArgExcep);
	}
}
