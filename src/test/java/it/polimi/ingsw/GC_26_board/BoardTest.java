package it.polimi.ingsw.GC_26_board;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26_actionsHandlers.ActionCheckerHandler;
import it.polimi.ingsw.GC_26_actionsHandlers.ActionPerformerHandler;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_readJson.ReadAll;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.dices.Dices;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class BoardTest {
	
	List<ResourcesOrPoints[]> resourcesOrPointsList = new ArrayList<ResourcesOrPoints[]>();
	
	ReadAll readAll = new ReadAll();
	
	@Test (expected = NullPointerException.class)
	public void testNullResourcesException() {
		List<ResourcesOrPoints[]> listOfResourcesOrPoints = null;
		Board board = new Board(2, listOfResourcesOrPoints);
		board.create(2, listOfResourcesOrPoints);
	}
	
	@Test
	public void testCorrectNumberOfPlayers() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board = new Board(2, resourcesOrPointsList);
		assertEquals(2, board.getNumberOfPlayers());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testIllegalNumberOfPlayersException() {
		
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board = new Board(5, resourcesOrPointsList);
		int numberOfPlayers = board.getNumberOfPlayers();
	}
	
	@Test
	public void testCorrectNumberOfHarvestAndProductionPositionsWith2Players() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board2 = new Board(2, resourcesOrPointsList);
		assertTrue(board2.getProductionZone().getPositionsActivated() == 1 && board2.getHarvestZone().getPositionsActivated() == 1);
	}
	
	@Test
	public void testCorrectNumberOfProductionPositionsWith3Or4Players() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board3 = new Board(3, resourcesOrPointsList);
		Board board4 = new Board(4, resourcesOrPointsList);
		assertTrue(board3.getProductionZone().getPositionsActivated() == 2 && board3.getHarvestZone().getPositionsActivated() == 2 &&
				board3.getProductionZone().getPositionsActivated() == board4.getProductionZone().getPositionsActivated() && 
				board3.getHarvestZone().getPositionsActivated() == board4.getHarvestZone().getPositionsActivated());
	}
	
	@Test
	public void testCorrectNumberOfMarketPositionsWith2Or3Players() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board2 = new Board(2, resourcesOrPointsList);
		Board board3 = new Board(3, resourcesOrPointsList);
		assertTrue(board2.getMarket().getPositionsActivated() == 2 && board3.getMarket().getPositionsActivated() == 2);
	}
	
	@Test
	public void testCorrectNumberOfMarketPositionsWith4Players() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board4 = new Board(4, resourcesOrPointsList);
		assertEquals(4, board4.getMarket().getPositionsActivated());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testIllegalGetTowerException() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board4 = new Board(4, resourcesOrPointsList);
		board4.getTower(BoardZone.HARVEST);
	}
	
	@Test
	public void testCorrectGetTowerFloorValue() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board4 = new Board(4, resourcesOrPointsList);
		assertEquals(7, board4.getTower(BoardZone.TERRITORYTOWER).getFloorValue(4));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testIllegalGetTowerFloorValue() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board4 = new Board(4, resourcesOrPointsList);
		int illegalFloorValue = board4.getTower(BoardZone.CHARACTERTOWER).getFloorValue(5);
	}
	
	@Test
	public void testCorrectGetTowerFloorNumber() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board4 = new Board(4, resourcesOrPointsList);
		assertEquals(3, board4.getTower(BoardZone.VENTURETOWER).getPosition(3).getFloor());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testIllegalGetTowerFloorNumber() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board4 = new Board(4, resourcesOrPointsList);
		TowerPosition illegalTowerPosition = board4.getTower(BoardZone.BUILDINGTOWER).getPosition(5);
	}
	
	@Test
	public void testCanPlayerGoToTower() {
		readAll.start();
		Dices dices = new Dices();
		dices.rollDices();
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		player.getFamilyMembers().setValues(dices);
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board4 = new Board(4, resourcesOrPointsList);
		assertTrue(board4.getTower(BoardZone.BUILDINGTOWER).canFamilyMemberGoToTheTower(player.getFamilyMembers().getfamilyMember(Colour.BLACK)));
	}
	
	@Test
	public void testTowersFreeInTheBeginning() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board4 = new Board(4, resourcesOrPointsList);
		assertTrue(board4.getTower(BoardZone.VENTURETOWER).isTheTowerFree());
	}
	
	@Test
	public void testCorrectTowersOccupiedAfterPlayerMove() {
		readAll.start();
		Dices dices = new Dices();
		dices.rollDices();
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		player.getFamilyMembers().setValues(dices);
		Player player2 = new Player("Bob", ResourcesOrPoints.newResources(6, 3, 2, 2));
		player2.getFamilyMembers().setValues(dices);
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board4 = new Board(4, resourcesOrPointsList);
		FamilyMember orangeMember1 = player.getFamilyMembers().getfamilyMember(Colour.ORANGE);
		FamilyMember orangeMember2 = player2.getFamilyMembers().getfamilyMember(Colour.ORANGE);
		FamilyMember neutralMember1 = player.getFamilyMembers().getfamilyMember(Colour.NEUTRAL);
		board4.getTower(BoardZone.CHARACTERTOWER).setPlayerInTheTower(orangeMember1);
		assertFalse(board4.getTower(BoardZone.CHARACTERTOWER).isTheTowerFree() && 
				board4.getTower(BoardZone.CHARACTERTOWER).canFamilyMemberGoToTheTower(orangeMember2) &&
				!board4.getTower(BoardZone.CHARACTERTOWER).canFamilyMemberGoToTheTower(neutralMember1));
	}
	
	@Test
	public void testCouncilPalaceBonusResourcesAndPoints() {
		readAll.start();
		Dices dices = new Dices();
		dices.rollDices();
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		player.getFamilyMembers().setValues(dices);
		Player player2 = new Player("Stephen", ResourcesOrPoints.newResources(6, 3, 2, 2));
		player2.getFamilyMembers().setValues(dices);
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board2 = new Board(2, resourcesOrPointsList);
		ActionPerformerHandler actionPerformerHandler = new ActionPerformerHandler();
		actionPerformerHandler.goToCouncilPalacePosition(board2.getCouncilPalace(), player.getFamilyMembers().getfamilyMember(Colour.BLACK));
		actionPerformerHandler.goToCouncilPalacePosition(board2.getCouncilPalace(), player2.getFamilyMembers().getfamilyMember(Colour.ORANGE));
		assertTrue(player.getWarehouse().getCoins() == 6 && player.getWarehouse().getCouncilPrivileges() == 1 &&
				board2.getCouncilPalace().getResourcesOrPointsInPosition().getCoins() == 1 &&
				board2.getCouncilPalace().getResourcesOrPointsInPosition().getCouncilPrivileges() == 1 &&
				board2.getCouncilPalace().getFamilyMember(0) == player.getFamilyMembers().getfamilyMember(Colour.BLACK) &&
				board2.getCouncilPalace().getFamilyMember(1) == player2.getFamilyMembers().getfamilyMember(Colour.ORANGE) &&
				board2.getCouncilPalace().getCounter() == 2);
	}
	
	@Test
	public void testNeutralMembersInHarvestZone() {
		readAll.start();
		Dices dices = new Dices();
		dices.rollDices();
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		player.getFamilyMembers().setValues(dices);
		Player player2 = new Player("Bob", ResourcesOrPoints.newResources(6, 3, 2, 2));
		player2.getFamilyMembers().setValues(dices);
		Player player3 = new Player("Dom", ResourcesOrPoints.newResources(7, 3, 2, 2));
		player3.getFamilyMembers().setValues(dices);
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board3 = new Board(3, resourcesOrPointsList);
		FamilyMember neutralMember1 = player.getFamilyMembers().getfamilyMember(Colour.NEUTRAL); 
		FamilyMember neutralMember2 = player2.getFamilyMembers().getfamilyMember(Colour.NEUTRAL); 
		FamilyMember neutralMember3 = player3.getFamilyMembers().getfamilyMember(Colour.NEUTRAL); 
		board3.getHarvestZone().addPlayerHere(player.getFamilyMembers().getfamilyMember(Colour.NEUTRAL));
		board3.getHarvestZone().addPlayerHere(player2.getFamilyMembers().getfamilyMember(Colour.NEUTRAL));
		board3.getHarvestZone().addPlayerHere(player3.getFamilyMembers().getfamilyMember(Colour.NEUTRAL));
		assertFalse(board3.getHarvestZone().playerAlreadyHere(neutralMember1) &&
				board3.getHarvestZone().playerAlreadyHere(neutralMember2) && board3.getHarvestZone().playerAlreadyHere(neutralMember3));
	}
	
	@Test
	public void testColouredMemberInHarvestZone() {
		readAll.start();
		Dices dices = new Dices();
		dices.rollDices();
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		player.getFamilyMembers().setValues(dices);
		Player player2 = new Player("Bob", ResourcesOrPoints.newResources(6, 3, 2, 2));
		player2.getFamilyMembers().setValues(dices);
		Player player3 = new Player("Dom", ResourcesOrPoints.newResources(7, 3, 2, 2));
		player3.getFamilyMembers().setValues(dices);
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board3 = new Board(3, resourcesOrPointsList);
		board3.getHarvestZone().addPlayerHere(player.getFamilyMembers().getfamilyMember(Colour.WHITE));
		board3.getHarvestZone().addPlayerHere(player2.getFamilyMembers().getfamilyMember(Colour.BLACK));
		assertTrue(board3.getHarvestZone().playerAlreadyHere(player.getFamilyMembers().getfamilyMember(Colour.ORANGE)) &&
				board3.getHarvestZone().playerAlreadyHere(player2.getFamilyMembers().getfamilyMember(Colour.WHITE)) &&
				!board3.getHarvestZone().playerAlreadyHere(player3.getFamilyMembers().getfamilyMember(Colour.BLACK)));
		
	}

	
	@Test
	public void testMarketGivingRightResourcesAndOccupiedCorrectly() {
		readAll.start();
		Dices dices = new Dices();
		dices.rollDices();
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		player.getFamilyMembers().setValues(dices);
		Player player2 = new Player("Claudia", ResourcesOrPoints.newResources(6, 3, 2, 2));
		player2.getFamilyMembers().setValues(dices);
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board4 = new Board(4, resourcesOrPointsList);
		ActionPerformerHandler marketHandler = new ActionPerformerHandler();
		marketHandler.goToMarketPositions(board4.getMarket().getPosition(1), player.getFamilyMembers().getfamilyMember(Colour.WHITE));
		marketHandler.goToMarketPositions(board4.getMarket().getPosition(2), player2.getFamilyMembers().getfamilyMember(Colour.ORANGE));
		assertTrue(player.getWarehouse().getCoins() == 10 && player2.getWarehouse().getServants() == 8 &&
				!board4.getMarket().getPosition(1).IsPositionFree() && !board4.getMarket().getPosition(2).IsPositionFree() &&
				board4.getMarket().getPosition(3).IsPositionFree() && board4.getMarket().getPosition(4).IsPositionFree());
	}
	
	@Test
	public void testCorrectCouncilPalaceValue() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board4 = new Board(4, resourcesOrPointsList);
		assertEquals(1, board4.getCouncilPalace().getValueOfPosition());
	}
	@Test
	public void testCanPlayerGoToCouncilPalace() {
		readAll.start();
		Dices dices = new Dices();
		dices.rollDices();
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		player.getFamilyMembers().setValues(dices);
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board4 = new Board(4, resourcesOrPointsList);
		ActionCheckerHandler councilPalaceChecker = new ActionCheckerHandler();
		ActionPerformerHandler councilPalacePerformer = new ActionPerformerHandler();
		councilPalacePerformer.goToCouncilPalacePosition(board4.getCouncilPalace(), player.getFamilyMembers().getfamilyMember(Colour.WHITE));
		Action action = new Action(BoardZone.COUNCILPALACE, 1, player.getFamilyMembers().getfamilyMember(Colour.BLACK).getColour(), 0);
		Action action2 = new Action(BoardZone.COUNCILPALACE, 2, player.getFamilyMembers().getfamilyMember(Colour.ORANGE).getColour(), 0);
		assertTrue(councilPalaceChecker.canMemberGoToPosition(board4.getCouncilPalace(), player, player.getFamilyMembers().getfamilyMember(Colour.BLACK), action) &&
				councilPalaceChecker.canMemberGoToPosition(board4.getCouncilPalace(), player, player.getFamilyMembers().getfamilyMember(Colour.ORANGE), action2));
	}
	
	@Test
	public void testCanPlayerGoToMarketInTheBeginning() {
		readAll.start();
		Dices dices = new Dices();
		dices.rollDices();
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		player.getFamilyMembers().setValues(dices);
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Colour blackMemberColour = player.getFamilyMembers().getfamilyMember(Colour.BLACK).getColour();
		FamilyMember blackMember = player.getFamilyMembers().getfamilyMember(Colour.BLACK);
		Board board4 = new Board(4, resourcesOrPointsList);
		ActionCheckerHandler marketChecker = new ActionCheckerHandler();
		Action action1 = new Action(BoardZone.MARKET, 1, blackMemberColour, 0);
		Action action4 = new Action(BoardZone.MARKET, 4, blackMemberColour, 0);
		assertTrue(marketChecker.canMemberGoToPosition(board4.getMarket().getPosition(1), player, blackMember, action1) &&
				marketChecker.canMemberGoToPosition(board4.getMarket().getPosition(4), player, blackMember, action4));
	}
	
	@Test
	public void testPlayerCannotGoToOccupiedMarketPosition() {
		readAll.start();
		Dices dices = new Dices();
		dices.rollDices();
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		Player player2 = new Player("Seth", ResourcesOrPoints.newResources(6, 3, 2, 2));
		player.getFamilyMembers().setValues(dices);
		player2.getFamilyMembers().setValues(dices);
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Colour blackMemberColour = player.getFamilyMembers().getfamilyMember(Colour.BLACK).getColour();
		FamilyMember blackMember = player.getFamilyMembers().getfamilyMember(Colour.BLACK);
		FamilyMember orangeMember = player.getFamilyMembers().getfamilyMember(Colour.ORANGE);
		FamilyMember orangeMember2 = player2.getFamilyMembers().getfamilyMember(Colour.ORANGE);
		Board board4 = new Board(4, resourcesOrPointsList);
		ActionCheckerHandler marketChecker = new ActionCheckerHandler();
		Action action1 = new Action(BoardZone.MARKET, 1, blackMemberColour, 0);
		ActionPerformerHandler marketPerformer = new ActionPerformerHandler();
		marketPerformer.goToMarketPositions(board4.getMarket().getPosition(1), blackMember);
		assertFalse(marketChecker.canMemberGoToPosition(board4.getMarket().getPosition(1), player, orangeMember, action1) &&
				marketChecker.canMemberGoToPosition(board4.getMarket().getPosition(1), player2, orangeMember2, action1));
	}
	
	@Test
	public void testCorrectMarketPositionsValues() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board4 = new Board(4, resourcesOrPointsList);
		assertTrue(board4.getMarket().getPosition(1).getValueOfPosition() == 1 &&
				board4.getMarket().getPosition(2).getValueOfPosition() == 1 &&
				board4.getMarket().getPosition(3).getValueOfPosition() == 1 &&
				board4.getMarket().getPosition(4).getValueOfPosition() == 1);
	}
	
	@Test
	public void testEndRound() {
		readAll.start();
		Dices dices = new Dices();
		dices.rollDices();
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		Player player2 = new Player("Seth", ResourcesOrPoints.newResources(6, 3, 2, 2));
		player.getFamilyMembers().setValues(dices);
		player2.getFamilyMembers().setValues(dices);
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		FamilyMember blackMember = player.getFamilyMembers().getfamilyMember(Colour.BLACK);
		FamilyMember orangeMember2 = player2.getFamilyMembers().getfamilyMember(Colour.ORANGE);
		Board board4 = new Board(4, resourcesOrPointsList);
		ActionPerformerHandler actionPerformerHandler = new ActionPerformerHandler();
		actionPerformerHandler.goToMarketPositions(board4.getMarket().getPosition(1), blackMember);
		actionPerformerHandler.goToMarketPositions(board4.getMarket().getPosition(2), orangeMember2);
		boolean marketPosition1Occupied = board4.getMarket().getPosition(1).IsPositionFree();
		boolean marketPosition2Occupied = board4.getMarket().getPosition(2).IsPositionFree();
		board4.endRound();
		assertTrue(board4.getMarket().getPosition(1).IsPositionFree() && board4.getMarket().getPosition(2).IsPositionFree() &&
				!marketPosition1Occupied && !marketPosition2Occupied);
	}
	
}
