package it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26.jsonReader.BonusInterface;
import it.polimi.ingsw.GC_26.jsonReader.Cards;
import it.polimi.ingsw.GC_26.jsonReader.ReadAll;
import it.polimi.ingsw.GC_26.jsonReader.TimerValuesInterface;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.Warehouse;
import it.polimi.ingsw.GC_26.model.game.gameLogic.Game;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.utilities.NotEnoughResourcesExceptions;

public class WarehouseTest {
	Cards cards;
	BonusInterface bonus;
	TimerValuesInterface times;
	List<ResourcesOrPoints[]> resourcesOrPointsList = new ArrayList<ResourcesOrPoints[]>();
	ReadAll readAll = new ReadAll();
	
	@Test
	public void testCorrectPlayersList() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		game.addPlayer("David");
		game.addPlayer("Steph");
		game.addPlayer("Daniel");
		Player player4 = game.addPlayer("Emma");
		game.initialiseGame();
		game.startGame();
		assertEquals(8, player4.getWarehouse().getCoins());
	}

	@Test (expected = NullPointerException.class)
	public void testNullPlayerException() {
		Player nullPlayer = null;
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0);
		Warehouse warehouse = new Warehouse(nullPlayer, startingResources);
		warehouse.notifyAll();
		
	}
	
	
	
	@Test (expected = NullPointerException.class)
	public void testNullWarehouse() {
		
		Warehouse other = null;
		Warehouse warehouse = new Warehouse(other);
		warehouse.notifyAll();
	}
	
	@Test
	public void testResourcesNotEnough() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0);
		Player player = new Player("Daphne", startingResources);
		Warehouse warehouse = new Warehouse(player, startingResources);
		ResourcesOrPoints res = ResourcesOrPoints.newResourcesOrPoints(4, 1, 1, 7, 1, 1, 1, 1);
		assertFalse(warehouse.areResourcesEnough(res));
	}
	
	
	
	@Test
	public void testSpendResources() {
		ResourcesOrPoints resources = ResourcesOrPoints.newResourcesOrPoints(1, 1, 1, 1, 0, 0, 0, 0);
		ResourcesOrPoints res1 = ResourcesOrPoints.newResourcesOrPoints(6, 3, 2, 2, 0, 0, 0, 0);
		Player player = new Player("Fred", res1);
		Warehouse warehouse = new Warehouse(player, res1);
		warehouse.spendResources(resources);
		assertEquals(5, warehouse.getCoins());
		
	}
	
	@Test (expected = NotEnoughResourcesExceptions.class)
	public void testIllegalSpendResources() {

		ResourcesOrPoints resources = ResourcesOrPoints.newResourcesOrPoints(7, 1, 1, 1, 0, 0, 0, 0);
		ResourcesOrPoints res1 = ResourcesOrPoints.newResourcesOrPoints(6, 3, 2, 2, 0, 0, 0, 0);
		Player player = new Player("Ben", res1);
		Warehouse illegalWarehouse = new Warehouse(player, res1);
		illegalWarehouse.spendResources(resources);
		
	}
}
