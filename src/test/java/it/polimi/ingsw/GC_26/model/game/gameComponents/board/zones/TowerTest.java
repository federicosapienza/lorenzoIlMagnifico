package it.polimi.ingsw.GC_26.model.game.gameComponents.board.zones;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26.model.game.game_components.board.zones.Tower;
import it.polimi.ingsw.GC_26.model.game.game_components.dices.Colour;
import it.polimi.ingsw.GC_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;

public class TowerTest {

	ResourcesOrPoints[] resourcesOrPointsArray = new ResourcesOrPoints[4];
	ResourcesOrPoints res1 = ResourcesOrPoints.newResources(0, 0, 0, 0);
	ResourcesOrPoints res2 = ResourcesOrPoints.newResources(0, 0, 0, 0);
	ResourcesOrPoints res3 = ResourcesOrPoints.newResources(0, 0, 1, 0);
	ResourcesOrPoints res4 = ResourcesOrPoints.newResources(0, 0, 2, 0);
	
	@Test
	public void testCanFamilyMemberGoToTower() {
		resourcesOrPointsArray[0] = res1;
		resourcesOrPointsArray[1] = res2;
		resourcesOrPointsArray[2] = res3;
		resourcesOrPointsArray[3] = res4;
		Tower tower = new Tower(resourcesOrPointsArray);
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		assertTrue(tower.canFamilyMemberGoToTheTower(player.getFamilyMembers().getfamilyMember(Colour.BLACK)));
	}

	@Test
	public void testIsTowerOccupiedAfterPlayerSetting() {
		resourcesOrPointsArray[0] = res1;
		resourcesOrPointsArray[1] = res2;
		resourcesOrPointsArray[2] = res3;
		resourcesOrPointsArray[3] = res4;
		Tower tower = new Tower(resourcesOrPointsArray);
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		tower.setPlayerInTheTower(player.getFamilyMembers().getfamilyMember(Colour.ORANGE));
		assertFalse(tower.isTheTowerFree());
	}
}
