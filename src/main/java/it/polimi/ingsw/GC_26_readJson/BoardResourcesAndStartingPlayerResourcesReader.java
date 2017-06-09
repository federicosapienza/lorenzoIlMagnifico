package it.polimi.ingsw.GC_26_readJson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class BoardResourcesAndStartingPlayerResourcesReader extends DevelopmentCardsReader {

	private JsonPathData jsonPathData = new JsonPathData();
	private String[] listOfPaths;
	private BufferedReader br= null;
	private Gson gson = new Gson();
	private JsonObject jsonObject= null;
	private JsonElement jsonElement= null;
	private JsonElement jsonElement2= null;
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
				jsonElement = jsonObject.get("resources").getAsJsonArray();
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
			jsonElement = jsonObject.get("resources").getAsJsonArray();
			list = new Gson().fromJson(jsonObject.get("resources"), listTypeInt);
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResources(list.get(0),list.get(1),list.get(2),list.get(3));
			bonusImplementation.getResourcesOrPointsStarting().add(resourcesOrPoints);
		}
	}
	
	public void readFaithTrack(BonusImplementation bonusImplementation){
		try {
			br = new BufferedReader(new FileReader("src/ResourcesForBoard/Faith_Point_Track/faith_point_track.json"));
			jsonObject= gson.fromJson(br, JsonObject.class);
			}
		catch (FileNotFoundException e) {e.printStackTrace();}
	jsonElement = jsonObject.get("position").getAsJsonArray();
	list = new Gson().fromJson(jsonObject.get("position"), listTypeInt);
	jsonElement2 = jsonObject.get("victoryPoints").getAsJsonArray();
	list2 = new Gson().fromJson(jsonObject.get("victoryPoints"), listTypeInt);
		for(int i = 0; i<list.size() ;i++){
			bonusImplementation.getFaithTrack().put(list.get(i), list2.get(i));
		}
	}
	
	private void addResourcesInArray(ResourcesOrPoints resOrPoint){
		if(counter<4){
			territoryTowerResources[counter]=resOrPoint;
			counter++;
			System.out.println(counter);
			return;
		}
		if(counter>=4 && counter<8){
			characterTowerResources[counter -4] = resOrPoint;
			counter++;
			System.out.println(counter);
			return;
		}
		if(counter>=8 && counter<12){
			buildingTowerResources[counter -8] = resOrPoint;
			counter++;
			System.out.println(counter );
			return;
		}
		if(counter>=12 && counter<16){
			ventureTowerResources[counter -12] = resOrPoint;
			counter++;
			System.out.println(counter);
			return;
		}
		if(counter>=16 && counter<20){
			marketResources[counter -16] = resOrPoint;
			counter++;
			System.out.println(counter);
			return;
		}
		if(counter==20){
			councilResources[counter -20] = resOrPoint;
			counter++;
			System.out.println(counter);
			return;
		}
		else{ System.out.println("counter exceeded");}	
	}
	
	
	
}