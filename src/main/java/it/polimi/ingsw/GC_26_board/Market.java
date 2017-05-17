package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class Market {
	private final int value=1;
	private MarketPosition marketPosition1;
	private MarketPosition marketPosition2;
	private MarketPosition marketPosition3;
	private MarketPosition marketPosition4;
	
	public Market(int numberOfPlayers,ResourcesOrPoints[] resourcesOrPoints){
		if(numberOfPlayers==2 || numberOfPlayers==3){
			marketPosition1= new MarketPosition();
			marketPosition1= new MarketPosition();
		}
	}
}
