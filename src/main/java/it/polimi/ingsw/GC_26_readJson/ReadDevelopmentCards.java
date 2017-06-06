package it.polimi.ingsw.GC_26_readJson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.*;


import com.google.gson.*;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelpmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26_cards.leaderCard.PointsOrResourcesRequirement;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Points;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

public class ReadDevelopmentCards {
	
	private String name;
	private int period;
	private List<Integer> immediateResourcesAndPointsList = new ArrayList<Integer>(); 
	private List<Integer> permanentResourcesAndPointsList = new ArrayList<Integer>();
	private int actionValue;
	private BufferedReader br= null;
	private Gson gson = new Gson();
	private JsonObject jsonObject= null;
	private JsonElement jsonElement= null;
	private Type listTypeInt = new TypeToken<List<Integer>>() {}.getType();
	private List<DevelopmentCard> territoryCards = new ArrayList<DevelopmentCard>();//getter! -> non credo sia utile,passo i file direttamente ad un'altra classe
	private JsonPathData jsonPathData = new JsonPathData();
	
	public String readName(){
		jsonElement= jsonObject.get("name");
		return name= jsonElement.getAsString();
	}
	
	public JsonObject createJsonObjectFromFile(String path){
		try {
			br = new BufferedReader(new FileReader(path));
			return jsonObject= gson.fromJson(br, JsonObject.class);
		} catch (FileNotFoundException e) {
			   e.printStackTrace();
			   return null;
		}
	}
	
	public int readPeriod(){
		jsonElement = jsonObject.get("period");
		return period = jsonElement.getAsInt();
	}
	
	public int readActionValue(){
		jsonElement = jsonObject.get("actionValue");
		return actionValue= jsonElement.getAsInt();
	}
	
	public List<Integer> readImmediateResourcesAndPoints(){
		try {
			jsonElement = jsonObject.get("immediateResourcesAndPoints").getAsJsonArray();
			return immediateResourcesAndPointsList = new Gson().fromJson(jsonObject.get("immediateResourcesAndPoints"), listTypeInt);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return immediateResourcesAndPointsList= null;
		}
	}
	
	public List<Integer> readPermanentResourcesAndPoints(){
		try {
			jsonElement = jsonObject.get("permanentResourcesAndPoints").getAsJsonArray();
			return permanentResourcesAndPointsList = new Gson().fromJson(jsonObject.get("permanentResourcesAndPoints"), listTypeInt);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return permanentResourcesAndPointsList= null;
		}
	}
	
	
}
