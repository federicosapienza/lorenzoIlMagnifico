package it.polimi.ingsw.GC_26_player;

import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.List;


import org.junit.Test;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_gameLogic.Game;
import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;
import it.polimi.ingsw.GC_26_readJson.ReadAll;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class PermanentModifiersTest {

	Player player1 = new Player("Bill", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
	Player nullPlayer = null;
	PermanentModifiers permMod = new PermanentModifiers(player1);
	
	Cards cards;
	BonusInterface bonus;
	TimerValuesInterface times;
	List<ResourcesOrPoints[]> resourcesOrPointsList = new ArrayList<ResourcesOrPoints[]>();
	ReadAll readAll = new ReadAll();
	
	@Test (expected = NullPointerException.class)
	public void testNullPlayerException() {
		
		PermanentModifiers permanentModifiers = new PermanentModifiers(nullPlayer);
		permanentModifiers.isResourcesMalusOn();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void isThereADiscountException() {
		BoardZone illegalZone = BoardZone.valueOf("CouncilPalaces");
		permMod.IsTherediscountOnResources(illegalZone);
	}
	
	@Test
	public void testNoPermanentEffectsInTheBeginning() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		Player player1 = game.addPlayer("David");
		Player player2 = game.addPlayer("Steph");
		game.initialiseGame();
		game.startGame();
		assertFalse(player1.getPermanentModifiers().isDoubleEarningOn() && player1.getPermanentModifiers().isDoubleServantsOn() &&
				player1.getPermanentModifiers().isGoingInOccupiedPositionsAllowed() && player1.getPermanentModifiers().isMilitaryPointRequirementNotNeeded() &&
				player1.getPermanentModifiers().isResourcesMalusOn() && player1.getPermanentModifiers().IsTherediscountOnResources(BoardZone.VENTURETOWER) &&
				player1.getPermanentModifiers().isThreeDicesChangeOn() && player1.getPermanentModifiers().isTowerBonusRevokedOn() &&
				player1.getPermanentModifiers().isTowerOccupiedMalusDisabled() &&
				player2.getPermanentModifiers().isDoubleEarningOn() && player2.getPermanentModifiers().isDoubleServantsOn() &&
				player2.getPermanentModifiers().isGoingInOccupiedPositionsAllowed() && player2.getPermanentModifiers().isMilitaryPointRequirementNotNeeded() &&
				player2.getPermanentModifiers().isResourcesMalusOn() && player2.getPermanentModifiers().IsTherediscountOnResources(BoardZone.VENTURETOWER) &&
				player2.getPermanentModifiers().isThreeDicesChangeOn() && player2.getPermanentModifiers().isTowerBonusRevokedOn() &&
				player2.getPermanentModifiers().isTowerOccupiedMalusDisabled());
	}

	@Test
	public void testAddFirstDiscountInTheSelectedZone() {
		permMod.addDiscount(BoardZone.VENTURETOWER, ResourcesOrPoints.newResources(1, 2, 3, 4));
		assertTrue(permMod.IsTherediscountOnResources(BoardZone.VENTURETOWER) && 
				!permMod.IsTherediscountOnResources(BoardZone.CHARACTERTOWER) &&
				permMod.getDiscount(BoardZone.VENTURETOWER).getCoins() == 1 && 
				permMod.getDiscount(BoardZone.VENTURETOWER).getStone() == 4);
	}
	
	@Test
	public void testAddFirstDiscountToEveryTower() {
		permMod.addDiscount(null, ResourcesOrPoints.newResourcesOrPoints(1, 2, 3, 4, 5, 6, 7, 8));
		assertTrue(permMod.IsTherediscountOnResources(BoardZone.VENTURETOWER) && 
				permMod.IsTherediscountOnResources(BoardZone.TERRITORYTOWER) &&
				permMod.IsTherediscountOnResources(BoardZone.CHARACTERTOWER) &&
				permMod.IsTherediscountOnResources(BoardZone.BUILDINGTOWER) &&
				!permMod.IsTherediscountOnResources(BoardZone.HARVEST) &&
				!permMod.IsTherediscountOnResources(BoardZone.PRODUCTION) &&
				!permMod.IsTherediscountOnResources(BoardZone.MARKET) &&
				!permMod.IsTherediscountOnResources(BoardZone.COUNCILPALACE) &&
				permMod.getDiscount(BoardZone.VENTURETOWER).getCouncilPrivileges() == 8 &&
				permMod.getDiscount(BoardZone.TERRITORYTOWER).getVictoryPoints() == 5 &&
				permMod.getDiscount(BoardZone.CHARACTERTOWER).getServants() == 2 &&
				permMod.getDiscount(BoardZone.BUILDINGTOWER).getWood() == 3 &&
				permMod.getDiscount(BoardZone.TERRITORYTOWER) == permMod.getDiscount(BoardZone.CHARACTERTOWER));
	}
	
	@Test
	public void testAddDiscountToDiscountedZone() {
		permMod.addDiscount(BoardZone.BUILDINGTOWER, ResourcesOrPoints.newResourcesOrPoints(1, 2, 3, 4, 5, 6, 7, 8));
		permMod.addDiscount(BoardZone.BUILDINGTOWER, ResourcesOrPoints.newResources(4, 6, 7, 1));
		assertTrue(permMod.getDiscount(BoardZone.BUILDINGTOWER).getWood() == 10 &&
				permMod.IsTherediscountOnResources(BoardZone.BUILDINGTOWER) &&
				!permMod.IsTherediscountOnResources(BoardZone.VENTURETOWER) &&
				!permMod.IsTherediscountOnResources(BoardZone.MARKET));
	}
	
	
}
