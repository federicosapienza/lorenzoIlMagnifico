package it.polimi.ingsw.GC_26_board;

import java.util.*;

import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_server.Observable;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the board which is in common to all the players of the game
 *
 */
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
	private String errorString="resourcesOrPointsList is null";
	
	/**
	 * Constructor: it creates the correct version of the board according to the number of the players that are playing 
	 * the game, expressed in the parameter
	 * @param numberOfPlayers It's the number of players that are playing the game
	 * @param resourcesOrPointsList It's the list of the starting resources and points 
	 */
	public Board(int numberOfPlayers, List<ResourcesOrPoints []> resourcesOrPointsList){
		if (resourcesOrPointsList == null) {
			throw new NullPointerException();
		}
		if (numberOfPlayers >4 || numberOfPlayers <2) {
			throw new IllegalArgumentException();
		}
		this.numberOfPlayers=numberOfPlayers;	
		create(numberOfPlayers,resourcesOrPointsList);
	}
	
	/**
	 * Method that creates all the correct zones of the board, according to the number of the players that are playing 
	 * the game, expressed in the parameter
	 * @param numberOfPlayers It's the number of players that are playing the game
	 * @param resourcesOrPointsList It's the list of the starting resources and points 
	 */
	public void create(int numberOfPlayers,List<ResourcesOrPoints []> resourcesOrPointsList){
		if (resourcesOrPointsList == null) {
			throw new NullPointerException(errorString);
		}
		if (numberOfPlayers > 4 || numberOfPlayers <2) {
			throw new IllegalArgumentException();
		}
		createTowers(resourcesOrPointsList);
		createMarket(resourcesOrPointsList);
		ResourcesOrPoints [] resourcesOrPointsCouncilPalace=resourcesOrPointsList.get(5);
		createCouncilPalace(resourcesOrPointsCouncilPalace[0],1);
		createProductionZone();
		createHarvestZone();
		}	
	
	/**
	 * Method that creates the towers of the board with the corresponding resources and points bonus
	 * @param resourcesOrPointsList It's the list containing the resources or points which every tower gives as a bonus
	 * when players occupy it with their family members
	 */
	private void createTowers(List<ResourcesOrPoints []> resourcesOrPointsList){
		if (resourcesOrPointsList == null) {
			throw new NullPointerException(errorString);
		}
		territoriesTower=new Tower(resourcesOrPointsList.get(0)); 
		buildingsTower=new Tower(resourcesOrPointsList.get(1));   
		charactersTower=new Tower(resourcesOrPointsList.get(2));  
		venturesTower=new Tower(resourcesOrPointsList.get(3));
	}
	
	/**
	 * Method that creates the correct Market zone of the board according to the number of players
	 * @param resourcesOrPointsList It's the list containing the resources or points which every Market space gives as 
	 * a bonus when players occupy it with their family members
	 */
	private void createMarket(List<ResourcesOrPoints []> resourcesOrPointsList){
		if (resourcesOrPointsList == null) {
			throw new NullPointerException(errorString);
		}
		market=new Market(numberOfPlayers,resourcesOrPointsList.get(4));
	}
	
	/**
	 * Method that creates the Council Palace: every player who wants to occupy this zone needs a family member with
	 * the value expressed in the param (by rules it's 1) and when doing this, he obtains some resources or points (by
	 * rules 1 coin and 1 Council Privilege)
	 * @param resourcesOrPoints the resources or points that the player who occupies this zone will obtain
	 * @param value It's the required value of the family members 
	 */
	private void createCouncilPalace(ResourcesOrPoints resourcesOrPoints,int value){
		if (resourcesOrPoints == null) {
			throw new NullPointerException(errorString);
		}
		councilPalace= new CouncilPalace(resourcesOrPoints, value);
	}
	
	/**
	 * Method that creates the correct Production zone of the game, according to the number of players that are playing
	 * the game
	 */
	private void createProductionZone(){
		productionZone=new ProductionZone(GameParameters.getDefaultPositionValue(), GameParameters.getDefaultPositionValue(), numberOfPlayers);	
	}
	
	/**
	 * Method that creates the correct Harvest zone of the game, according to the number of players that are playing
	 * the game
	 */
	private void createHarvestZone(){
		harvestZone=new HarvestZone(GameParameters.getDefaultPositionValue(), GameParameters.getDefaultPositionValue(), numberOfPlayers);	
	}
	
	/**
	 * Method that returns the version of the Production zone of the current game
	 * @return the version of the Production zone of the current game
	 */
	public ProductionZone getProductionZone() {
		return productionZone;
	}
	
	/**
	 * Method that returns the version of the Harvest zone of the current game
	 * @return the version of the Harvest zone of the current game
	 */
	public HarvestZone getHarvestZone() {
		return harvestZone;
	}
	
	/**
	 * Method that returns the number of players that are playing the current game
	 * @return the number of players that are playing the current game
	 */
	public int getNumberOfPlayers(){
		return numberOfPlayers;
	}
	
	/**
	 * Method that returns the tower that corresponds to the board zone expressed in the parameter
	 * @param zone It's the board zone to analyze
	 * @return the tower that corresponds to the board zone
	 */
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
	
	/**
	 * Method that returns the Council Palace of the board
	 * @return the Council Palace of the board
	 */
	public CouncilPalace getCouncilPalace() {
		return councilPalace;
	}
	
	/**
	 * Method that returns the version of the market of this board
	 * @return the version of the market of this board
	 */
	public Market getMarket() {
		return market;
	}
	
	/**
	 * Method needed at the end of every round to clear the board, as the rules imply this
	 */
	public void endRound(){
		territoriesTower.clearCardsAndFamilyMembers();
		buildingsTower.clearCardsAndFamilyMembers();
		charactersTower.clearCardsAndFamilyMembers();
		venturesTower.clearCardsAndFamilyMembers();
		market.clearMarket();
		harvestZone.clear();
		productionZone.clear();
		councilPalace.clear();
	}
	
	/**
	 *  Method called at the beginning of the game and whenever a player is reconnecting to a game, to send every 
	 *  description of the board element, position by position.
	 */
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
	
	/**
	 * Method that describes the tower of the corresponding board zone, expressed in the parameters
	 * @param zone It's the board zone of the game that contains the tower to describe
	 * @param tower It's the tower of the corresponding board zone
	 */
	private void towerDescription(BoardZone zone,Tower tower){
		if (zone == null || tower == null) {
			throw new NullPointerException("zone or tower is null");
		}
		for(int i=1; i<= GameParameters.getTowerFloorsNumber(); i++){
			TowerPosition position = tower.getPosition(i);
			
			notifyObservers(new PositionDescriber(zone, i, tower.getFloorValue(i), position.getResourcesOrPointsinPosition().toString()));
		}	
	}
	
	/**
	 * Method that describes the Market of the board
	 */
	private void marketDescription(){
		for(int i=1 ; i<=market.getPositionsActivated(); i++){
			MarketPosition position= market.getPosition(i);
			notifyObservers(new PositionDescriber(BoardZone.MARKET, i, market.getValue(), position.getResourcesOrPointsinPosition().toString()));
		}
	}
	
	/**
	 * Method that describes the Harvest zone of the board
	 */
	private void harvestDescription(){
		for(int i=1 ; i<=harvestZone.getPositionsActivated(); i++){
			notifyObservers(new PositionDescriber(BoardZone.HARVEST, i,GameParameters.getDefaultPositionValue(), null));
		}
	}
	
	/**
	 * Method that describes the Production zone of the board
	 */
	private void productionDescription(){
		for(int i=1 ; i<=productionZone.getPositionsActivated(); i++){
			notifyObservers(new PositionDescriber(BoardZone.PRODUCTION, i,GameParameters.getDefaultPositionValue(), null));
		}
	}
}
