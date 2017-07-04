package it.polimi.ingsw.GC_26_actionsHandlers;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.Game;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;
import it.polimi.ingsw.GC_26_readJson.ReadAll;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;


public class SecondActionHandlerTest {

	Cards cards;
	BonusInterface bonus;
	TimerValuesInterface times;
	List<ResourcesOrPoints[]> resourcesOrPointsList = new ArrayList<ResourcesOrPoints[]>();
	ReadAll readAll = new ReadAll();
	
	@Test
	public void testNotPossibleBecauseNotEnoughServants() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		game.addPlayer("David");
		game.addPlayer("Steph");
		Player player1 = game.getPlayers().get(0);
		game.initialiseGame();
		game.startGame();
		SecondActionHandler secondActionHandler = new SecondActionHandler(game.getGameElements(), new HarvestAndProductionHandler());
		Action action = new Action(BoardZone.VENTURETOWER, 3, Colour.BLACK, 5);
		player1.setTemporaryWarehouse();
		assertFalse(secondActionHandler.isPossible(player1, action));
	}
	
	@Test
	public void testNotPossibleBecauseDifferentZones() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		game.addPlayer("David");
		game.addPlayer("Steph");
		Player player1 = game.getPlayers().get(0);
		game.initialiseGame();
		game.startGame();
		SecondActionHandler secondActionHandler = new SecondActionHandler(game.getGameElements(), new HarvestAndProductionHandler());
		Action action = new Action(BoardZone.VENTURETOWER, 3, Colour.BLACK, 2);
		player1.setTemporaryWarehouse();
		player1.setTypeOfSecondaryAction(new Action(BoardZone.COUNCILPALACE, 1, Colour.NEUTRAL, 1));
		assertFalse(secondActionHandler.isPossible(player1, action));
	}
	
	@Test
	public void testNotPossibleBecauseActionNotInTower() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		game.addPlayer("David");
		game.addPlayer("Steph");
		Player player1 = game.getPlayers().get(0);
		game.initialiseGame();
		game.startGame();
		SecondActionHandler secondActionHandler = new SecondActionHandler(game.getGameElements(), new HarvestAndProductionHandler());
		Action action = new Action(BoardZone.COUNCILPALACE, 1, Colour.NEUTRAL, 1);
		player1.setTemporaryWarehouse();
		player1.setTypeOfSecondaryAction(new Action(null, 1, Colour.BLACK, 1));
		assertFalse(secondActionHandler.isPossible(player1, action));
	}
	
	@Test 
	public void testIsPossible() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		game.addPlayer("David");
		game.addPlayer("Steph");
		Player player1 = game.getPlayers().get(0);
		game.initialiseGame();
		game.startGame();
		SecondActionHandler secondActionHandler = new SecondActionHandler(game.getGameElements(), new HarvestAndProductionHandler());
		Action action = new Action(BoardZone.HARVEST, 1, Colour.NEUTRAL, 1);
		player1.setTemporaryWarehouse();
		player1.setTypeOfSecondaryAction(new Action(BoardZone.HARVEST, 1, Colour.BLACK, 1));
		assertTrue(secondActionHandler.isPossible(player1, action));
	}
}
