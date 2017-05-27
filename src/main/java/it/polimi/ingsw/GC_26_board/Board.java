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
	private HarvestZone harvestZone;
	private ProductionZone productionZone;
	
	public Board(int numberOfPlayers, List<ResourcesOrPoints []> resourcesOrPointsList){
		this.numberOfPlayers=numberOfPlayers;	
		create(numberOfPlayers,resourcesOrPointsList);
	}
	
	public void create(int numberOfPlayers,List<ResourcesOrPoints []> resourcesOrPointsList){
		createTowers(resourcesOrPointsList);
		createMarket(resourcesOrPointsList);
		ResourcesOrPoints [] resourcesOrPointsCouncilPalace=resourcesOrPointsList.get(5);
		createCouncilPalace(resourcesOrPointsCouncilPalace[0],1);
		createProductionZone();
		createHarvestZone();
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
	
	private void createProductionZone(){
		harvestZone=new HarvestZone(1, 1, numberOfPlayers);	
	}
	
	private void createHarvestZone(){
		productionZone=new ProductionZone(1, 1, numberOfPlayers);	
	}
	
	
	public HarvestZone getHarvestZone() {
		return harvestZone;
	}
	
	public ProductionZone getProductionZone() {
		return productionZone;
	}
	
	public int getNumberOfPlayers(){
		return numberOfPlayers;
	}
	
	public Tower getTower(BoardZone zone){
		switch(zone){
		case TERRITORYTOWER: 
			return territoriesTower;
		case BUILDINGTOWER: 
			return buildingsTower;
		case CHARACTERTOWER:
			return charactersTower;
		case VENTURETOWER:
			return venturesTower;
		default:
			throw new IllegalArgumentException();
			}
	}
	
	public CouncilPalace getCouncilPalace() {
		return councilPalace;
	}
	
	
	public Market getMarket() {
		return market;
	}
	
	
	public void endRound(){
		territoriesTower.clearCardsAndFamilyMembers();
		buildingsTower.clearCardsAndFamilyMembers();
		charactersTower.clearCardsAndFamilyMembers();
		venturesTower.clearCardsAndFamilyMembers();
		market.clearMarket();
		harvestZone.clear();
		
		productionZone.clear();
		}
}
