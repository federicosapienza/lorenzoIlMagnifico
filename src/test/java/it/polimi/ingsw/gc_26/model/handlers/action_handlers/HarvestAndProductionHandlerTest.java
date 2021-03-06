package it.polimi.ingsw.gc_26.model.handlers.action_handlers;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.handlers.action_handlers.HarvestAndProductionHandler;
import it.polimi.ingsw.gc_26.model.player.Player;

public class HarvestAndProductionHandlerTest {

	HarvestAndProductionHandler harvestAndProductionHandler = new HarvestAndProductionHandler(); 
	
	
	//LEON:
	//I can test only that because perform is private.
	//I need to create a lot of setter for the personalBoard/personalBoardTile
	//to test class,i don't think is a good idea.
	
	@Test
	public void testNoPerformWhenValueSmallerThanOne() {
		Player player = new Player("Steve", ResourcesOrPoints.newResources(5, 3, 2, 2));
		int value = 0;
		HarvestAndProductionHandler handler = new HarvestAndProductionHandler();
		handler.startProduction(player, value);
		handler.startHarvest(player, value);
		assertTrue(player.getWarehouse().getCoins() == 5 && player.getWarehouse().getServants() == 3 && player.getWarehouse().getWood() == 2 && player.getWarehouse().getStone() == 2);
	}
	
}
