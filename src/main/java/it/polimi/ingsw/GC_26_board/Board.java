package it.polimi.ingsw.GC_26_board;

import java.util.*;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class Board {
	private int numberOfPlayers;
	private Tower territoriesTower;
	private Tower buildingsTower;
	private Tower charactersTower;
	private Tower venturesTower;
	private Market market;
	private CouncilPalace councilPalace;
	private SingleHarvest singleHarvest;
	private SingleProduction singleProduction;
	private MultipleHarvest multipleHarvest;
	private MultipleProduction multipleProduction;
	
	public Board(int numberOfPlayers, List<ResourcesOrPoints []> resourcesOrPointsList){
		this.numberOfPlayers=numberOfPlayers;	
		create(numberOfPlayers,resourcesOrPointsList);
	}
	
	public void create(int numberOfPlayers,List<ResourcesOrPoints []> resourcesOrPointsList){
		createTowers(resourcesOrPointsList);
		createMarket(resourcesOrPointsList);
		ResourcesOrPoints [] resourcesOrPointsCouncilPalace=resourcesOrPointsList.get(5);
		createCouncilPalace(resourcesOrPointsCouncilPalace[0],1);
		createSingleHarvest(1);
		createSingleProduction(1);
		if(numberOfPlayers>=3){
			createMultipleProducion(1);
			createMultipleHarvest(1);
		}	
	}
	
	private void createTowers(List<ResourcesOrPoints []> resourcesOrPointsList){
		territoriesTower=new Tower(resourcesOrPointsList.get(0)); 
		buildingsTower=new Tower(resourcesOrPointsList.get(1));   
		charactersTower=new Tower(resourcesOrPointsList.get(2));  
		venturesTower=new Tower(resourcesOrPointsList.get(3));
	}
	
	private void createMarket(List<ResourcesOrPoints []> resourcesOrPointsList){
		market=new Market(numberOfPlayers,resourcesOrPointsList.get(4));
	}
	
	private void createCouncilPalace(ResourcesOrPoints resourcesOrPoints,int value){
		councilPalace= new CouncilPalace(resourcesOrPoints,1);
	}
	
	private void createSingleHarvest(int value){
		singleHarvest=new SingleHarvest(value);	
	}
	
	private void createSingleProduction(int value){
		singleProduction=new SingleProduction(value);	
	}
	
	private void createMultipleHarvest(int value){
		multipleHarvest= new MultipleHarvest(value);
	}
	
	private void createMultipleProducion(int value){
		multipleProduction= new MultipleProduction(value);
	}
	
	public int getNumberOfPlayers(){
		return numberOfPlayers;
	}
	
	public Tower getTower(int number){
		switch(number){
		case 1: 
			return territoriesTower;
		case 2: 
			return buildingsTower;
		case 3:
			return charactersTower;
		case 4:
			return venturesTower;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	public CouncilPalace getCouncilPalace() {
		return councilPalace;
	}
	
	public SingleHarvest getSingleHarvest(){
		return singleHarvest;
	}
	
	public SingleProduction getSingleProduction() {
		return singleProduction;
	}
	
	public MultipleProduction getMultipleProduction(){
		return multipleProduction;
	}
	
	public MultipleHarvest getMultipleHarvest(){
		return multipleHarvest;
	}
	
	public void endRound(){
		territoriesTower.clearCardsAndFamilyMembers();
		buildingsTower.clearCardsAndFamilyMembers();
		charactersTower.clearCardsAndFamilyMembers();
		venturesTower.clearCardsAndFamilyMembers();
		market.clearMarket();
		singleHarvest.clear();
		multipleHarvest.clear();
		singleProduction.clear();
		multipleProduction.clear();
		}
}
