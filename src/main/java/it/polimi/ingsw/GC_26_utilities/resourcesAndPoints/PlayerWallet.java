package it.polimi.ingsw.GC_26_utilities.resourcesAndPoints;

import java.io.Serializable;


//the message class sent to the players for describing player' resources and points 
//Then is used to update status of the player in clients' view

public  class PlayerWallet  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String  playerName;
	private final int coins;
	private final int servants;
	private final int wood; 
	private final int stone;
	private final int victoryPoints;
	private final int militaryPoints;
	private final int faithPoints;
	private final int councilPrivileges;
	
	public PlayerWallet(Warehouse warehouse) {
		this.playerName=warehouse.getPlayerName();
		this.coins =warehouse.getCoins();
		this.servants =warehouse.getServants();
		this.wood= warehouse.getWood();
		this.stone=warehouse.getWood();
		this.victoryPoints=warehouse.getVictoryPoints();
		this.militaryPoints=warehouse.getMilitaryPoints();
		this.faithPoints=warehouse.getFaithPoints();
		this.councilPrivileges=warehouse.getCouncilPrivileges();
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
