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
	private List<Integer> paymentList = new ArrayList<Integer>();
	private List<Integer> permanentCardsNumberToResourcesEffectResourcesAndPointsList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectGive1ResList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectReceive1ResList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectGive2ResList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectReceive2ResList = new ArrayList<Integer>();

	private int actionValue;
	private BufferedReader br= null;
	private Gson gson = new Gson();
	private JsonObject jsonObject= null;
	private JsonElement jsonElement= null;
	private Type listTypeInt = new TypeToken<List<Integer>>() {}.getType();
	private List<DevelopmentCard> territoryCards = new ArrayList<DevelopmentCard>();//getter! -> non credo sia utile,passo i file direttamente ad un'altra classe
	private JsonPathData jsonPathData = new JsonPathData();
	private String permanentCardsNumberToResourcesCardType;
	
	
	
	public JsonObject createJsonObjectFromFile(String path){
		try {
			br = new BufferedReader(new FileReader(path));
			return jsonObject= gson.fromJson(br, JsonObject.class);
		} catch (FileNotFoundException e) {
			   e.printStackTrace();
			   return null;
		}
	}
	
	public String readName(){
		jsonElement= jsonObject.get("name");
		return name= jsonElement.getAsString();
	}
	
	public int readPeriod(){
		jsonElement = jsonObject.get("period");
		return period = jsonElement.getAsInt();
	}
	
	public int readActionValue(){
		jsonElement = jsonObject.get("actionValue");
		return actionValue= jsonElement.getAsInt();
	}
	
	public List<Integer> readPayment(){
		try {
			jsonElement = jsonObject.get("payment").getAsJsonArray();
			return paymentList= new Gson().fromJson(jsonObject.get("payment"), listTypeInt);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return paymentList= null;
		}
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
	
	public List<Integer> readPermanentCardsNumberToResourcesEffect(){
		try {
			jsonElement = jsonObject.get("permanentCardsNumberToResourcesResources").getAsJsonArray(); 
			return permanentCardsNumberToResourcesEffectResourcesAndPointsList = new Gson().fromJson(jsonObject.get("permanentCardsNumberToResourcesResources"), listTypeInt);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return permanentCardsNumberToResourcesEffectResourcesAndPointsList= null; //mi servirà per scegliere poi l'effetto giusto!
		}
	}
	
	public String readPermanentCardsNumberToResourcesCardType(){
		try { 
			jsonElement= jsonObject.get("permanentCardsNumberToResourcesCardType");
			return permanentCardsNumberToResourcesCardType= jsonElement.getAsString();
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return permanentCardsNumberToResourcesCardType= null; //mi servirà per scegliere poi l'effetto giusto!
		}
	}
	
	public List<Integer> readPermanentTradeEffectGive1Resources(){
		try {
			jsonElement = jsonObject.get("permanentTradeEffectGive1").getAsJsonArray(); 
			return permanentTradeEffectGive1ResList = new Gson().fromJson(jsonObject.get("permanentTradeEffectGive1"), listTypeInt);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return permanentTradeEffectGive1ResList= null; //mi servirà per scegliere poi l'effetto giusto!
		}
	}
	
	public List<Integer> readPermanentTradeEffectReceive1Resources(){
		try {
			jsonElement = jsonObject.get("permanentTradeEffectReceive1").getAsJsonArray(); 
			return permanentTradeEffectReceive1ResList = new Gson().fromJson(jsonObject.get("permanentTradeEffectReceive1"), listTypeInt);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return permanentTradeEffectReceive1ResList= null; //mi servirà per scegliere poi l'effetto giusto!
		}
	}
	
	public List<Integer> readPermanentTradeEffectGive2Resources(){
		try {
			jsonElement = jsonObject.get("permanentTradeEffectGive2").getAsJsonArray(); 
			return permanentTradeEffectGive2ResList = new Gson().fromJson(jsonObject.get("permanentTradeEffectGive2"), listTypeInt);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return permanentTradeEffectGive2ResList= null; //mi servirà per scegliere poi l'effetto giusto!
		}
	}
	
	public List<Integer> readPermanentTradeEffectReceive2Resources(){
		try {
			jsonElement = jsonObject.get("permanentTradeEffectReceive1").getAsJsonArray(); 
			return permanentTradeEffectReceive1ResList = new Gson().fromJson(jsonObject.get("permanentTradeEffectReceive1"), listTypeInt);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return permanentTradeEffectReceive1ResList= null; //mi servirà per scegliere poi l'effetto giusto!
		}
	}
	
	
}
