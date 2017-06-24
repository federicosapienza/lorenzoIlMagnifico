package it.polimi.ingsw.GC_26_utilities.resourcesAndPoints;

import java.io.Serializable;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * This class represents the message sent to the players, containing the status of the resources and points of the current
 * player.
 * Then it updates the status of the player in the clients' view.  
 *
 */


public class PlayerWallet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String playerName;
	private final int coins;
	private final int servants;
	private final int wood; 
	private final int stone;
	private final int victoryPoints;
	private final int militaryPoints;
	private final int faithPoints;
	private final int councilPrivileges;
	
	/**
	 * Constructor: it creates a player wallet with the characteristics of the warehouse expressed in the parameter. 
	 * @param warehouse It's the warehouse that the player will get
	 */
	public PlayerWallet(Warehouse warehouse) {
		this.playerName=warehouse.getPlayerName();
		this.coins =warehouse.getCoins();
		this.servants =warehouse.getServants();
		this.wood= warehouse.getWood();
		this.stone=warehouse.getStone();
		this.victoryPoints=warehouse.getVictoryPoints();
		this.militaryPoints=warehouse.getMilitaryPoints();
		this.faithPoints=warehouse.getFaithPoints();
		this.councilPrivileges=warehouse.getCouncilPrivileges();
	}
		
	/**
	 * Getter method that returns the name of the player
	 * @return the name of the player
	 */
	
	public String getPlayerName() {
		return playerName;
	}
	
	/**
	 * Getter method that returns the number of coins of the current player
	 * @return the current player's number of coins
	 */
	public int getCoins() {
		return coins;
	}
	
	/**
	 * Getter method that returns the number of servants of the current player
	 * @return the current player's number of servants 
	 */
	public int getServants() {
		return servants;
	}
	
	/**
	 * Getter method that returns the number of stones of the current player
	 * @return the current player's number of stones 
	 */
	public int getStone() {
		return stone;
	}
	
	/**
	 * Getter method that returns the number of wood of the current player
	 * @return the current player's number of wood 
	 */
	public int getWood() {
		return wood;
	}
	
	/**
	 * Getter method that returns the number of Military Points of the current player
	 * @return the current player's number of Military Points 
	 */
	public int getMilitaryPoints() {
		return militaryPoints;
	}
	
	/**
	 * Getter method that returns the number of Council Privileges of the current player
	 * @return the current player's number of Council Privileges 
	 */
	public int getCouncilPrivileges() {
		return councilPrivileges;
	}
	
	/**
	 * Getter method that returns the number of Faith Points of the current player
	 * @return the current player's number of Faith Points 
	 */
	public int getFaithPoints() {
		return faithPoints;
	}
	
	/**
	 * Getter method that returns the number of Victory Points of the current player
	 * @return the current player's number of Victory Points 
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}
	
	@Override
	
	public boolean equals(Object obj) {
	    if (this == obj)               
	        return true;
	    if (obj == null)               
	        return false;
	    if (getClass() != obj.getClass())   
	        return false;
	    PlayerWallet other = (PlayerWallet) obj;                
	    if (this.getPlayerName() != other.getPlayerName() || this.getCoins()!=other.getCoins() || this.getServants()!=other.getServants()
	    		 || this.getWood()!=other.getWood() || this.getStone()!=other.getStone() || this.getMilitaryPoints()!= other.getMilitaryPoints()
	    		 || this.getVictoryPoints()!=other.getVictoryPoints() || this.getFaithPoints()!=other.getFaithPoints()
	    		 || this.getCouncilPrivileges()!=other.getCouncilPrivileges())        
	        return false;                 
	    return true; 

	}
	
	public String toStringResources() {
		return coins+" coins"+ servants +" servants" + stone +" stone"+wood+ " wood";
	}
	public String toStringPoints() {
		return victoryPoints+" victory points"+ militaryPoints +" military points" + faithPoints +" faith points"+ councilPrivileges + " council privileges";
	}
	

	@Override
	public String toString() {
		
		return playerName+": " + toStringResources()+ " "+toStringPoints();
		}
}
