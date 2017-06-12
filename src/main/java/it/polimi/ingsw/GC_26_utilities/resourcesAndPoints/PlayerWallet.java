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
	
	
	
	

}
