package it.polimi.ingsw.GC_26_utilities.resourcesAndPoints;


/* It ' s the class that represent the status of the player in terms of resources and points owned.
 * It must not be mistaken with ResourcesOrPoints class , which instead represent the payments and effects that are called by cards, positions etc
 *ResourcesAndPoints' attributes are immutable, warehouse's attributes are mutable.  */

public class Warehouse {
	private int coins;
	private int servants;
	private int wood; 
	private int stone;
	private int victoryPoints;
	private int militaryPoints;
	private int faithPoints;
	private int councilPrivileges;
	
	public Warehouse(ResourcesOrPoints startingResources){  //initialisation
		add(startingResources);
	}
	
	public  Warehouse(Warehouse other){  /// use to copy and create temporary warehouse : useful in some parts of gameLogic
		this.coins =other.getCoins();
		this.servants=other.getServants();
		this.wood=other.getWood();
		this.stone= other.getStone();
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
		return stone;
	}
	public int getWood() {
		return wood;
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
		return coins+" coins,  "+ servants+ " servants, "+wood+ " wood, "+stone+ " stone.";
	}

	@Override
	public String toString(){
		return coins+" coins,  "+ servants+ " servants, "+wood+ " wood, "+stone+ " stone"
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
		if(!greaterEqualThan(wood, resources.getResources().getWood()))  
			return false;
		if(!greaterEqualThan(stone, resources.getResources().getStone()))  
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
		if(!greaterEqualThan(wood, 0))  
			return false;
		if(!greaterEqualThan(stone, 0))  
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
		this.wood += resources.getResources().getWood();
		this.stone +=resources.getResources().getStone();
		this.militaryPoints +=resources.getPoints().getMilitaryPoints();
		this.victoryPoints += resources.getPoints().getVictoryPoints();
		this.faithPoints +=resources.getPoints().getFaithPoints();
		this.councilPrivileges += resources.getPoints().getCouncilPrivileges();
	}
	
	public void spendResources(ResourcesOrPoints resources)throws IllegalArgumentException{
		this.coins  -= resources.getResources().getCoins();
		this.servants -= resources.getResources().getServants();
		this.wood -= resources.getResources().getWood();
		this.stone -=resources.getResources().getStone();
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
