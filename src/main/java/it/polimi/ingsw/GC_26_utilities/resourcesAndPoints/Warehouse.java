package it.polimi.ingsw.GC_26_utilities.resourcesAndPoints;
import it.polimi.ingsw.GC_26_player.*;

/* It ' s the class that represent the status of the player in terms of resources and points owned.
 * It must not be mistaken with ResourcesOrPoints class , which instead represent the payments and effects that are called by cards, positions etc
 *ResourcesAndPoints' attributes are immutable, warehouse's attributes are mutable.  */

public class Warehouse {
	private int coins;
	private int servants;
	private int woods; 
	private int stones;
	private int victoryPoints;
	private int militaryPoints;
	private int faithPoints;
	private int councilPrivileges;
	private static final int initialCoinsID1 = 5;
    private static final int initialCoinsID2 = 6;
    private static final int initialCoinsID3 = 7;
    private static final int initialCoinsID4 = 8;
    private static final int initialServants = 3;
    private static final int initialWoods = 2;
    private static final int initialStones = 2;

    public Warehouse(Player player) {
        servants = initialServants;
        woods = initialWoods;
        stones = initialStones;
        if (player.getPlayerID()==1){
            coins = initialCoinsID1;
        }
        else if (player.getPlayerID()==2) {
            coins = initialCoinsID2;
        }
        else if (player.getPlayerID()==3) {
            coins = initialCoinsID3;
        }
        else if (player.getPlayerID()==4) {
            coins = initialCoinsID4;
        }
    }
	
	
	public Warehouse(Warehouse other){  /// use to copy and create temporary warehouse : useful in some parts of gameLogic
		this.coins =other.getCoins();
		this.servants=other.getServants();
		this.woods=other.getWood();
		this.stones= other.getStone();
		this.victoryPoints= other.getVictoryPoints();
		this.militaryPoints = other.getMilitaryPoints();
		this.faithPoints= other.getFaithPoints();
		this.councilPrivileges=other.getCouncilPrivileges();
	}
	
	//getters methods
	public int getCoins() {
		return coins;
	}
	public int getServants() {
		return servants;
	}
	public int getStone() {
		return stones;
	}
	public int getWood() {
		return woods;
	}
	
	public int getMilitaryPoints() {
		return militaryPoints;
	}
	
	public int getCouncilPrivileges() {
		return councilPrivileges;
	}
	
	public int getFaithPoints() {
		return faithPoints;
	}
	public int getVictoryPoints() {
		return victoryPoints;
	}
	
	// ToString methods
	public String ResourcesStatus() {// String that describes only the status of resources owned by the player
		return coins+" coins,  "+ servants+ " servants, "+woods+ " wood, "+stones+ " stone.";
	}

	@Override
	public String toString(){
		return coins+" coins,  "+ servants+ " servants, "+woods+ " wood, "+stones+ " stone"
	    		+ victoryPoints+ " victoryPoints"+  militaryPoints+" military points" + victoryPoints+ " victory points"+
	    		 faithPoints+ " faith points" + councilPrivileges +" council priviledges.";
	}

	public String MilitaryPointStatus(){
		return militaryPoints +" military points";
	}

	// Checkers methods:
	
	public boolean areResourcesEnough(ResourcesOrPoints resources){
		/*as soon as one element (resources or scores) is not enough, returns false.
		 *  In order to be more general , this method checks also if faith points or victoryPoint are enough,
		 *   even if there is no payment with them. Instead , there is no need to check council privileges number 
		 */
		if(!greaterEqualThan(coins, resources.getResources().getCoins()))  
			return false;
		if(!greaterEqualThan(servants, resources.getResources().getServants()))  
			return false;
		if(!greaterEqualThan(woods, resources.getResources().getWood()))  
			return false;
		if(!greaterEqualThan(stones, resources.getResources().getStone()))  
			return false;
		if(!greaterEqualThan(victoryPoints, resources.getPoints().getVictoryPoints()))  
			return false;
		if(!greaterEqualThan(faithPoints, resources.getPoints().getFaithPoints()))  
			return false;
		if(!greaterEqualThan(militaryPoints, resources.getPoints().getMilitaryPoints()))  
			return false;
		return true;
	}
	
	private boolean greaterEqualThan(int WarehouseParameter, int test){
		/*Two uses:
		-checks if one resource (or score) in  warehouse is greater than the correspondent one in the 
		ResourcesOrPoint object(used in payments). Called by AreResourcesEnough().
		-It s used by moreThanZero , passing all 0 as "test" parameter.
		*/
		return WarehouseParameter>=test;
		
	}
	private boolean moreThanZero(){  // as soon as one element (resources or scores) is less than zero, returns false
		if(!greaterEqualThan(coins,0))  
			return false;
		if(!greaterEqualThan(servants, 0))  
			return false;
		if(!greaterEqualThan(woods, 0))  
			return false;
		if(!greaterEqualThan(stones, 0))  
			return false;
		if(!greaterEqualThan(victoryPoints, 0))
			return false;
		if(!greaterEqualThan(faithPoints, 0)) 
			return false;
		if(!greaterEqualThan(militaryPoints,0 ))  
			return false;
		return true;
	}
	
	
	// Setters methods
	
	public void add(ResourcesOrPoints resources){
		this.coins  += resources.getResources().getCoins();
		this.servants += resources.getResources().getServants();
		this.woods += resources.getResources().getWood();
		this.stones +=resources.getResources().getStone();
		this.militaryPoints +=resources.getPoints().getMilitaryPoints();
		this.victoryPoints += resources.getPoints().getVictoryPoints();
		this.faithPoints +=resources.getPoints().getFaithPoints();
		this.councilPrivileges += resources.getPoints().getCouncilPrivileges();
	}
	
	public void spendResources(ResourcesOrPoints resources)throws IllegalArgumentException{
		this.coins  -= resources.getResources().getCoins();
		this.servants -= resources.getResources().getServants();
		this.woods -= resources.getResources().getWood();
		this.stones -=resources.getResources().getStone();
		this.militaryPoints -=resources.getPoints().getMilitaryPoints();
		this.victoryPoints -= resources.getPoints().getVictoryPoints();
		this.faithPoints -=resources.getPoints().getFaithPoints();
		
		//Council privileges spending is handled in other functions . So there is not its parameter here.
		// To ensure nothing went wrong calls moreThanZero. 
		if(moreThanZero())  
			throw new IllegalArgumentException("Resources went below zero");
		
		}
	
	
	public void resetFaithPoints() {  // used in Vatican Report
		faithPoints =0;
	}
	
	public void resetCouncilPriviledges(){  // used in Council privileges handling
		councilPrivileges = 0;
	}
	
	
}
