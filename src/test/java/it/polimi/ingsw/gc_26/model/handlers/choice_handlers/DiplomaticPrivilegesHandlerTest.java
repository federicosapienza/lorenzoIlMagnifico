package it.polimi.ingsw.gc_26.model.handlers.choice_handlers;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.game.game_logic.GameParameters;
import it.polimi.ingsw.gc_26.model.handlers.choice_handlers.DiplomaticPrivilegesHandler;
import it.polimi.ingsw.gc_26.model.player.Player;
import it.polimi.ingsw.gc_26.utilities.exceptions.NotEnoughResourcesExceptions;

public class DiplomaticPrivilegesHandlerTest {

	DiplomaticPrivilegesHandler diplomaticPrivilegesHandler = new DiplomaticPrivilegesHandler(); 
	
	@Test
	public void isPossibleSecondIfFalseTest() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 0, 0, 2);
		Player player = new Player("Leon", startingResources);
		assertFalse(diplomaticPrivilegesHandler.isPossible(player, 6));
	}
	
	@Test
	public void isPossibleSecondIfTrueTest() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 0, 0, 2);
		Player player = new Player("Leon", startingResources);
		System.out.println(GameParameters.getDiplomaticPrivilegesTrades().length);
		assertTrue(diplomaticPrivilegesHandler.isPossible(player, 4));
	}

	@Test
	public void isPossibleThirdIfTestFalse() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 0, 0, 2);
		Player player = new Player("Leon", startingResources);
		diplomaticPrivilegesHandler.isPossible(player, 1);
		diplomaticPrivilegesHandler.setUsedToTrue(1);
		assertFalse(diplomaticPrivilegesHandler.isPossible(player, 1));
	}
	
	@Test
	public void isPossibleThirdIfTest() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 0, 0, 2);
		Player player = new Player("Leon", startingResources);
		diplomaticPrivilegesHandler.isPossible(player, 1);
		diplomaticPrivilegesHandler.setUsedToTrue(1);
		assertTrue(diplomaticPrivilegesHandler.isPossible(player, 2));
	}
	
	@Test
	public void performTestTrue(){
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 1);
		Player player = new Player("Leon", startingResources);
		ResourcesOrPoints resources = ResourcesOrPoints.newPoints(0, 0, 0, 1);
		player.getWarehouse().add(resources);
		diplomaticPrivilegesHandler.perform(player, 1);
		assertEquals(7, player.getWarehouse().getServants());
	}
	
	
	@Test(expected=NotEnoughResourcesExceptions.class)
	public void performTestFalse(){
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 1);
		Player player = new Player("Leon", startingResources);
		ResourcesOrPoints resources = ResourcesOrPoints.newPoints(0, 0, 0, 0);
		player.getWarehouse().add(resources);
		diplomaticPrivilegesHandler.perform(player, 1);
	}
	
	
	
	}

