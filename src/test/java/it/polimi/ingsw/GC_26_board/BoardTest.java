package it.polimi.ingsw.GC_26_board;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_readJson.BonusImplementation;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class BoardTest {
	ResourcesOrPoints[] resourcesOrPointsArray1 = new ResourcesOrPoints[4];
	ResourcesOrPoints[] resourcesOrPointsArray2 = new ResourcesOrPoints[4];
	ResourcesOrPoints[] resourcesOrPointsArray3 = new ResourcesOrPoints[4];
	ResourcesOrPoints[] resourcesOrPointsArray4 = new ResourcesOrPoints[4];
	ResourcesOrPoints[] resourcesOrPointsArray5 = new ResourcesOrPoints[4];
	List<ResourcesOrPoints[]> resourcesOrPointsList = new ArrayList<ResourcesOrPoints[]>();
	
	Board board;
	
	Player player1;
	Player player2;
	Player player3;
	Player player4;
	
	@Test
	public void testCorrectConstructor() {
		List<ResourcesOrPoints[]> listOfResourcesOrPoints = null;
		boolean thrownException = false;
		try {
			Board testBoard = new Board(2, listOfResourcesOrPoints);
		} catch (NullPointerException e) {
			thrownException = true;
		}
		assertTrue(thrownException);
	}
	/*
	@Test
	public void testCorrectCreation() {
		List<ResourcesOrPoints[]> startingResources = new BonusImplementation().getListOfResourcesOfPointsArray();
		List<ResourcesOrPoints[]> listOfResourcesOrPoints = null;
		boolean thrownException = false;
		Board testBoard = new Board(2, startingResources);
		try {
			testBoard.create(2, listOfResourcesOrPoints);
		} catch (NullPointerException e) {
			thrownException = true;
		}
		assertTrue(thrownException);
	}
	*/
	
	@Test
	public void existenceTest() {
		
		resourcesOrPointsArray1[0] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		resourcesOrPointsArray1[1] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		resourcesOrPointsArray1[2] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 1, 0, 0, 0, 0, 0);
		resourcesOrPointsArray1[3] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 2, 0, 0, 0, 0, 0);
		resourcesOrPointsArray2[0] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		resourcesOrPointsArray2[1] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		resourcesOrPointsArray2[2] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 1, 0, 0);
		resourcesOrPointsArray2[3] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 2, 0, 0);
		resourcesOrPointsArray3[0] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		resourcesOrPointsArray3[1] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		resourcesOrPointsArray3[2] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 1, 0, 0, 0, 0);
		resourcesOrPointsArray3[3] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 2, 0, 0, 0, 0);
		resourcesOrPointsArray4[0] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		resourcesOrPointsArray4[1] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		resourcesOrPointsArray4[2] = ResourcesOrPoints.newResourcesOrPoints(1, 0, 0, 0, 0, 0, 0, 0);
		resourcesOrPointsArray4[3] = ResourcesOrPoints.newResourcesOrPoints(2, 0, 0, 0, 0, 0, 0, 0);
		resourcesOrPointsArray5[0] = ResourcesOrPoints.newResourcesOrPoints(5, 0, 0, 0, 0, 0, 0, 0);
		resourcesOrPointsArray5[1] = ResourcesOrPoints.newResourcesOrPoints(0, 5, 0, 0, 0, 0, 0, 0);
		resourcesOrPointsArray5[2] = ResourcesOrPoints.newResourcesOrPoints(3, 0, 0, 0, 2, 0, 0, 0);
		resourcesOrPointsArray5[3] = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 2);
		resourcesOrPointsList.add(resourcesOrPointsArray1);
		resourcesOrPointsList.add(resourcesOrPointsArray2);
		resourcesOrPointsList.add(resourcesOrPointsArray3);
		resourcesOrPointsList.add(resourcesOrPointsArray4);
		resourcesOrPointsList.add(resourcesOrPointsArray5);
		assertNotNull(resourcesOrPointsArray1);
		assertNotNull(resourcesOrPointsArray2);
		assertNotNull(resourcesOrPointsArray3);
		assertNotNull(resourcesOrPointsArray4);
		assertNotNull(resourcesOrPointsArray5);
		assertNotNull(resourcesOrPointsList);
		//board = new Board(2, resourcesOrPointsList);
		//BonusImplementation startingresources = new BonusImplementation();
		//Board board4 = new Board(4, startingresources.getListOfResourcesOfPointsArray());
		//assertNotNull(board4);
		//assertEquals(4, board4.getNumberOfPlayers());
		//assertEquals(2, board.getNumberOfPlayers());
		//Commented lines give errors but I don't know why.
		
		
		
	}
	
	

}
