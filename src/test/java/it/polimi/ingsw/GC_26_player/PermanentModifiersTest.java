package it.polimi.ingsw.GC_26_player;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class PermanentModifiersTest {

	Player player1 = new Player("Bill", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
	PermanentModifiers permMod =  new PermanentModifiers(player1);
	ResourcesOrPoints resourcesOrPoints;
	
	@Test
	public void testExistence() {
		assertNotNull(permMod);
	}
	@Test
	public void testResourceMalus() {
		assertFalse(permMod.IsresourcesMalusOn());
	}
	
	@Test
	public void testMalusOn() {
		resourcesOrPoints.newResourcesOrPoints(1, 0, 0, 0, 0, 0, 0, 0);
		permMod.setMalus(ResourcesOrPoints.newResourcesOrPoints(1, 0, 0, 0, 0, 0, 0, 0));
		assertTrue(permMod.IsresourcesMalusOn());
		assertEquals(resourcesOrPoints, permMod.getResourcesAfterMalus(ResourcesOrPoints.newResourcesOrPoints(2, 0, 0, 0, 0, 0, 0, 0)));
		
	}
	
	@Test
	public void testMilitaryPointsRequirement() {
		assertFalse(permMod.isMilitaryPointRequirementNotNeeded());
		permMod.setMilitaryPointRequirementNotNeeded();
		assertTrue(permMod.isMilitaryPointRequirementNotNeeded());
		
	}
	
	

}
