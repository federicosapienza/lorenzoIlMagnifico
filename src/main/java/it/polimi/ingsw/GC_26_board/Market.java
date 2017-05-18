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
			marketPosition2= new MarketPosition();
			if(numberOfPlayers==4){
				marketPosition3= new MarketPosition();
				marketPosition4= new MarketPosition();
			}
		}
	}
	
	public MarketPosition getPosition(int number){
		switch(number) {
		case 1:
			return marketPosition1;
		case 2:
			return marketPosition2;
		case 3:
			return marketPosition3;
		case 4:
			return marketPosition4;
		default:
			System.out.println("Hai inserito un numero non corretto");
			return null;
		}//exception?
	}
	
	/*public clearMarket(){
		marketPosition1.clear();
		marketPosition2.clear();
		marketPosition3.clear();
		marketPosition4.clear();
	} */
	
	
	
}
