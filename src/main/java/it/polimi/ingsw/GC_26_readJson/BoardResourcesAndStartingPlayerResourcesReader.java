package it.polimi.ingsw.GC_26_readJson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import it.polimi.ingsw.GC_26_personalBoard.PersonalBoardTile;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class BoardResourcesAndStartingPlayerResourcesReader {

	private JsonPathData jsonPathData = new JsonPathData();
	private String[] listOfPaths;
	private BufferedReader br= null;
	private Gson gson = new Gson();
	private JsonObject jsonObject= null;
	private Type listTypeInt = new TypeToken<List<Integer>>() {}.getType();
	private List<Integer> list =  new ArrayList<Integer>();
	private List<Integer> list2 =  new ArrayList<Integer>();
	int counter=0;
	private ResourcesOrPoints[] territoryTowerResources = new ResourcesOrPoints[4];
	private ResourcesOrPoints[] characterTowerResources = new ResourcesOrPoints[4];
	private ResourcesOrPoints[] buildingTowerResources = new ResourcesOrPoints[4];
	private ResourcesOrPoints[] ventureTowerResources = new ResourcesOrPoints[4];
	private ResourcesOrPoints[] marketResources = new ResourcesOrPoints[4];
	private ResourcesOrPoints[] councilResources = new ResourcesOrPoints[1];
	
	public void readResources(BonusImplementation bonusImplementation){
		listOfPaths = jsonPathData.getResources();
		for(String s:listOfPaths){
				try {
					br = new BufferedReader(new FileReader(s));
				    jsonObject= gson.fromJson(br, JsonObject.class);
					}
				catch (FileNotFoundException e) {e.printStackTrace();}
		list = new Gson().fromJson(jsonObject.get("resources"), listTypeInt);
		ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5),list.get(6),list.get(7));
		addResourcesInArray(resourcesOrPoints);
			try {
				br.close();
				}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		bonusImplementation.getListOfResourcesOfPointsArray().add(territoryTowerResources);
		bonusImplementation.getListOfResourcesOfPointsArray().add(characterTowerResources);
		bonusImplementation.getListOfResourcesOfPointsArray().add(buildingTowerResources);
		bonusImplementation.getListOfResourcesOfPointsArray().add(ventureTowerResources);
		bonusImplementation.getListOfResourcesOfPointsArray().add(marketResources);
		bonusImplementation.getListOfResourcesOfPointsArray().add(councilResources);
	}

	public void readStartingPlayerResources(BonusImplementation bonusImplementation){
		listOfPaths = jsonPathData.getStartingResources();
		for(String s:listOfPaths){
				try {
					br = new BufferedReader(new FileReader(s));
					jsonObject= gson.fromJson(br, JsonObject.class);
					}
				catch (FileNotFoundException e) {e.printStackTrace();}
			list = new Gson().fromJson(jsonObject.get("resources"), listTypeInt);
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResources(list.get(0),list.get(1),list.get(2),list.get(3));
			bonusImplementation.getResourcesOrPointsStarting().add(resourcesOrPoints);
		}
	}
	
	public void readPersonalBoardTiles(BonusImplementation bonusImplementation,String normalOrAdvanced){
		if(normalOrAdvanced.equals("normal")){
			listOfPaths = jsonPathData.getPersonalBoardTilesNormal();
		}
		if(normalOrAdvanced.equals("advanced")){
			listOfPaths = jsonPathData.getPersonalBoardTilesAdvanced();
		}
		
		for(String s:listOfPaths){
				try {
					br = new BufferedReader(new FileReader(s));
					jsonObject= gson.fromJson(br, JsonObject.class);
					}
				catch (FileNotFoundException e) {e.printStackTrace();}
				list = new Gson().fromJson(jsonObject.get("resourcesOrPointsProduction"), listTypeInt);
				ResourcesOrPoints resourcesOrPointsProduction = ResourcesOrPoints.newResourcesOrPoints(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5),list.get(6),list.get(7));
				list = new Gson().fromJson(jsonObject.get("resourcesOrPointsHarvest"), listTypeInt);
				ResourcesOrPoints resourcesOrPointsHarvest = ResourcesOrPoints.newResourcesOrPoints(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5),list.get(6),list.get(7));
				createPersonalBoardTiles(bonusImplementation, normalOrAdvanced, resourcesOrPointsProduction, resourcesOrPointsHarvest);
			}
			
			
		}
	
	
	public void readFaithTrack(BonusImplementation bonusImplementation){
		try {
			br = new BufferedReader(new FileReader("src/ResourcesForBoard/Faith_Point_Track/faith_point_track.json"));
			jsonObject= gson.fromJson(br, JsonObject.class);
			}
		catch (FileNotFoundException e) {e.printStackTrace();}
	list = new Gson().fromJson(jsonObject.get("position"), listTypeInt);
	list2 = new Gson().fromJson(jsonObject.get("victoryPoints"), listTypeInt);
		for(int i = 0; i<list.size() ;i++){
			bonusImplementation.getFaithTrack().put(list.get(i), list2.get(i));
		}
	}
	
	
	private void addResourcesInArray(ResourcesOrPoints resOrPoint){
		if(counter<4){
			territoryTowerResources[counter]=resOrPoint;
			counter++;
			return;
		}
		if(counter>=4 && counter<8){
			characterTowerResources[counter -4] = resOrPoint;
			counter++;
			return;
		}
		if(counter>=8 && counter<12){
			buildingTowerResources[counter -8] = resOrPoint;
			counter++;
			return;
		}
		if(counter>=12 && counter<16){
			ventureTowerResources[counter -12] = resOrPoint;
			counter++;
			return;
		}
		if(counter>=16 && counter<20){
			marketResources[counter -16] = resOrPoint;
			counter++;
			return;
		}
		if(counter==20){
			councilResources[counter -20] = resOrPoint;
			counter++;
			return;
		}
		else{ throw new IllegalArgumentException();}	
	}
	
	
	private void createPersonalBoardTiles(BonusImplementation bonusImplementation,String normalOrAdvanced,ResourcesOrPoints production,ResourcesOrPoints harvest){
		PersonalBoardTile personalBoardTile = new PersonalBoardTile(production, harvest);
		if( normalOrAdvanced.equals("normal")){
			bonusImplementation.getPersonalBoardTiles(normalOrAdvanced).add(personalBoardTile);
		}
		if( normalOrAdvanced.equals("advanced")){
			bonusImplementation.getPersonalBoardTiles(normalOrAdvanced).add(personalBoardTile);
		}
		
	}
}