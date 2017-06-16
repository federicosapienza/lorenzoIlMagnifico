package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class Market {
	private final int value=GameParameters.getDefaultPositionValue();
	private MarketPosition marketPosition1;
	private MarketPosition marketPosition2;
	private MarketPosition marketPosition3;
	private MarketPosition marketPosition4;
	private int numberOfPlayers;
	private int positionsActivated;
	
	public Market(int numberOfPlayers,ResourcesOrPoints[] resourcesOrPoints){
		this.numberOfPlayers=numberOfPlayers;
		
		marketPosition1= new MarketPosition(1,resourcesOrPoints[0],value);
		marketPosition2= new MarketPosition(2,resourcesOrPoints[1],value);
	
		if(numberOfPlayers>=GameParameters.getNumPlayerforCompleteMarketActivation()){
			marketPosition3= new MarketPosition(3,resourcesOrPoints[2],value);
			marketPosition4= new MarketPosition(4,resourcesOrPoints[3],value);
			positionsActivated=GameParameters.getMaxMarketZones();
		}
		else positionsActivated= GameParameters.getMinMarketZones();
		
	}
	
	public int getValue() {
		return value;
	}
	
	public MarketPosition getPosition(int number){
		switch(number) {
		case 1:
			return marketPosition1;
		case 2:
			return marketPosition2;
		case 3:
			if(numberOfPlayers<4){
				throw new IllegalArgumentException();
			}
			return marketPosition3;
		case 4:
			if(numberOfPlayers<4){
				throw new IllegalArgumentException();
			}
			return marketPosition4;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	public int getPositionsActivated() {
		return positionsActivated;
	}
	
	public void clearMarket(){
		marketPosition1.clear();
		marketPosition2.clear();
		if(marketPosition3!=null)
			marketPosition3.clear();
		if(marketPosition4!=null)
			marketPosition4.clear();
	} 
	
	
	
}
