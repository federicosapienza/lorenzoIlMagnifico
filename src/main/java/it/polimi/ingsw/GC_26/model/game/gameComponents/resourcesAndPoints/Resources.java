package it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the Resources of the game
 *
 */
public class Resources {
	private final int coins;
	private final int servants;
	private final int wood; 
	private final int stone;
	
	/**
	 * Constructor: it creates a Resources object which contains all the type of resources: coins, servants, wood and stone
	 * @param coins the coins of the game
	 * @param servants the servants of the game
	 * @param wood the wood of the game
	 * @param stone the stones of the game
	 */
	protected Resources(int coins, int servants, int wood, int stone) {
		this.coins=coins;
		this.servants=servants;
		this.wood=wood;
		this.stone=stone;
	}
	
	/**
	 * Method that describes the Resources as a String value
	 */
	@Override
	public String toString(){     //returns only the non 0 fields.
		StringBuilder temp = new StringBuilder("");
		if(coins!=0)
			temp.append(coins + " coins ");
		if(servants!=0)
			temp.append(servants + " servants ");
		if(stone!=0)
			temp.append(stone + " stone ");
		if(wood!=0)
			temp.append(wood + " wood ");
		return temp.toString(); 
	}

	/**
	 * Method that returns the coins of these Resources
	 * @return the coins of these Resources
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * Method that returns the servants of these Resources
	 * @return the servants of these Resources
	 */
	public int getServants() {
		return servants;
	}

	/**
	 * Method that returns the wood of these Resources
	 * @return the wood of these Resources
	 */
	public int getWood() {
		return wood;
	}

	/**
	 * Method that returns the stones of these Resources
	 * @return the stones of these Resources
	 */
	public int getStone() {
		return stone;
	}
	
}
