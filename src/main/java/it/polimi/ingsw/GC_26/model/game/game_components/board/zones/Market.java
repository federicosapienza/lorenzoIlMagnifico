package it.polimi.ingsw.GC_26.model.game.game_components.board.zones;

import it.polimi.ingsw.GC_26.model.game.game_components.board.positions.MarketPosition;
import it.polimi.ingsw.GC_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.game.game_logic.GameParameters;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the Market zone of the board
 *
 */
public class Market {
	private final int value=GameParameters.getDefaultPositionValue();
	private MarketPosition marketPosition1;
	private MarketPosition marketPosition2;
	private MarketPosition marketPosition3;
	private MarketPosition marketPosition4;
	private int numberOfPlayers;
	private int positionsActivated;
	
	/**
	 * Constructor: it creates the correct Market according to the number of players contained in the parameters
	 * @param numberOfPlayers It's the number of players that are playing the game
	 * @param resourcesOrPoints It's the array of resources or points to set in each action space of the Market
	 */
	public Market(int numberOfPlayers,ResourcesOrPoints[] resourcesOrPoints){
		this.numberOfPlayers=numberOfPlayers;
		
		marketPosition1= new MarketPosition(1,resourcesOrPoints[0],value);
		marketPosition2= new MarketPosition(2,resourcesOrPoints[1],value);
	
		//The 3rd and 4th action spaces of the Market are created only if the number of players is 4
		if(numberOfPlayers>=GameParameters.getNumPlayerforCompleteMarketActivation()){
			marketPosition3= new MarketPosition(3,resourcesOrPoints[2],value);
			marketPosition4= new MarketPosition(4,resourcesOrPoints[3],value);
			positionsActivated=GameParameters.getMaxMarketZones();
		}
		else positionsActivated= GameParameters.getMinMarketZones();
		
	}
	
	/**
	 * Method that returns the minimum required value of the family member that every player has to satisfy to put it in
	 * an action space of the Market
	 * @return the minimum required value of the family member
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Method that returns the action space of the Market that corresponds to the number of position contained in 
	 * the parameter 
	 * @param number It's the number of position to check
	 * @return the action space of the Market that corresponds to the number of position contained in 
	 * the parameter 
	 */
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
	
	/**
	 * Method that checks the version of the Market and returns the number of positions activated
	 * @return 2 if the number of players is 2 or 3; 4 if the number of players is 4
	 */
	public int getPositionsActivated() {
		return positionsActivated;
	}
	
	/**
	 * Method to call at the end of every round: it clears all the action spaces of the Market.
	 */
	public void clearMarket(){
		marketPosition1.clear();
		marketPosition2.clear();
		if(marketPosition3!=null)
			marketPosition3.clear();
		if(marketPosition4!=null)
			marketPosition4.clear();
	} 
	
	
	
}
