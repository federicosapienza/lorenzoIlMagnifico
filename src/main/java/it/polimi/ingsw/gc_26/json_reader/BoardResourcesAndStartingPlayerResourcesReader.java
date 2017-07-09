package it.polimi.ingsw.gc_26.json_reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import it.polimi.ingsw.gc_26.model.game.game_components.personalBoard.PersonalBoardTile;
import it.polimi.ingsw.gc_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the reader of the resources and points of the Board and the ones owned by the player at the beginning 
 * of the game
 *
 */
public class BoardResourcesAndStartingPlayerResourcesReader extends CardsReader {

	private JsonPathData jsonPathData = new JsonPathData();
	private String[] listOfPaths;
	private BufferedReader br= null;
	private Gson gson = new Gson();
	private JsonObject jsonObject= null;
	private Logger logger;
	private Type listTypeInt = new TypeToken<List<Integer>>() {}.getType();
	private List<Integer> list =  new ArrayList<>();
	int counter=0;
	int timer;
	private FileReader fileReader;
	private ResourcesOrPoints[] territoryTowerResources = new ResourcesOrPoints[4];
	private ResourcesOrPoints[] characterTowerResources = new ResourcesOrPoints[4];
	private ResourcesOrPoints[] buildingTowerResources = new ResourcesOrPoints[4];
	private ResourcesOrPoints[] ventureTowerResources = new ResourcesOrPoints[4];
	private ResourcesOrPoints[] marketResources = new ResourcesOrPoints[4];
	private ResourcesOrPoints[] councilResources = new ResourcesOrPoints[1];
	
	/**
	 * Method that reads the resources and points of the Board from the file 
	 * @param bonusImplementation It's the object that contains all the resources and points that must be placed in the Board
	 */
	public void readResources(BonusImplementation bonusImplementation){
		listOfPaths = jsonPathData.getResources();
		for(String s:listOfPaths){
			try {
				fileReader = new FileReader(s);
				br = new BufferedReader(fileReader);
				jsonObject= gson.fromJson(br, JsonObject.class);
				} catch (FileNotFoundException e) {
				logger.log(null, "File not Found!", e);
			}
			finally {
				try {
					fileReader.close();
				} catch (IOException e2) {
				logger.log(null, "FileReader not closed!", e2);
			}
		}
		list = new Gson().fromJson(jsonObject.get("resources"), listTypeInt);
		ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5),list.get(6),list.get(7));
		addResourcesInArray(resourcesOrPoints);
			try {
				br.close();
			}
			catch (IOException e) {
				logger.log(null, "Buffered Reader not closed", e);
			}
		}
		bonusImplementation.getListOfResourcesOfPointsArray().add(territoryTowerResources);
		bonusImplementation.getListOfResourcesOfPointsArray().add(characterTowerResources);
		bonusImplementation.getListOfResourcesOfPointsArray().add(buildingTowerResources);
		bonusImplementation.getListOfResourcesOfPointsArray().add(ventureTowerResources);
		bonusImplementation.getListOfResourcesOfPointsArray().add(marketResources);
		bonusImplementation.getListOfResourcesOfPointsArray().add(councilResources);
	}

	/**
	 * Method that reads the resources and points of the player at the beginning of the game 
	 * @param bonusImplementation It's the object that contains all the resources and points that must be given to each player at
	 * the beginning of the game
	 */
	public void readStartingPlayerResources(BonusImplementation bonusImplementation){
		listOfPaths = jsonPathData.getStartingResources();
		for(String s:listOfPaths){
			try {
				fileReader = new FileReader(s);
				br = new BufferedReader(fileReader);
				jsonObject= gson.fromJson(br, JsonObject.class);
				} catch (FileNotFoundException e) {
				logger.log(null, "File not Found!", e);
			}
			finally {
				try {
					fileReader.close();
				} catch (IOException e2) {
				logger.log(null, "FileReader not closed!", e2);
			}
			}
			list = new Gson().fromJson(jsonObject.get("resources"), listTypeInt);
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResources(list.get(0),list.get(1),list.get(2),list.get(3));
			bonusImplementation.getResourcesOrPointsStarting().add(resourcesOrPoints);
			try {
				br.close();
				}
			catch (IOException e) {
				logger.log(null, "Buffered Reader not closed", e);
			}
		}
	}
	
	/**
	 * Method that, depending on the value contained in the String normalOrAdvanced contained in the parameter, creates the 
	 * correct Personal Board Tiles
	 * @param bonusImplementation It's the object to update with the correct Personal Board Tiles
	 * @param normalOrAdvanced It's the string that specifies if the Personal Board Tiles used in the game are normal or advanced
	 * @param production It indicates the bonus resources and points given by the Personal Board Tile to the player when he performs
	 * a Production action 
	 * @param harvest It indicates the bonus resources and points given by the Personal Board Tile to the player when he performs
	 * a Harvest action 
	 */
	private void createPersonalBoardTiles(BonusImplementation bonusImplementation, String normalOrAdvanced, ResourcesOrPoints production, ResourcesOrPoints harvest){
		PersonalBoardTile personalBoardTile = new PersonalBoardTile(production, harvest);
		if("normal".equals(normalOrAdvanced) || "advanced".equals(normalOrAdvanced)){
			bonusImplementation.getPersonalBoardTiles(normalOrAdvanced).add(personalBoardTile);
		}
		
	}
	
	/**
	 * Method that reads the Personal Board Tiles from json and save them in the BonusImplementation contained in the parameter
	 * @param bonusImplementation It's the object to update with the correct Personal Board Tiles
	 * @param normalOrAdvanced It's the string that specifies if the Personal Board Tiles used in the game are normal or advanced
	 */
	public void readPersonalBoardTiles(BonusImplementation bonusImplementation,String normalOrAdvanced){
		if("normal".equals(normalOrAdvanced)){
			listOfPaths = jsonPathData.getPersonalBoardTilesNormal();
		}
		if("advanced".equals(normalOrAdvanced)){
			listOfPaths = jsonPathData.getPersonalBoardTilesAdvanced();
		}
		for(String s:listOfPaths){
			try {
				fileReader = new FileReader(s);
				br = new BufferedReader(fileReader);
				jsonObject= gson.fromJson(br, JsonObject.class);
				} catch (FileNotFoundException e) {
				logger.log(null, "File not Found!", e);
			}
			finally {
				try {
					fileReader.close();
				} catch (IOException e2) {
				logger.log(null, "FileReader not closed!", e2);
				}
			}
			list = new Gson().fromJson(jsonObject.get("resourcesOrPointsProduction"), listTypeInt);
			ResourcesOrPoints resourcesOrPointsProduction = ResourcesOrPoints.newResourcesOrPoints(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5),list.get(6),list.get(7));
			list = new Gson().fromJson(jsonObject.get("resourcesOrPointsHarvest"), listTypeInt);
			ResourcesOrPoints resourcesOrPointsHarvest = ResourcesOrPoints.newResourcesOrPoints(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5),list.get(6),list.get(7));
			createPersonalBoardTiles(bonusImplementation, normalOrAdvanced, resourcesOrPointsProduction, resourcesOrPointsHarvest);
			try {
				br.close();
				}
			catch (IOException e) {
				logger.log(null, "Buffered reader not closed", e);
			}
		}		
	}
	
	/**
	 * Method that reads the Faith Points Track from json and saves it in the BonusImplementation contained in the parameter
	 * @param bonusImplementation It's the object to update with the correct Faith Points Track
	 */
	public void readFaithTrack(BonusImplementation bonusImplementation){
		List<Integer> list2;
		try {
			fileReader = new FileReader("doc/ResourcesForBoard/Faith_Point_Track/faith_point_track.json");
			br = new BufferedReader(fileReader);
			jsonObject= gson.fromJson(br, JsonObject.class);
			} catch (FileNotFoundException e) {
			logger.log(null, "File not Found!", e);
		}
		finally {
			try {
				fileReader.close();
			} catch (IOException e2) {
			logger.log(null, "FileReader not closed!", e2);
		}
		}
	list = new Gson().fromJson(jsonObject.get("position"), listTypeInt);
	list2 = new Gson().fromJson(jsonObject.get("victoryPoints"), listTypeInt);
		for(int i = 0; i<list.size() ;i++){
			bonusImplementation.getFaithTrack().put(list.get(i), list2.get(i));
		}
		try {
			br.close();
			}
		catch (IOException e) {
			logger.log(null, "Buffered reader not closed!", e);
		}
	}
	
	/**
	 * Method that reads the timers from the file and save the correct values in the TimerValueImplementation contained in the parameter
	 * @param timerValueImplementation It's the object to update with correct timers contained in json
	 */
	public void readTimers(TimerValueImplementation timerValueImplementation){
		JsonElement jsonElement = null;
		try {
			fileReader = new FileReader("doc/Timers/timer.json");
			br = new BufferedReader(fileReader);
			jsonObject= gson.fromJson(br, JsonObject.class);
			} catch (FileNotFoundException e) {
			logger.log(null, "File not Found!", e);
		}
		finally {
			try {
				fileReader.close();
			} catch (IOException e2) {
			logger.log(null, "FileReader not closed!", e2);
		}
		}
		jsonElement = jsonObject.get("startingTimer");
		timer = jsonElement.getAsInt();
		timerValueImplementation.setStartingTimer(timer);
		jsonElement = jsonObject.get("turnTimer");
		timer = jsonElement.getAsInt();
		timerValueImplementation.setTurnTimer(timer);
		jsonElement = jsonObject.get("vaticanreportTimer");
		timer = jsonElement.getAsInt();
		timerValueImplementation.setVaticanReportTimer(timer);
		try {
			br.close();
			}
		catch (IOException e) {
			logger.log(null, "Buffered reader not closed!", e);
		}
	}
	
	/**
	 * Method that adds the resources in the correct array that corresponds to a particular zone of the Board, which depends on the
	 * value of the counter
	 * @param resOrPoint It's the amount of resources and points to set in the position of the zone that corresponds to the 
	 * position of the array indicated by the counter 
	 */
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

}