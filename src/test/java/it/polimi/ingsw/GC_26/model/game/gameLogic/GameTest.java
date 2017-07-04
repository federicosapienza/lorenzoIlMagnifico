package it.polimi.ingsw.GC_26.model.game.gameLogic;



import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26.jsonReader.BonusInterface;
import it.polimi.ingsw.GC_26.jsonReader.Cards;
import it.polimi.ingsw.GC_26.jsonReader.ReadAll;
import it.polimi.ingsw.GC_26.jsonReader.TimerValuesInterface;
import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.model.actionHandlers.FirstActionHandler;
import it.polimi.ingsw.GC_26.model.actionHandlers.HarvestAndProductionHandler;
import it.polimi.ingsw.GC_26.model.game.action.Action;
import it.polimi.ingsw.GC_26.model.game.gameComponents.board.BoardZone;
import it.polimi.ingsw.GC_26.model.game.gameComponents.dices.Colour;
import it.polimi.ingsw.GC_26.model.game.gameComponents.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.Warehouse;
import it.polimi.ingsw.GC_26.model.game.gameLogic.Game;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.model.player.PlayerStatus;

public class GameTest {

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
		Player player2 = game.addPlayer("Steph");
		List<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		assertTrue(game.getPlayers().get(0) == players.get(0) && game.getPlayers().get(1) == players.get(1));
	}
	
	@Test
	public void testCorrectWarehouses() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		game.addPlayer("David");
		game.addPlayer("Steph");
		Warehouse warehouse1 = game.getPlayers().get(0).getWarehouse();
		Warehouse warehouse2 = game.getPlayers().get(1).getWarehouse();
		assertTrue(warehouse1.getCoins() == 5 && warehouse1.getServants() == 3 && warehouse1.getWood() == 2&& 
				warehouse1.getStone() == 2 && warehouse2.getCoins() == 6 && warehouse2.getServants() == 3 &&
				warehouse2.getWood() == 2 && warehouse2.getStone() == 2);
	}
	
	@Test
	public void testWaitingPlayersBeforeStartingGame() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		Player player1 = game.addPlayer("David");
		Player player2 = game.addPlayer("Steph");
		assertTrue(player1.getStatus() == PlayerStatus.WAITINGHISTURN && player2.getStatus() == PlayerStatus.WAITINGHISTURN);
	}
	
	@Test
	public void testStartGame() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		game.addPlayer("David");
		game.addPlayer("Steph");
		game.initialiseGame();
		game.startGame();
		PlayerStatus status1 = game.getPlayers().get(0).getStatus();
		assertTrue(status1 == PlayerStatus.PLAYING && game.getPeriod() == 1);
	}
	
	@Test
	public void testNextStepPlayer() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		game.addPlayer("David");
		game.addPlayer("Steph");
		game.initialiseGame();
		game.startGame();
		PlayerStatus status1 = game.getPlayers().get(0).getStatus();
		game.nextStep();
		PlayerStatus status2 = game.getPlayers().get(1).getStatus();
		assertTrue(status2 == PlayerStatus.PLAYING && status1 == PlayerStatus.PLAYING && game.getPeriod() == 1);
	}
	
	@Test
	public void testCorrectPeriodChange() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		game.addPlayer("David");
		game.addPlayer("Steph");
		game.initialiseGame();
		game.startGame();
		game.nextStep();
		game.nextStep();
		game.nextStep();
		game.nextStep();
		game.nextStep();
		game.nextStep();
		game.nextStep();
		game.nextStep();
		assertTrue(game.getPeriod() == 2 && game.getRound() == 1);
	}
	
	
	@Test
	public void testChangeNextRoundOrder() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		game.addPlayer("David");
		game.addPlayer("Steph");
		Player player1 = game.getPlayers().get(0);
		Player player2 = game.getPlayers().get(1);
		game.initialiseGame();
		game.startGame();
		FamilyMember orangeMember1 = player1.getFamilyMembers().getfamilyMember(Colour.ORANGE);
		HarvestAndProductionHandler handler = new HarvestAndProductionHandler();
		FirstActionHandler firstActionHandler = new FirstActionHandler(game.getGameElements(), handler);
		Action action2 = new Action(BoardZone.COUNCILPALACE, 1, Colour.NEUTRAL, 1);
		Action action1 = new Action(BoardZone.COUNCILPALACE, 1, orangeMember1.getColour(), 0);
		firstActionHandler.perform(player2, action2);
		game.nextStep();
		firstActionHandler.perform(player1, action1);
		assertTrue(game.getGameElements().getNextRoundOrder().getNextRoundOrder().get(0) == "Steph" &&
				game.getGameElements().getNextRoundOrder().getNextRoundOrder().get(1) == "David");
	}
	
	
	@Test
	public void testSuspendedAndLastRound() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		game.addPlayer("David");
		game.addPlayer("Steph");
		game.initialiseGame();
		game.startGame();
		game.nextStep(); //playersPerformedActions = 1
		game.nextStep(); //playersPerformedActions = 2 --> round = 2, turn =1, playerPerformedActions = 0
		game.nextStep();
		game.nextStep();
		
		game.nextStep();
		game.nextStep();
		game.nextStep();
		game.nextStep();
		
		game.nextStep();
		game.nextStep();
		game.nextStep();
		game.nextStep();
		
		game.nextStep();
		game.nextStep();
		game.nextStep();
		game.nextStep();
		
		game.getPlayers().get(0).setStatus(new Request(PlayerStatus.SUSPENDED, "David suspended", null));
		game.nextStep();
		game.nextStep();
		game.addPlayerNoMoreSuspended(game.getPlayers().get(0));
		game.nextStep();
		assertTrue(game.getPlayers().get(0).getStatus() == PlayerStatus.SUSPENDED && game.getPeriod() == 3 && game.getRound() == 2);
		
	}
	
	@Test
	public void testCorrectPreviousStatusAfterStarting() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		game.addPlayer("David");
		game.addPlayer("Steph");
		game.initialiseGame();
		game.startGame();
		
		assertTrue(game.getPlayers().get(0).getPreviousStatus() == PlayerStatus.WAITINGHISTURN && 
				game.getPlayers().get(0).getStatus() == PlayerStatus.PLAYING &&
				game.getPlayers().get(1).getStatus() == PlayerStatus.WAITINGHISTURN);
		
	}
	
	@Test
	public void testCorrectPreviousStatusDuringGame() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		game.addPlayer("David");
		game.addPlayer("Steph");
		game.initialiseGame();
		game.startGame();
		game.nextStep(); //playersPerformedActions = 1
		game.nextStep(); //playersPerformedActions = 2 --> round = 2, turn =1, playerPerformedActions = 0
		game.nextStep();
		game.nextStep();
		
		game.nextStep();
		game.nextStep();
		game.nextStep();
		game.nextStep();
		
		game.getPlayers().get(0).setStatus(new Request(PlayerStatus.SUSPENDED, "David suspended", null));
		game.nextStep();
		assertEquals(PlayerStatus.PLAYING, game.getPlayers().get(0).getPreviousStatus());
		
	}
	
	
	/*
	@Test (expected = IllegalArgumentException.class)
	public void testCannotCreateA5PlayersGame() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		game.addPlayer("David");
		game.addPlayer("Steph");
		game.addPlayer("Flash");
		game.addPlayer("Hulk");
		game.addPlayer("Tony");
	
		game.initialiseGame();
	}
	*/

}

