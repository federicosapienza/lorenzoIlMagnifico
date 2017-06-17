package it.polimi.ingsw.GC_26_controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.Game;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_readJson.BonusImplementation;
import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;
import it.polimi.ingsw.GC_26_readJson.CardsImplementation;
import it.polimi.ingsw.GC_26_readJson.TimerValueImplementation;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class ActionControllerTest {
	
	MainActionHandler handler;
	/*
	DevelopmentCard card;
	DevelopmentCardImplementation developmentCard;
	Action action1 = new Action(BoardZone.TERRITORYTOWER, 1, Colour.ORANGE, 0);
	Request request1 = new Request(PlayerStatus.PLAYING, "Player is playing", new CardDescriber(developmentCard));
	Player player1 = new Player("Lorenzo", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
	MainActionHandler handlers;
	ActionController actionCtrl = new ActionController(player1, handlers);
	*/
	@Test
	public void testCorrectConstructor(){
		Player player1 = new Player("Lorenzo", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
		MainActionHandler nullHandler = null;
		boolean thrownNullPointerException = false;
		try {
			ActionController ctrl = new ActionController(player1, nullHandler);
		} catch (NullPointerException e) {
			thrownNullPointerException = true;
		}
		assertTrue(thrownNullPointerException);
		
	}
	
	
	/*
	@Test
	public void testCorrectUpdate(){
		Player player1 = new Player("Lorenzo", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
		Player player2 = new Player("Lawrence", ResourcesOrPoints.newResourcesOrPoints(6, 3, 2, 2, 0, 0, 0, 0));
		Game game = new Game(new CardsImplementation(), new BonusImplementation(), new TimerValueImplementation());
		List<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		List<ResourcesOrPoints[]> resourcesOrPointsList = new BonusImplementation().getListOfResourcesOfPointsArray();
		Map<Integer, Integer> faithPointsTrack = new HashMap<>();
		GameElements gameElements = new GameElements(game, players, 2, resourcesOrPointsList, faithPointsTrack);
		MainActionHandler handlers = new MainActionHandler(gameElements);
		ActionController actionController = new ActionController(player2, handlers);
		Action nullAction = null;
		boolean thrownException = false;
		try {
			actionController.update(nullAction);
		} catch (NullPointerException e) {
			thrownException = true;
		}
		assertTrue(thrownException);
	}
	*/
	
	/*
	@Test
	public void testStatus(){
		Player player = new Player("Mike", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
		Player player2 = new Player("Michael", ResourcesOrPoints.newResourcesOrPoints(6, 3, 2, 2, 0, 0, 0, 0));
		Request request1 = new Request(PlayerStatus.PLAYING, "Mike is playing", null);
		player.setStatus(request1);
		assertNotNull(player.getStatus());
		assertEquals(PlayerStatus.PLAYING, player.getStatus());
		Action action1 = new Action(BoardZone.TERRITORYTOWER, 1, Colour.ORANGE, 0);
		List<Player> players = new ArrayList<>();
		players.add(player);
		players.add(player2);
		List<ResourcesOrPoints[]> resourcesOrPointsList = new ArrayList<>();
		Map<Integer, Integer> faithPointsTrack = new HashMap<>();
		Cards cards = new CardsImplementation();
		BonusInterface bonus = new BonusImplementation();
		TimerValuesInterface times = new TimerValueImplementation();
		Game game = new Game(cards, bonus, times);
		GameElements gameElements = new GameElements(game, players, 2, resourcesOrPointsList, faithPointsTrack);
		MainActionHandler handlers = new MainActionHandler(gameElements);
		ActionController actionController = new ActionController(player, handlers);
		actionController.update(action1);
		assertTrue(player.isPlayerActive());
		
	}
	
	
	
	@Test
	public void testExistence() {
		assertNotNull(actionCtrl);
	}
	*/
	
	
}
