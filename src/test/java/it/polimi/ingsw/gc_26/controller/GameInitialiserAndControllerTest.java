package it.polimi.ingsw.gc_26.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.gc_26.json_reader.BonusInterface;
import it.polimi.ingsw.gc_26.json_reader.Cards;
import it.polimi.ingsw.gc_26.json_reader.ReadAll;
import it.polimi.ingsw.gc_26.json_reader.TimerValuesInterface;
import it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.gc_26.view.ClientMainServerView;

public class GameInitialiserAndControllerTest {
	Cards cards;
	BonusInterface bonus;
	TimerValuesInterface timers;
	List<ResourcesOrPoints[]> resourcesOrPointsList = new ArrayList<ResourcesOrPoints[]>();
	ReadAll readAll = new ReadAll();

	@Test (expected = IllegalArgumentException.class)
	public void testEmptyGameException() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		timers = readAll.getTimes();
		GameInitialiserAndController gameInitialiserAndController = new GameInitialiserAndController(cards, bonus, timers);
		gameInitialiserAndController.initialiseGame();
	}

	@Test (expected = NullPointerException.class)
	public void testNullConnection() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		timers = readAll.getTimes();
		GameInitialiserAndController gameInitialiserAndController = new GameInitialiserAndController(cards, bonus, timers);
		ServerConnectionToClient serverConnectionToClient = null;
		ClientMainServerView clientMainServerView = new ClientMainServerView("David", serverConnectionToClient, timers);
		gameInitialiserAndController.addClient(clientMainServerView);
	}

}
