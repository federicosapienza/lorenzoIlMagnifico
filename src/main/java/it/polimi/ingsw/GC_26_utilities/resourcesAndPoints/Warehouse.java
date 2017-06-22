package it.polimi.ingsw.GC_26_utilities.resourcesAndPoints;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_server.Observable;

/* It ' s the class that represent the status of the player in terms of resources and points owned.
 * It must not be mistaken with ResourcesOrPoints class , which instead represent the payments and effects that are called by cards, positions etc
 *ResourcesAndPoints' attributes are immutable, warehouse's attributes are mutable.  */

public class Warehouse  extends Observable<PlayerWallet> {
	/**
	 * 
	 */
	
	private final Player player;
	private final String  playerName;
	private int coins;
	private int servants;
	private int wood; 
	private int stone;
	private int victoryPoints;
	private int militaryPoints;
	private int faithPoints;
	private int councilPrivileges;
	
	public Warehouse(Player player, ResourcesOrPoints startingResources){
		if (player == null || startingResources == null) {
			throw new NullPointerException();
		}
		
		if (startingResources.getFaithPoints()!=0 || startingResources.getCouncilPrivileges()!=0 || startingResources.getVictoryPoints()!=0 || startingResources.getMilitaryPoints()!=0) {
			throw new IllegalArgumentException();
		}
		this.player = player;
		this.playerName= player.getName(); 
		add(startingResources);
	}
	
	public Warehouse(Warehouse other){
		if (other == null) {
			throw new NullPointerException("other is null");
		}
		this.playerName=other.getPlayerName();
		this.player=other.getPlayer();
		this.coins =other.getCoins();
		this.servants =other.getServants();
		this.wood= other.getWood();
		this.stone=other.getStone();
		this.victoryPoints=other.getVictoryPoints();
		this.militaryPoints=other.getMilitaryPoints();
		this.faithPoints=other.getFaithPoints();
		this.councilPrivileges=other.getCouncilPrivileges();
	}
	
	
	private Player getPlayer() {
		return player;
	}

	//getters methods
	public ResourcesOrPoints getStatus(){
		return ResourcesOrPoints.newResourcesOrPoints(coins, servants, wood, stone, victoryPoints, militaryPoints, 
				faithPoints, councilPrivileges);
	}
	
	 
	public String getPlayerName() {
		return playerName;
	}
	
	
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
	//methods used to check if resources are gone below zero
	private boolean moreThanZero(){  // as soon as one element (resources or scores) is less than zero, returns false
		if(!greaterEqualThan(coins,0)) {
			coins=0; //something went wrong , we reinitialize to make sure not negative values.
			return false;
		}
		if(!greaterEqualThan(servants, 0)){
			servants=0;
			return false;}
		if(!greaterEqualThan(wood, 0)){
				wood=0;
				return false;}
		if(!greaterEqualThan(stone, 0)){
				stone=0;
				return false;}
		if(!greaterEqualThan(victoryPoints, 0)){
				victoryPoints=0;
				return false;}
		if(!greaterEqualThan(faithPoints, 0)){
			faithPoints=0;
			return false;}
		if(!greaterEqualThan(militaryPoints,0 )){
			militaryPoints=0;
			return false;}
		if(!greaterEqualThan(councilPrivileges, 0)){
			councilPrivileges=0;
			return false;}
		return true;
	}
	
	
	
	
	
	
	// Setters methods
	
	
	
	public void add(ResourcesOrPoints resources){
		ResourcesOrPoints temp=resources;
		if(player.getPermanentModifiers().IsresourcesMalusOn())
		//check if any malus on getting resources is on (look at permanentModifier class)
		//calls the permanent effect to reduce the resources the player can earn
			temp= player.getPermanentModifiers().getResourcesAfterMalus(resources);
		
		this.coins  += temp.getResources().getCoins();
		this.servants += temp.getResources().getServants();
		this.wood += temp.getResources().getWood();
		this.stone +=temp.getResources().getStone();
		this.militaryPoints +=temp.getPoints().getMilitaryPoints();
		this.victoryPoints += temp.getPoints().getVictoryPoints();
		this.faithPoints +=temp.getPoints().getFaithPoints();
		this.councilPrivileges += temp.getPoints().getCouncilPrivileges();	
		//notify the clients
		this.notifyObservers(new PlayerWallet(this));
	}
	
	
	public void spendResources(ResourcesOrPoints resources) {//throws IllegalArgumentException
		this.coins  -= resources.getResources().getCoins();
		this.servants -= resources.getResources().getServants();
		this.wood -= resources.getResources().getWood();
		this.stone -=resources.getResources().getStone();
		this.militaryPoints -=resources.getPoints().getMilitaryPoints();
		this.victoryPoints -= resources.getPoints().getVictoryPoints();
		this.faithPoints -=resources.getPoints().getFaithPoints();
		this.councilPrivileges-=resources.getPoints().getCouncilPrivileges();
		
		// To ensure nothing went wrong calls moreThanZero. 
		if(!moreThanZero())  
			throw new IllegalArgumentException("Resources went below zero");
		
		
		//notify the clients
			notifyObservers(new PlayerWallet(this));
		}
	
	
	public void resetFaithPoints() {  // used in Vatican Report
		faithPoints =0;
		notifyObservers(new PlayerWallet(this));
		
		//notify the clients
		notifyObservers(new PlayerWallet(this));
	}
	
	public void resetCouncilPriviledges(){  // used in Council privileges handling
		councilPrivileges = 0;
		
		//notify the clients
		notifyObservers(new PlayerWallet(this));
	}
	
	
	
}
