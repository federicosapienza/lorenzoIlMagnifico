package it.polimi.ingsw.GC_26_gameLogic;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;


//here are all the parameters not passed by files but that we find useful to keep parametric 
public class GameParameters {
	private static final int numberOfPlayers=4;
	private static int numberOfPeriods=3;
	private static int roundsforPeriod=2;
	private static int faithPointNeededPeriod1= 3;
	private static int faithPointNeededPeriod2= 4;
	private static int faithPointNeededPeriod3= 5;
	private static int turnsForRound = 4; // how many family members per player?
	private static int defaultNeutralValue = 0;
	private static ResourcesOrPoints towerOccupiedMalus = ResourcesOrPoints.newResources(3, 0, 0, 0);
	private static int maxNumOfCards= 6;
	private static Integer[] territoryCardRequirements = {0,0,3,7,12,18};
	private static int TowerFloors =4;
	private static Integer[] towersFloorsValues = {1,3,5,7};
	private static int defaultPositionValue= 1;

	private static int numPlayerforCompleteMarketActivation=4;
	private static int minMarketzone=2;
	private static int maxMarketZone=4;
	private static int numPlayersForMultipleZones =3;
	private static int singleHarvestOrProuductionZones=1;
	private static int multipleHarvestOrProductionZones=1;
	private static int multiplePositionMalus = -3;
	private static int standardPersonalBonusTileValue=1;
	private static ResourcesOrPoints[] diplomaticPrivilegesTrades ={ResourcesOrPoints.newResources(0,0,1,1),
							ResourcesOrPoints.newResources(0,2,0,0),ResourcesOrPoints.newResources(2,0,0,0),
							ResourcesOrPoints.newPoints(0, 2, 0, 0),ResourcesOrPoints.newPoints(0, 0, 1, 0)};
			

	
	
	public static int getNumberOfPlayers() {
		return numberOfPlayers;
	}
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
			return towersFloorsValues[4];
		default: 	
			throw new IllegalArgumentException();
		}
	}
	
	
	
	
	public static int getNumberOfPeriods() {
		return numberOfPeriods;
	}
	public static int getRoundsforPeriod() {
		return roundsforPeriod;
	}
	
	public static int getTurnsForRound() {
		return turnsForRound;
	}
	
	public static int getMaxNumOfCards() {
		return maxNumOfCards;
	}
	
	public static int getDefaultNeutralValue() {
		return defaultNeutralValue;
	}
	
	public static ResourcesOrPoints getTowerOccupiedMalus() {
		return towerOccupiedMalus;
	}
	
	public static int getTerritoryCardRequirements(int number) {
		return territoryCardRequirements[number+1];
	}
	public static int getTowerFloorsNumber() {
		return TowerFloors;
	}
	
	public static int getNumPlayerforCompleteMarketActivation() {
		return numPlayerforCompleteMarketActivation;
	}
	public static int getMaxMarketZones() {
		return maxMarketZone;
	}
	public static int getMinMarketZones() {
		return minMarketzone;
	}
	public static int getNumPlayersForMultipleZones() {
		return numPlayersForMultipleZones;
	}
	
	public static int getMultipleHarvestOrProductionZones() {
		return multipleHarvestOrProductionZones;
	}
	
	public static int getSingleHarvestOrProductionZones() {
		return singleHarvestOrProuductionZones;
	}
	public static int getMultiplePositionMalus() {
		return multiplePositionMalus;
	}
	public static int getStandardPersonalBonusTileValue() {
		return standardPersonalBonusTileValue;
	}
	
	public static ResourcesOrPoints[] getDiplomaticPrivilegesTrades() {
		return diplomaticPrivilegesTrades;
	}
	
	public static int getDefaultPositionValue() {
		return defaultPositionValue;
	}
}	


