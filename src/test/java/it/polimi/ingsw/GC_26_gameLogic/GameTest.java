package it.polimi.ingsw.GC_26_gameLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26_actionsHandlers.ActionPerformerHandler;
import it.polimi.ingsw.GC_26_board.CouncilPalace;
import it.polimi.ingsw.GC_26_board.MultiplePosition;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_readJson.BonusImplementation;
import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;
import it.polimi.ingsw.GC_26_readJson.CardsImplementation;
import it.polimi.ingsw.GC_26_readJson.ReadAll;
import it.polimi.ingsw.GC_26_readJson.TimerValueImplementation;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;

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
	public void testVaticanNextChangesPeriod() {
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
		game.vaticanReportNext();
		assertEquals(2, game.getPeriod());
	}
	
	/*
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
		FamilyMember blackMember2 = player2.getFamilyMembers().getfamilyMember(Colour.BLACK);
		ActionPerformerHandler actionPerformerHandler = new ActionPerformerHandler();
		CouncilPalace councilPalace = game.getGameElements().getBoard().getCouncilPalace(); 
		actionPerformerHandler.goToCouncilPalacePosition(councilPalace, blackMember2);
		game.getGameElements().getNextRoundOrder().nextRoundChanging(player2);
		game.nextStep();
		actionPerformerHandler.goToCouncilPalacePosition(councilPalace, orangeMember1);
		game.getGameElements().getNextRoundOrder().nextRoundChanging(player1);
		game.vaticanReportNext();
		assertEquals("Steph", game.getPlayers().get(0).getName());
	}
	*/
	

}
