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
	private static int numPlayerforCompleteMarketActivation=4;
	private static int minMarketzone=2;
	private static int maxMarketZone=4;
	private static int numPlayersForMultipleZones =3;
	private static int singleHarvestOrProuductionZones=1;
	private static int multipleHarvestOrProductionZones=1;
	
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
	public static int getMaxMarketZone() {
		return maxMarketZone;
	}
	public static int getMinMarketzone() {
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
}	


