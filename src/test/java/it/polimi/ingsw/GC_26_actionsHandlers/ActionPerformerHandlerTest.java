package it.polimi.ingsw.GC_26_actionsHandlers;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_board.CouncilPalace;
import it.polimi.ingsw.GC_26_board.MarketPosition;
import it.polimi.ingsw.GC_26_board.Tower;
import it.polimi.ingsw.GC_26_board.TowerPosition;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class ActionPerformerHandlerTest {

	ActionPerformerHandler actionPerformerHandler = new ActionPerformerHandler();
	/*
	@Test
	public void payCoinsIfTowerOccupiedTest() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(0, 0, 2, 0, 0,0,0,0);
		ResourcesOrPoints[] resOrPtArray = new ResourcesOrPoints[4];
		for(int i=0; i<4;i++){
			resOrPtArray[i]=resourcesOrPoints;
		}
		Tower tower = new Tower(resOrPtArray);
		FamilyMember familyMember = new FamilyMember(Colour.BLACK, player);
		tower.setPlayerInTheTower(familyMember);
		Player player2 = new Player("Leon2", startingResources);
		FamilyMember familyMember2 = new FamilyMember(Colour.ORANGE, player2);
		tower.setPlayerInTheTower(familyMember2);
		actionPerformerHandler.payCoinsIfTowerOccupied(tower, player2);
		assertEquals(2, player2.getWarehouse().getCoins());
	}
	
	@Test
	public void getResourcesBonusFromTowerPositionsTest(){
		ResourcesOrPoints resourcesOrPointsinPosition = ResourcesOrPoints.newResourcesOrPoints(0, 0, 2, 0, 0, 0, 0, 0);
		TowerPosition towerPosition = new TowerPosition(3, resourcesOrPointsinPosition, 5);
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		actionPerformerHandler.goToTowerPosition(towerPosition, player.getFamilyMembers().getfamilyMember(Colour.BLACK));
		assertEquals(7, player.getWarehouse().getWood());
	}

	
	@Test
	public void getResourcesBonusFromMarketPositionsTest(){
		ResourcesOrPoints resourcesOrPointsinPosition = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 2, 0, 0, 0, 0);
		MarketPosition marketPosition = new MarketPosition(1, resourcesOrPointsinPosition, 1);
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		actionPerformerHandler.goToMarketPositions(marketPosition, player.getFamilyMembers().getfamilyMember(Colour.ORANGE));
		assertEquals(7, player.getWarehouse().getStone());
	}
	
	@Test
	public void getResourcesBonusFromCouncilPalacePositionsTest(){
		ResourcesOrPoints resourcesOrPointsinPosition = ResourcesOrPoints.newResourcesOrPoints(2, 0, 0, 0, 0, 0, 0, 0);
		CouncilPalace councilPalace = new CouncilPalace(resourcesOrPointsinPosition, 1);
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		actionPerformerHandler.goToCouncilPalacePosition(councilPalace, player.getFamilyMembers().getfamilyMember(Colour.WHITE));
		assertEquals(7, player.getWarehouse().getCoins());
	}
	*/
}
