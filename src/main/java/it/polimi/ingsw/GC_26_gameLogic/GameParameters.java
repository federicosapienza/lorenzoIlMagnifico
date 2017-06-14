package it.polimi.ingsw.GC_26_gameLogic;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This class represents all the parameters not passed by files, but that can be useful to keep as parameters.
 */

public class GameParameters {
	private static final int numberOfPlayers = 4;
	private static int numberOfPeriods = 3;
	private static int roundsforPeriod = 2;
	private static int faithPointNeededPeriod1 = 3;
	private static int faithPointNeededPeriod2 = 4;
	private static int faithPointNeededPeriod3 = 5;
	private static int turnsForRound = 4; // how many family members per player?
	private static int defaultNeutralValue = 0;
	private static ResourcesOrPoints towerOccupiedMalus = ResourcesOrPoints.newResources(3, 0, 0, 0);
	private static int maxNumOfCards = 6;
	private static int[] territoryCardRequirements =new int[] {0,0,3,7,12,18};
	private static int TowerFloors = 4;
	private static int[] towersFloorsValues =new int[] {1,3,5,7};
	private static int defaultPositionValue = 1;

	private static int numPlayerforCompleteMarketActivation = 4;
	private static int minMarketzone = 2;
	private static int maxMarketZone = 4;
	private static int numPlayersForMultipleZones = 3;
	private static int singleHarvestOrProductionZones =1;
	private static int multipleHarvestOrProductionZones =1;
	private static int multiplePositionMalus = -3;
	private static int standardPersonalBonusTileValue =1;
	private static ResourcesOrPoints[] diplomaticPrivilegesTrades = {ResourcesOrPoints.newResources(0,0,1,1),
							ResourcesOrPoints.newResources(0,2,0,0),ResourcesOrPoints.newResources(2,0,0,0),
							ResourcesOrPoints.newPoints(0, 2, 0, 0),ResourcesOrPoints.newPoints(0, 0, 1, 0)};
			

	/**
	 * Method that returns the number of players involved in the game
	 * @return numberOfPlayers It's the number of players involved in the game
	 */
	
	public static int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	/**
	 * Method that returns the number of faith points that every player needs to avoid the excommunication 
	 * @param period It's current period
	 * @return faithPointNeededPeriod1 It returns 3 if the players are playing in the 1st period.
	 * @return faithPointNeededPeriod2 It returns 4 if the players are playing in the 2nd period.
	 * @return faithPointNeededPeriod3 It returns 5 if the players are playing in the 3rd period.
	 */
	public static int getFaithPointNeeded(int period){
		switch (period) {
		case 1:{
			return faithPointNeededPeriod1;
			}
		case 2:{
			return faithPointNeededPeriod2;
		}
		case 3 :{
			return faithPointNeededPeriod3;
		}
		default:
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Method that returns the value of the desired floor of the towers. 
	 * @param floor It denotes the number of the desired floor.
	 * @return towersFloorsValues[0] It returns 1 if the number of the floor is 1
	 * @return towersFloorsValues[1] It returns 3 if the number of the floor is 2
	 * @return towersFloorsValues[2] It returns 5 if the number of the floor is 3
	 * @return towersFloorsValues[3] It returns 7 if the number of the floor is 4
	 */
	public static int getTowersFloorsValues(int floor){
		switch (floor) {
		case 1:{
			return towersFloorsValues[0];
			}
		case 2:{
			return towersFloorsValues[1];
		}
		case 3 :{
			return towersFloorsValues[2];
		}
		case 4:
			return towersFloorsValues[3];
		default: 	
			throw new IllegalArgumentException();
		}
	}
	
	
	/**
	 * Method that returns the number of periods
	 * @return numberOfPeriods It's the number of periods.
	 */
	
	public static int getNumberOfPeriods() {
		return numberOfPeriods;
	}
	
	/**
	 * Method that returns the number of rounds for each period
	 * @return roundsforPeriod It's number of rounds for each period
	 */
	public static int getRoundsforPeriod() {
		return roundsforPeriod;
	}
	
	/**
	 * Method that returns the number of turns for each round
	 * @return turnsForRound It's the number of turns for each round
	 */
	public static int getTurnsForRound() {
		return turnsForRound;
	}
	
	/**
	 * Method that returns the maximum number of cards
	 * @return maxNumOfCards It's the maximum number of cards
	 */
	public static int getMaxNumOfCards() {
		return maxNumOfCards;
	}
	
	/**
	 * Method that returns the default value for neutral family members, which is 0.
	 * @return defaultNeutralValue It's the default value for neutral family members
	 */
	public static int getDefaultNeutralValue() {
		return defaultNeutralValue;
	}
	
	/**
	 * Method that returns the malus (3 coins to pay) that the player has to pay 
	 * if he wants to set a family member in a tower which has already been occupied by another player or
	 * by the neutral family member of the current player
	 * @return towerOccupiedMalus It's the malus (3 coins) that the player has to pay 
	 */
	public static ResourcesOrPoints getTowerOccupiedMalus() {
		return towerOccupiedMalus;
	}
	
	/**
	 * Method that returns the number of military points required to set a territory card in the correct territory zone
	 * @param number It's the number of the territory zone (from the left) from which I want to get the required military points 
	 * @return territoryCardRequirements[number+1] It represents the required military points to set a territory
	 * card in the territory zone indicated by @param number
	 */
	public static int getTerritoryCardRequirements(int number) {
		return territoryCardRequirements[number-1]; // Ho modificato da number + 1, che penso sia errato
	}
	
	/**
	 * Method that returns the number of floors of the towers.
	 * @return TowerFloors It's number of floors of the towers
	 */
	public static int getTowerFloorsNumber() {
		return TowerFloors;
	}
	
	/**
	 * Method that returns the required number of players in order to use the full version of the market zone, which is 4. 
	 * @return numPlayerforCompleteMarketActivation It's the required number of players in order to use the full version of the market zone
	 */
	public static int getNumPlayerforCompleteMarketActivation() {
		return numPlayerforCompleteMarketActivation;
	}
	
	/**
	 * Method that returns the maximum number of market zones, which is 4.
	 * @return maxMarketZone It's the maximum number of market zones
	 */
	public static int getMaxMarketZones() {
		return maxMarketZone;
	}
	
	/**
	 * Method that returns the minimum number of market zones, which is 2.
	 * @return minMarketZone It's the minimum number of market zones
	 */
	public static int getMinMarketZones() {
		return minMarketzone;
	}
	
	/**
	 * Method that returns the required number of players to use the board with multiple zones, which is 3. 
	 * @return numPlayersForMultipleZones It's the required number of players to use the board with multiple zones
	 */
	public static int getNumPlayersForMultipleZones() {
		return numPlayersForMultipleZones;
	}
	
	/**
	 * Method that returns the number of multiple zones for harvest or production, which is 1
	 * @return multipleHarvestOrProductionZones It's the number of multiple zones for harvest or production
	 */
	public static int getMultipleHarvestOrProductionZones() {
		return multipleHarvestOrProductionZones;
	}
	
	/**
	 * Method that returns the number of single zones for harvest or production, which is 1
	 * @return singleHarvestOrProductionZones It's the number of single zones for harvest or production
	 */
	public static int getSingleHarvestOrProductionZones() {
		return singleHarvestOrProductionZones;
	}
	
	/**
	 * Method that returns the malus that the player has to get if he sets a family member in a multiple zone, which is -3.
	 * @return multiplePositionMalus It's he malus that the player has to get if he sets a family member in a multiple zone
	 */
	public static int getMultiplePositionMalus() {
		return multiplePositionMalus;
	}
	
	/**
	 * Method that returns the standard bonus value got by the activation of the personal bonus tile, which is 1.
	 * @return standardPersonalBonusTileValue It's the standard bonus value got by the activation of the personal bonus tile
	 */
	public static int getStandardPersonalBonusTileValue() {
		return standardPersonalBonusTileValue;
	}
	
	/**
	 * Method that returns the values of the diplomatic privileges trades
	 * @return diplomaticPrivilegesTrades It represents the values of the diplomatic privileges trades
	 */
	public static ResourcesOrPoints[] getDiplomaticPrivilegesTrades() {
		return diplomaticPrivilegesTrades;
	}
	
	/**
	 * Method that returns the value of the default position, which is 1.
	 * @return defaultPositionValue It's the value of the default position
	 */
	public static int getDefaultPositionValue() {
		return defaultPositionValue;
	}
}	


