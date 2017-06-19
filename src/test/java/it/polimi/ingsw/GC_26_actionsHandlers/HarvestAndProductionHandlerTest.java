package it.polimi.ingsw.GC_26_actionsHandlers;

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
	
	//I can test only that because perform is private.
	//I need to create a lot of setter for the personalBoard/personalBoardTile
	//to test class,i don't think is a good idea.
	


}
