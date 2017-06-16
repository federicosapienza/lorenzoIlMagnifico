package it.polimi.ingsw.GC_26_utilities.resourcesAndPoints;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResourcesOrPointsTest {

	ResourcesOrPoints resPoints1;
	ResourcesOrPoints resPoints2;
	Points Points1 = new Points(3, 4, 5, 6);
	Resources resources1= new Resources(1, 2, 2, 2);
	Points points2= new Points(2, 3, 1, 3);
	Resources resources2 = new Resources(5, 3, 2, 1);
	@Test
	public void test() {
		ResourcesOrPoints res1 = resPoints1.newResourcesOrPoints(1, 2, 3, 4, 5, 6, 7, 8);
		ResourcesOrPoints res2 = resPoints2.newResourcesOrPoints(3, 4, 2, 3, 2, 1, 1, 3);
		assertEquals(4, res1.sum(res1, res2).getCoins());
		assertEquals(2, res2.subtract(res2, res1).getCoins());
		assertEquals(11, res1.sum(res1, res2).getCouncilPrivileges());
	}

}
