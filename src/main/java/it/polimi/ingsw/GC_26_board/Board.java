package it.polimi.ingsw.GC_26_board;

public class Board {
	int numberOfPlayers;
	Tower territoriesTower;
	Tower buildingsTower;
	Tower charactersTower;
	Tower venturesTower;
	public Board(int numberOfPlayers,ResourcesOrPoints [] resourcesOrPointsTerritoriesTower,ResourcesOrPoints [] resourcesOrPointsBuildingsTower,ResourcesOrPoints [] resourcesOrPointsCharactersTower,ResourcesOrPoints [] resourcesOrPointsVenturesTower){
		this.numberOfPlayers=numberOfPlayers;	
		create(numberOfPlayers,resourcesOrPointsTerritoriesTower,resourcesOrPointsBuildingsTower,resourcesOrPointsCharactersTower,resourcesOrPointsVenturesTower);
	}
	
	private void create(int numberOfPlayers,ResourcesOrPoints [] resourcesOrPointsTerritoriesTower,ResourcesOrPoints [] resourcesOrPointsBuildingsTower,ResourcesOrPoints [] resourcesOrPointsCharactersTower,ResourcesOrPoints [] resourcesOrPointsVenturesTower){
		createTowers(resourcesOrPointsTerritoriesTower,resourcesOrPointsBuildingsTower,resourcesOrPointsCharactersTower,resourcesOrPointsVenturesTower);
		//councilpalace= new councilpalace...
		//if(numberOfplayers>2) crea il resto
	}
	
	public void createTowers(ResourcesOrPoints [] resourcesOrPointsTerritoriesTower,ResourcesOrPoints [] resourcesOrPointsBuildingsTower,ResourcesOrPoints [] resourcesOrPointsCharactersTower,ResourcesOrPoints [] resourcesOrPointsVenturesTower){            //16 resor points , 4 per tower
		territoriesTower=new Tower(resourcesOrPointsTerritoriesTower); 
		buildingsTower=new Tower(resourcesOrPointsBuildingsTower);   
		charactersTower=new Tower(resourcesOrPointsCharactersTower);  
		venturesTower=new Tower(resourcesOrPointsVenturesTower);
	} 
	
	public int getNumberOfPlayers(){
		return numberOfPlayers;
	}
	                                      
	public Tower getTerritoriesTower(){
		return territoriesTower;
	}//da fare per ognuno
	
	public void endRound(){
	//territoriestower.clear 
	//market.clear ecc
	}
	
		
	
	public void endGame(){
		//TODO
	}
	
	public void endPeriod(){
		//TODO
	}

}
