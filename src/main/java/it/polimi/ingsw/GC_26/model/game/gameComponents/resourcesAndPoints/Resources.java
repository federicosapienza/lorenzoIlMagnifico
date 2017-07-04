package it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints;

//go to ResourcesOrPoints for explanation
public class Resources {
	private final int coins;
	private final int servants;
	private final int wood; 
	private final int stone;
	
	protected Resources(int coins, int servants, int wood, int stone) {
		this.coins=coins;
		this.servants=servants;
		this.wood=wood;
		this.stone=stone;
	}
	
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

	public int getCoins() {
		return coins;
	}

	public int getServants() {
		return servants;
	}

	public int getWood() {
		return wood;
	}

	public int getStone() {
		return stone;
	}
	
}
