package it.polimi.ingsw.GC_26_board;

import java.util.*;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class Board {
	private int numberOfPlayers;
	private Tower territoriesTower;
	private Tower buildingsTower;
	private Tower charactersTower;
	private Tower venturesTower;
	//TODO riferimenti a tutto il resto
	public Board(int numberOfPlayers, List<ResourcesOrPoints []> resourcesOrPointsList){
		this.numberOfPlayers=numberOfPlayers;	
		create(numberOfPlayers,resourcesOrPointsList);
	}
	
	public void create(int numberOfPlayers,List<ResourcesOrPoints []> resourcesOrPointsList){
		createTowers(resourcesOrPointsList);
		if(numberOfPlayers==2){
			
		}
		
		if(numberOfPlayers==3){
			
		}
		
		if(numberOfPlayers==4){
			
		}
		//TODO councilpalace= new councilpalace...
		//if(numberOfplayers>2) crea il resto
	}
	
	private void createTowers(List<ResourcesOrPoints []> resourcesOrPointsList){
		territoriesTower=new Tower(resourcesOrPointsList.get(0)); 
		buildingsTower=new Tower(resourcesOrPointsList.get(1));   
		charactersTower=new Tower(resourcesOrPointsList.get(2));  
		venturesTower=new Tower(resourcesOrPointsList.get(3));
	} 
	
	public int getNumberOfPlayers(){
		return numberOfPlayers;
	}
	                                      
	public Tower getTerritoriesTower(){
		return territoriesTower;
	}
	
	public Tower getBuildingsTower(){
		return buildingsTower;
	}
	public Tower getCharactersTower(){
		return charactersTower;
	}
	public Tower getVenturesTower(){
		return venturesTower;
	}
	
	public void endRound(){
	//territoriestower.clear 
	//market.clear ecc
	}
}
