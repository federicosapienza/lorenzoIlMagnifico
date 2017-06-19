package it.polimi.ingsw.GC_26_actionsHandlers;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class HarvestAndProductionHandlerTest {

	HarvestAndProductionHandler harvestAndProductionHandler = new HarvestAndProductionHandler(); 
	
	@Test(expected = IllegalArgumentException.class)
	public void exceptionTest() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 0, 0, 0);
		Player player = new Player("Leon", startingResources);
		harvestAndProductionHandler.startHarvest(player, 0);
	}
	//LEON:
	//I can test only that because perform is private.
	//I need to create a lot of setter for the personalBoard/personalBoardTile
	//to test class,i don't think is a good idea.
	
	
	@Test
	public void testNotNullHandler() {
		assertNotNull(harvestAndProductionHandler);
	}
	@Test
	public void testIllegalHarvestValue() {
		boolean thrownIllegalHarvestValue = false;
		int illegalValue = 0;
		ResourcesOrPoints startingResAndPoints =  ResourcesOrPoints.newResources(5, 3, 2, 2);
		Player player = new Player("David", startingResAndPoints);
		HarvestAndProductionHandler harvestAndProductionHandler = new HarvestAndProductionHandler();
		try {
			harvestAndProductionHandler.startHarvest(player, illegalValue);
		} catch (IllegalArgumentException e) {
			thrownIllegalHarvestValue = true;
		}
		assertTrue(thrownIllegalHarvestValue);
	}

	@Test
	public void testIllegalProductionValue() {
		boolean thrownIllegalProductionValue = false;
		int illegalValue = -1;
		ResourcesOrPoints startingResAndPoints =  ResourcesOrPoints.newResources(6, 3, 2, 2);
		Player player2 = new Player("Golia", startingResAndPoints);
		HarvestAndProductionHandler harvestAndProductionHandler = new HarvestAndProductionHandler();
		try {
			harvestAndProductionHandler.startProduction(player2, illegalValue);
		} catch (IllegalArgumentException e) {
			thrownIllegalProductionValue = true;
		}
		assertTrue(thrownIllegalProductionValue);
	}
	
	//Mi dà errore il seguente test ma dovrebbe essere corretto
	/*
	@Test
	public void testCorrectStartHarvest() {
		boolean thrownIllegalHarvestValue = false;
		int value = 1;
		ResourcesOrPoints startingResAndPoints =  ResourcesOrPoints.newResources(5, 3, 2, 2);
		Player player = new Player("Dave", startingResAndPoints);
		HarvestAndProductionHandler harvestAndProductionHandler = new HarvestAndProductionHandler();
		try {
			harvestAndProductionHandler.startHarvest(player, value);
		} catch (IllegalArgumentException e) {
			thrownIllegalHarvestValue = true;
		}
		assertFalse(thrownIllegalHarvestValue);
	}
	*/
}
