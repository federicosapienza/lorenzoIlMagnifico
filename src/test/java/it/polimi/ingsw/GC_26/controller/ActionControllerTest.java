package it.polimi.ingsw.GC_26.controller;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26.controller.ActionController;
import it.polimi.ingsw.GC_26.jsonReader.BonusInterface;
import it.polimi.ingsw.GC_26.jsonReader.Cards;
import it.polimi.ingsw.GC_26.jsonReader.ReadAll;
import it.polimi.ingsw.GC_26.jsonReader.TimerValuesInterface;
import it.polimi.ingsw.GC_26.model.actionHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26.model.game.action.Action;
import it.polimi.ingsw.GC_26.model.game.gameComponents.board.BoardZone;
import it.polimi.ingsw.GC_26.model.game.gameComponents.dices.Colour;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.game.gameLogic.Game;
import it.polimi.ingsw.GC_26.model.player.Player;

public class ActionControllerTest {
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
		Player player1 = game.addPlayer("David");
		game.addPlayer("Steph");
		game.initialiseGame();
		game.startGame();
		MainActionHandler mainActionHandler = new MainActionHandler(game.getGameElements());
		ActionController actionController = new ActionController(game.getPlayers().get(0), mainActionHandler);
		Action action1 = new Action(BoardZone.COUNCILPALACE, 1, Colour.BLACK, 0);
		actionController.update(action1);
		assertTrue(player1.isPlayerActive());
	}
	
	
}
