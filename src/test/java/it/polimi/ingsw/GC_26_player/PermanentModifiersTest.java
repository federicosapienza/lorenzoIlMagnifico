package it.polimi.ingsw.GC_26_player;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class PermanentModifiersTest {

	Player player1 = new Player("Bill", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
	Player nullPlayer = null;
	PermanentModifiers permMod = new PermanentModifiers(player1);
	ResourcesOrPoints resourcesOrPoints;
	
	@Test
	public void testNullPlayerException() {
		boolean thrownNullPlayerExcep = false;
		try {
			PermanentModifiers permanentModifiers = new PermanentModifiers(nullPlayer);
		} catch (NullPointerException e) {
			thrownNullPlayerExcep = true;
		}
		assertTrue(thrownNullPlayerExcep);
	}
	
	@Test
	public void testNotNull() {
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
		
	}
	
	@Test
	public void testMilitaryPointsRequirement() {
		assertFalse(permMod.isMilitaryPointRequirementNotNeeded());
		permMod.setMilitaryPointRequirementNotNeeded();
		assertTrue(permMod.isMilitaryPointRequirementNotNeeded());
		
	}
	
	@Test
	public void testBonusRevoked(){
		assertFalse(permMod.isTowerBonusRevokedOn());
		permMod.setBonusRevokedOn();
		assertTrue(permMod.isTowerBonusRevokedOn());
		
	}
	
	@Test
	public void testVictoryPointsReducer(){
		assertEquals(1, permMod.getVictoryPointsReducer());
		permMod.setVictoryPointsReducer(3);
		assertEquals(3, permMod.getVictoryPointsReducer());
	}
	
	@Test
	public void testThreeDicesChange(){
		assertFalse(permMod.isThreeDicesChangeOn());
		assertEquals(0, permMod.getValue3dicesChanged());
	}
	
	@Test
	public void testAdditionalVP() {
		assertEquals(0, permMod.getAdditionalVP());
		int value = 2;
		permMod.gainAdditionalVP(value);
		assertEquals(2, permMod.getAdditionalVP());
	}
	
	@Test
	public void testDiscounts() {
		//Map<BoardZone, ResourcesOrPoints> discounts= new HashMap<>();
		BoardZone zone = BoardZone.BUILDINGTOWER;
		ResourcesOrPoints res = ResourcesOrPoints.newResources(1, 1, 1, 1);
		assertFalse(permMod.IsTherediscountOnResources(zone));
		assertNull(permMod.getDiscount(zone));
		permMod.addDiscount(zone, res);
		assertNotNull(permMod.getDiscount(zone));
		assertTrue(permMod.IsTherediscountOnResources(zone));
		assertEquals(res, permMod.getDiscount(zone));
	}

}
