package it.polimi.ingsw.GC_26_utilities.resourcesAndPoints;

//the interface sent to the players: when it s used to send resources bonus on positions playerName is null, 
//Then is used to update status of the player in clients' view

public interface PlayerWallet {
	
	public String getPlayerName();
	
	public int getCoins();
	public int getServants() ;
	public int getStone();
	public int getWood() ;
	public int getMilitaryPoints();
	public int getCouncilPrivileges();
	public int getFaithPoints();
	public int getVictoryPoints();
	

}
