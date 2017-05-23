package it.polimi.ingsw.GC_26_gameLogic;

public class GameParameters {
	private static int numberOfPeriods=3;
	private static int roundsforPeriod=2;
	private static int faithPointNeededPeriod1= 3;
	private static int faithPointNeededPeriod2= 4;
	private static int faithPointNeededPeriod3= 5;
	private static int turnsForRound = 4; // how many family members per player?
	private static int defaultNeutralValue = 0;
	
	private static int maxNumOfCards= 6; 
	
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
}
