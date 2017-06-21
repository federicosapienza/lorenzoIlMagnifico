package it.polimi.ingsw.GC_26_board;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
		Player player2 = new Player("Bob", ResourcesOrPoints.newResources(6, 3, 2, 2));
		player2.getFamilyMembers().setValues(dices);
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		Board board2 = new Board(2, resourcesOrPointsList);
		board2.getCouncilPalace().addBonusResources(player);
		assertTrue(player.getWarehouse().getCoins() == 6 && player.getWarehouse().getCouncilPrivileges() == 1 &&
				board2.getCouncilPalace().getResourcesOrPointsInPosition().getCoins() == 1 &&
				board2.getCouncilPalace().getResourcesOrPointsInPosition().getCouncilPrivileges() == 1);
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

}
