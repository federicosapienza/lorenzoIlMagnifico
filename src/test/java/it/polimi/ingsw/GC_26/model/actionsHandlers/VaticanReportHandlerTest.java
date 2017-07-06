package it.polimi.ingsw.GC_26.model.actionsHandlers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26.jsonReader.BonusInterface;
import it.polimi.ingsw.GC_26.jsonReader.Cards;
import it.polimi.ingsw.GC_26.jsonReader.ReadAll;
import it.polimi.ingsw.GC_26.jsonReader.TimerValuesInterface;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.game.gameLogic.Game;
import it.polimi.ingsw.GC_26.model.handlers.choiceHandlers.VaticanReportHandler;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.utilities.exceptions.IllegalActionException;

public class VaticanReportHandlerTest {

	Cards cards;
	BonusInterface bonus;
	TimerValuesInterface times;
	List<ResourcesOrPoints[]> resourcesOrPointsList = new ArrayList<ResourcesOrPoints[]>();
	ReadAll readAll = new ReadAll();
	
	@Test (expected = IllegalActionException.class)
	public void testPlayerFaithPointsException() {
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
		VaticanReportHandler vaticanReportHandler = new VaticanReportHandler(game.getGameElements());
		vaticanReportHandler.perform(player1, 1);
	}
	
	@Test
	public void testPlayerSupportingChurch() {
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
		player1.getWarehouse().add(ResourcesOrPoints.newPoints(0, 0, 9, 0));
		VaticanReportHandler vaticanReportHandler = new VaticanReportHandler(game.getGameElements());
		vaticanReportHandler.perform(player1, 1);
		assertTrue(player1.getWarehouse().getFaithPoints() == 0 && player1.getWarehouse().getVictoryPoints() == 13);
	}
	
	@Test
	public void testPlayerNotSupportingChurch() {
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
		player1.getWarehouse().add(ResourcesOrPoints.newPoints(0, 0, 9, 0));
		VaticanReportHandler vaticanReportHandler = new VaticanReportHandler(game.getGameElements());
		vaticanReportHandler.perform(player1, 0);
		assertTrue(player1.getWarehouse().getFaithPoints() == 9 && player1.getWarehouse().getVictoryPoints() == 0);
	}
}
