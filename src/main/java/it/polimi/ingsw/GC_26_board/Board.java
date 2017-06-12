package it.polimi.ingsw.GC_26_board;

import java.util.*;

import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_server.Observable;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class Board extends Observable<PositionDescriber>{
	private int numberOfPlayers;
	private Tower territoriesTower;
	private Tower buildingsTower;
	private Tower charactersTower;
	private Tower venturesTower;
	private Market market;
	private CouncilPalace councilPalace;
	private HarvestZone harvestZone;
	private ProductionZone productionZone;
	List<ResourcesOrPoints []> resourcesOrPointsList;
	
	public Board(int numberOfPlayers, List<ResourcesOrPoints []> resourcesOrPointsList){
		this.numberOfPlayers=numberOfPlayers;	
		create(numberOfPlayers,resourcesOrPointsList);
		this.resourcesOrPointsList= resourcesOrPointsList;
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
		harvestZone=new HarvestZone(GameParameters.getDefaultPositionValue(), GameParameters.getDefaultPositionValue(), numberOfPlayers);	
	}
	
	private void createHarvestZone(){
		productionZone=new ProductionZone(GameParameters.getDefaultPositionValue(), GameParameters.getDefaultPositionValue(), numberOfPlayers);	
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
	// called at the beginning of the game and whenever a player is re-inserted in a game, 
	//to send any boardElement, position by position.
	public void boardSendingDescription(){  
		towerDescription(BoardZone.TERRITORYTOWER, territoriesTower);
		towerDescription(BoardZone.BUILDINGTOWER, buildingsTower);
		towerDescription(BoardZone.CHARACTERTOWER, charactersTower);
		towerDescription(BoardZone.VENTURETOWER, venturesTower);
		marketDescription();
		harvestDescription();
		productionDescription();
		notifyObservers(new PositionDescriber(BoardZone.COUNCILPALACE, 1 ,GameParameters.getDefaultPositionValue(), null));

	}
	
	private void towerDescription(BoardZone zone,Tower tower){
		for(int i=1; i<= GameParameters.getTowerFloorsNumber(); i++){
			TowerPosition position = tower.getPosition(i);
			
			notifyObservers(new PositionDescriber(zone, i, tower.getFloorValue(i), position.getResourcesOrPointsinPosition().toString()));
		}	
	}
	
	private void marketDescription(){
		for(int i=1 ; i<=market.getPositionsActivated(); i++){
			MarketPosition position= market.getPosition(i);
			notifyObservers(new PositionDescriber(BoardZone.MARKET, i, market.getValue(), position.getResourcesOrPointsinPosition().toString()));
		}
	}
	
	private void harvestDescription(){
		for(int i=1 ; i<=harvestZone.getPositionsActivated(); i++){
			notifyObservers(new PositionDescriber(BoardZone.HARVEST, i,GameParameters.getDefaultPositionValue(), null));
		}
	}
	private void productionDescription(){
		for(int i=1 ; i<=productionZone.getPositionsActivated(); i++){
			notifyObservers(new PositionDescriber(BoardZone.PRODUCTION, i,GameParameters.getDefaultPositionValue(), null));
		}
	}
}
