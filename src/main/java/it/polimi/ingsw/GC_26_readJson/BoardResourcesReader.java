package it.polimi.ingsw.GC_26_readJson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class BoardResourcesReader extends DevelopmentCardsReader {

	private JsonPathData jsonPathData = new JsonPathData();
	private String[] listOfPaths;
	private BufferedReader br= null;
	private Gson gson = new Gson();
	private JsonObject jsonObject= null;
	private JsonElement jsonElement= null;
	private Type listTypeInt = new TypeToken<List<Integer>>() {}.getType();
	private List<Integer> resourcesList =  new ArrayList<Integer>();
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
		resourcesList = new Gson().fromJson(jsonObject.get("resources"), listTypeInt);
		ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(resourcesList.get(0),resourcesList.get(1),resourcesList.get(2),resourcesList.get(3),resourcesList.get(4),resourcesList.get(5),resourcesList.get(6),resourcesList.get(7));
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