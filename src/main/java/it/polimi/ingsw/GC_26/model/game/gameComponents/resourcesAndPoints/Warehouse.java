package it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints;

import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.utilities.exceptions.NotEnoughResourcesExceptions;
import it.polimi.ingsw.GC_26.utilities.observer.Observable;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the status of the player in terms of resources and points owned.
 * It must not be mistaken with ResourcesOrPoints class, which instead represents the payments, requirements and effects that are 
 * called by cards, positions, etc.
 * The attributes of ResourcesAndPoints are immutable, while the attributes of Warehouse are mutable. 
 *
 */


public class Warehouse  extends Observable<PlayerWallet> {

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
	
	/**
	 * Constructor: it creates a Warehouse with the starting resources and points for the player contained in the parameter
	 * @param player It's the player who is getting the warehouser
	 * @param startingResources the ResourcesOrPoints to add in the new Warehouse
	 */
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
	
	/**
	 * Method that creates a copy of the Warehouse contained in the parameter
	 * @param other the Warehouse to copy
	 */
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
	
	/**
	 * Method that returns the player who owns this Warehouse
	 * @return the player who owns this Warehouse
	 */
	private Player getPlayer() {
		return player;
	}

	/**
	 * Method that returns the resources and points actually contained in this Warehouse 
	 * @return the resources and points actually contained in this Warehouse 
	 */
	public ResourcesOrPoints getStatus(){
		return ResourcesOrPoints.newResourcesOrPoints(coins, servants, wood, stone, victoryPoints, militaryPoints, 
				faithPoints, councilPrivileges);
	}
	
	 /**
	  * Method that returns the name of the player who owns this Warehouse
	  * @return the name of the player who owns this Warehouse
	  */
	public String getPlayerName() {
		return playerName;
	}
	
	/**
	 * Method that returns the coins contained in this Warehouse
	 * @return the coins contained in this Warehouse
	 */
	public int getCoins() {
		return coins;
	}
	
	/**
	 * Method that returns the servants contained in this Warehouse
	 * @return the servants contained in this Warehouse
	 */
	public int getServants() {
		return servants;
	}
	
	/**
	 * Method that returns the stones contained in this Warehouse
	 * @return the stones contained in this Warehouse
	 */
	public int getStone() {
		return stone;
	}
	
	/**
	 * Method that returns the wood contained in this Warehouse
	 * @return the wood contained in this Warehouse
	 */
	public int getWood() {
		return wood;
	}
	
	/**
	 * Method that returns the Military Points contained in this Warehouse
	 * @return the Military Points contained in this Warehouse
	 */
	public int getMilitaryPoints() {
		return militaryPoints;
	}
	
	/**
	 * Method that returns the Council Privileges contained in this Warehouse
	 * @return the Council Privileges contained in this Warehouse
	 */
	public int getCouncilPrivileges() {
		return councilPrivileges;
	}
	
	/**
	 * Method that returns the Faith Points contained in this Warehouse
	 * @return the Faith Points contained in this Warehouse
	 */
	public int getFaithPoints() {
		return faithPoints;
	}
	
	/**
	 * Method that returns the Victory Points contained in this Warehouse
	 * @return the Victory Points contained in this Warehouse
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}
	
	/**
	 * Method that describes the amount of resources contained in this Warehouse, as a String value
	 * @return the description of the amount of resources contained in this Warehouse, as a String value
	 */
	public String ResourcesStatus() {// String that describes only the status of resources owned by the player
		return coins+" coins,  "+ servants+ " servants, "+wood+ " wood, "+stone+ " stone.";
	}

	/**
	 * Method that describes this Warehouse as a String value
	 */
	@Override
	public String toString(){
		return coins+" coins,  "+ servants+ " servants, "+wood+ " wood, "+stone+ " stone, "
	    		+ victoryPoints+ " victoryPoints, "+  militaryPoints+" military points, " 
	    		+ faithPoints+ " faith points, " + councilPrivileges +" council priviledges.";
	}

	/**
	 * Method that describes the amount of Military points contained in this Warehouse as a String value
	 * @return the amount of Military points contained in this Warehouse as a String value
	 */
	public String MilitaryPointStatus(){
		return militaryPoints +" military points";
	}

	
	/**
	 * Method that checks if the resources and points contained in this Warehouse are enough to satisfy the requirement represented
	 * by the ResourcesOrPoints contained in the parameter
	 * @param resources the ResourcesOrPoints that represent the requirement
	 * @return true if the requirement is satisfied; false if it isn't
	 */
	public boolean areResourcesEnough(ResourcesOrPoints resources){
		/*as soon as one element (resources or scores) is not enough, returns false.
		 * Note that there is no need to check council privileges number 
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
		return greaterEqualThan(militaryPoints, resources.getPoints().getMilitaryPoints())  ;
	}
	
	/**
	 * Method that checks if the amount of one particular resource or point in the Warehouse, represented by the first parameter int 
	 * WarehouseParameter is greater or equal to the corresponding one in the ResourcesOrPoints object, represented by the 
	 * second int test. 
	 * @param WarehouseParameter It's the amount of one particular resource or point in the Warehouse
	 * @param test It's the corresponding amount of the resource or point of the ResourcesOrPoints object that has to be compared
	 * with the Warehouse
	 * @return true if the first parameter is greater or equal to the second one; false if it isn't
	 */
	private boolean greaterEqualThan(int WarehouseParameter, int test){
		
		return WarehouseParameter>=test;
		
	}
	
	
	/**
	 * Method used to check if resources are less than zero
	 * @return false if any of the resources and points contained in this Warehouse are less than zero; else true
	 */
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
	
	
	/**
	 * Method called to add resources or points contained in the parameter to this Warehouse
	 * @param resources the ResourcesOrPoints to add in this Warehouse
	 */
	public void add(ResourcesOrPoints resources){
		ResourcesOrPoints temp=resources;
		if(player.getPermanentModifiers().isResourcesMalusOn())
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
	
	/**
	 * Method called to spend the resources and points contained in the parameter
	 * @param resources ResourcesOrPoints to subtract from the ones contained in this Warehouse
	 */
	public void spendResources(ResourcesOrPoints resources) {
		this.coins  -= resources.getResources().getCoins();
		this.servants -= resources.getResources().getServants();
		this.wood -= resources.getResources().getWood();
		this.stone -=resources.getResources().getStone();
		this.militaryPoints -=resources.getPoints().getMilitaryPoints();
		this.victoryPoints -= resources.getPoints().getVictoryPoints();
		this.faithPoints -=resources.getPoints().getFaithPoints();
		this.councilPrivileges-=resources.getPoints().getCouncilPrivileges();
		
		// To ensure nothing went wrong calls moreThanZero. 
		if(!moreThanZero())  {
			throw new NotEnoughResourcesExceptions("Resources went below zero");
		}
		//notify the clients
			notifyObservers(new PlayerWallet(this));
		}
	
	/**
	 * Method used during the Vatican Report phase: it resets the Faith Points to 0.
	 */
	public void resetFaithPoints() {  
		faithPoints =0;
		notifyObservers(new PlayerWallet(this));
		
		//notify the clients
		notifyObservers(new PlayerWallet(this));
	}
	
	/**
	 * Method used during the handling of Council Privileges: it resets the Council Privileges to 0.
	 */
	public void resetCouncilPrivileges(){  
		councilPrivileges = 0;
		
		//notify the clients
		notifyObservers(new PlayerWallet(this));
	}
	
	
	
}
