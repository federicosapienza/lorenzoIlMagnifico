package it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points.ResourcesOrPoints;

public class ResourcesOrPointsTest {

	@Test
	public void testCorrectSum() {
		ResourcesOrPoints res1 = ResourcesOrPoints.newResourcesOrPoints(1, 2, 3, 4, 5, 6, 7, 8);
		ResourcesOrPoints res2 = ResourcesOrPoints.newResourcesOrPoints(3, 4, 2, 3, 2, 1, 1, 3);
		assertTrue(ResourcesOrPoints.sum(res1, res2).getCoins() == 4 && ResourcesOrPoints.sum(res1, res2).getCouncilPrivileges() == 11);
	}

	@Test
	public void testCorrectSubtraction() {
		ResourcesOrPoints res1 = ResourcesOrPoints.newResourcesOrPoints(20, 19, 18, 17, 16, 15, 14, 13);
		ResourcesOrPoints res2 = ResourcesOrPoints.newResourcesOrPoints(1, 2, 3, 4, 5, 6, 7, 8);
		assertTrue(ResourcesOrPoints.subtract(res1, res2).getServants() == 17 && ResourcesOrPoints.subtract(res1, res2).getWood() == 15);
	}
}
