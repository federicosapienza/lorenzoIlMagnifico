package it.polimi.ingsw.GC_26_readJson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.*;


import com.google.gson.*;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.effects.CardsNumberToResourcesEffect;
import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26_cards.effects.TradeEffect;
import it.polimi.ingsw.GC_26_cards.leaderCard.PointsOrResourcesRequirement;
import it.polimi.ingsw.GC_26_cards.payments.MilitaryPointPayment;
import it.polimi.ingsw.GC_26_cards.payments.Payment;
import it.polimi.ingsw.GC_26_cards.payments.ResourcesPayment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

public class DevelopmentCardsReader {
	
	private String name;
	private int period;
	private List<Integer> paymentList = new ArrayList<Integer>();
	private List<Integer> permanentResourcesAndPointsList = new ArrayList<Integer>();
	private List<Integer> permanentCardsNumberToResourcesEffectResourcesAndPointsList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectGive1ResList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectReceive1ResList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectGive2ResList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectReceive2ResList = new ArrayList<Integer>();
	private List<Integer> immediateResourcesAndPointsList = new ArrayList<Integer>();
	private List<Integer> immediateCardsNumberToResourcesEffectResourcesAndPointsList = new ArrayList<Integer>();
	private List<Integer> immediateTradeEffectGive1ResList = new ArrayList<Integer>();
	private List<Integer> immediateTradeEffectReceive1ResList = new ArrayList<Integer>();
	private List<Integer> immediateTradeEffectGive2ResList = new ArrayList<Integer>();
	private List<Integer> immediateTradeEffectReceive2ResList = new ArrayList<Integer>();
	
	private String permanentEffectType;
	private String immediateEffectType;
	private String paymentType;
	private int actionValue;
	private BufferedReader br= null;
	private Gson gson = new Gson();
	private JsonObject jsonObject= null;
	private JsonElement jsonElement= null;
	private Type listTypeInt = new TypeToken<List<Integer>>() {}.getType();
	private List<DevelopmentCard> territoryCards = new ArrayList<DevelopmentCard>();//getter! -> non credo sia utile,passo i file direttamente ad un'altra classe
	private JsonPathData jsonPathData = new JsonPathData();
	private String permanentCardsNumberToResourcesCardType;
	private String immediateCardsNumberToResourcesCardType;
	
	
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
	
	public String readPermanentEffectType(){
		jsonElement= jsonObject.get("typeOfPermanentEffect");
		return permanentEffectType= jsonElement.getAsString();
	}
	
	public String readImmediateEffectType(){
		jsonElement= jsonObject.get("typeOfImmediateEffect");
		return immediateEffectType= jsonElement.getAsString();
	}
	
	public String readPaymentType(){
		jsonElement= jsonObject.get("typeOfPayment");
		return paymentType= jsonElement.getAsString();	
	}
	
	public int readPeriod(){
		jsonElement = jsonObject.get("period");
		return period = jsonElement.getAsInt();
	}
	
	public int readActionValue(){
		jsonElement = jsonObject.get("actionValue");
		return actionValue= jsonElement.getAsInt();
	}
	
	public List<Integer> readResourcesPayment(){
			jsonElement = jsonObject.get("payment").getAsJsonArray();
			return paymentList= new Gson().fromJson(jsonObject.get("payment"), listTypeInt);
	}
	
	public List<Integer> readImmediateResourcesAndPoints(){
			jsonElement = jsonObject.get("immediateResourcesAndPoints").getAsJsonArray();
			return immediateResourcesAndPointsList = new Gson().fromJson(jsonObject.get("immediateResourcesAndPoints"), listTypeInt);
	}
	
	public List<Integer> readImmediateCardsNumberToResourcesEffect(){
		jsonElement = jsonObject.get("ImmediateCardsNumberToResourcesResources").getAsJsonArray(); 
		return immediateCardsNumberToResourcesEffectResourcesAndPointsList = new Gson().fromJson(jsonObject.get("immediateCardsNumberToResourcesResources"), listTypeInt);
	}

	public String readImmediateCardsNumberToResourcesCardType(){ 
		jsonElement= jsonObject.get("immediateCardsNumberToResourcesCardType");
		return immediateCardsNumberToResourcesCardType= jsonElement.getAsString();
	}

	public List<Integer> readImmediateTradeEffectGive1Resources(){
		jsonElement = jsonObject.get("immediateTradeEffectGive1").getAsJsonArray(); 
		return immediateTradeEffectGive1ResList = new Gson().fromJson(jsonObject.get("immediateTradeEffectGive1"), listTypeInt);
	}

	public List<Integer> readImmediateTradeEffectReceive1Resources(){
		jsonElement = jsonObject.get("ImmediateTradeEffectReceive1").getAsJsonArray(); 
		return immediateTradeEffectReceive1ResList = new Gson().fromJson(jsonObject.get("ImmediateTradeEffectReceive1"), listTypeInt);
	}

	public List<Integer> readImmediateTradeEffectGive2Resources(){
		jsonElement = jsonObject.get("immediateTradeEffectGive2").getAsJsonArray(); 
		return immediateTradeEffectGive2ResList = new Gson().fromJson(jsonObject.get("immediateTradeEffectGive2"), listTypeInt);
	}

	public List<Integer> readImmediateTradeEffectReceive2Resources(){
		jsonElement = jsonObject.get("immediateTradeEffectReceive1").getAsJsonArray(); 
		return immediateTradeEffectReceive1ResList = new Gson().fromJson(jsonObject.get("immediateTradeEffectReceive2"), listTypeInt);
	}
	
	public List<Integer> readPermanentResourcesAndPoints(){
			jsonElement = jsonObject.get("permanentResourcesAndPoints").getAsJsonArray();
			return permanentResourcesAndPointsList = new Gson().fromJson(jsonObject.get("permanentResourcesAndPoints"), listTypeInt);
	}
	
	public List<Integer> readPermanentCardsNumberToResourcesEffect(){
			jsonElement = jsonObject.get("permanentCardsNumberToResourcesResources").getAsJsonArray(); 
			return permanentCardsNumberToResourcesEffectResourcesAndPointsList = new Gson().fromJson(jsonObject.get("permanentCardsNumberToResourcesResources"), listTypeInt);
	}
	
	public String readPermanentCardsNumberToResourcesCardType(){ 
			jsonElement= jsonObject.get("permanentCardsNumberToResourcesCardType");
			return permanentCardsNumberToResourcesCardType= jsonElement.getAsString();
	}
	
	public List<Integer> readPermanentTradeEffectGive1Resources(){
			jsonElement = jsonObject.get("permanentTradeEffectGive1").getAsJsonArray(); 
			return permanentTradeEffectGive1ResList = new Gson().fromJson(jsonObject.get("permanentTradeEffectGive1"), listTypeInt);
	}
	
	public List<Integer> readPermanentTradeEffectReceive1Resources(){
			jsonElement = jsonObject.get("permanentTradeEffectReceive1").getAsJsonArray(); 
			return permanentTradeEffectReceive1ResList = new Gson().fromJson(jsonObject.get("permanentTradeEffectReceive1"), listTypeInt);
	}
	
	public List<Integer> readPermanentTradeEffectGive2Resources(){
			jsonElement = jsonObject.get("permanentTradeEffectGive2").getAsJsonArray(); 
			return permanentTradeEffectGive2ResList = new Gson().fromJson(jsonObject.get("permanentTradeEffectGive2"), listTypeInt);
	}
	
	public List<Integer> readPermanentTradeEffectReceive2Resources(){
			jsonElement = jsonObject.get("permanentTradeEffectReceive1").getAsJsonArray(); 
			return permanentTradeEffectReceive1ResList = new Gson().fromJson(jsonObject.get("permanentTradeEffectReceive2"), listTypeInt);
	}
	
	public Effect createPermanentEffect(String effectType){
		if(effectType.equals("singleTrade")){
			permanentTradeEffectGive1ResList=readPermanentTradeEffectGive1Resources();
			permanentTradeEffectReceive1ResList= readPermanentTradeEffectReceive1Resources();
			ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(permanentTradeEffectGive1ResList.get(0),permanentTradeEffectGive1ResList.get(1),permanentTradeEffectGive1ResList.get(2),permanentTradeEffectGive1ResList.get(3),permanentTradeEffectGive1ResList.get(4),permanentTradeEffectGive1ResList.get(5),permanentTradeEffectGive1ResList.get(6),permanentTradeEffectGive1ResList.get(7));
			ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(permanentTradeEffectReceive1ResList.get(0),permanentTradeEffectReceive1ResList.get(1),permanentTradeEffectReceive1ResList.get(2),permanentTradeEffectReceive1ResList.get(3),permanentTradeEffectReceive1ResList.get(4),permanentTradeEffectReceive1ResList.get(5),permanentTradeEffectReceive1ResList.get(6),permanentTradeEffectReceive1ResList.get(7));
			TradeEffect Effect = new TradeEffect(resourcesOrPointsGive1, null, resourcesOrPointsReceive1, null);
			return Effect;
			}
		if(effectType.equals("doubleTrade")){
			permanentTradeEffectGive1ResList=readPermanentTradeEffectGive1Resources();
			permanentTradeEffectReceive1ResList= readPermanentTradeEffectReceive1Resources();
			permanentTradeEffectGive2ResList=readPermanentTradeEffectGive2Resources();
			permanentTradeEffectReceive2ResList=readPermanentTradeEffectReceive2Resources();
			ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(permanentTradeEffectGive1ResList.get(0),permanentTradeEffectGive1ResList.get(1),permanentTradeEffectGive1ResList.get(2),permanentTradeEffectGive1ResList.get(3),permanentTradeEffectGive1ResList.get(4),permanentTradeEffectGive1ResList.get(5),permanentTradeEffectGive1ResList.get(6),permanentTradeEffectGive1ResList.get(7));
			ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(permanentTradeEffectReceive1ResList.get(0),permanentTradeEffectReceive1ResList.get(1),permanentTradeEffectReceive1ResList.get(2),permanentTradeEffectReceive1ResList.get(3),permanentTradeEffectReceive1ResList.get(4),permanentTradeEffectReceive1ResList.get(5),permanentTradeEffectReceive1ResList.get(6),permanentTradeEffectReceive1ResList.get(7));
			ResourcesOrPoints resourcesOrPointsGive2 = ResourcesOrPoints.newResourcesOrPoints(permanentTradeEffectGive2ResList.get(0),permanentTradeEffectGive2ResList.get(1),permanentTradeEffectGive2ResList.get(2),permanentTradeEffectGive2ResList.get(3),permanentTradeEffectGive2ResList.get(4),permanentTradeEffectGive2ResList.get(5),permanentTradeEffectGive2ResList.get(6),permanentTradeEffectGive2ResList.get(7));
			ResourcesOrPoints resourcesOrPointsReceive2 = ResourcesOrPoints.newResourcesOrPoints(permanentTradeEffectReceive2ResList.get(0),permanentTradeEffectReceive2ResList.get(1),permanentTradeEffectReceive2ResList.get(2),permanentTradeEffectReceive2ResList.get(3),permanentTradeEffectReceive2ResList.get(4),permanentTradeEffectReceive2ResList.get(5),permanentTradeEffectReceive2ResList.get(6),permanentTradeEffectReceive2ResList.get(7));
			TradeEffect Effect = new TradeEffect(resourcesOrPointsGive1, resourcesOrPointsGive2, resourcesOrPointsReceive1, resourcesOrPointsReceive2);
			return Effect;
			}
		if(effectType.equals("cardsNumberToResources")){
			permanentCardsNumberToResourcesEffectResourcesAndPointsList=readPermanentCardsNumberToResourcesEffect();
			permanentCardsNumberToResourcesCardType=readPermanentCardsNumberToResourcesCardType();
			DevelopmentCardTypes type = getDevelopmentCardType(permanentCardsNumberToResourcesCardType);
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(0),permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(1),permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(2),permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(3),permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(4),permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(5),permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(6),permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(7));
			CardsNumberToResourcesEffect Effect = new CardsNumberToResourcesEffect(type, resourcesOrPoints);
			return Effect;
			}
		if(effectType.equals("addResourcesAndPoints")){
			permanentResourcesAndPointsList=readPermanentResourcesAndPoints();
			ReceiveResourcesOrPointsEffect Effect= new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResourcesOrPoints(permanentResourcesAndPointsList.get(0),permanentResourcesAndPointsList.get(1),permanentResourcesAndPointsList.get(2),permanentResourcesAndPointsList.get(3),permanentResourcesAndPointsList.get(4),permanentResourcesAndPointsList.get(5),permanentResourcesAndPointsList.get(6),permanentResourcesAndPointsList.get(7)));
			return Effect;
			}
		return null;
	}
	
	public Payment createPayment(String paymentType){
		if(paymentType.equals("resources")){
			paymentList = readResourcesPayment();
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResources(paymentList.get(0),paymentList.get(1),paymentList.get(2),paymentList.get(3));
			ResourcesPayment payment = new ResourcesPayment(resourcesOrPoints);
			return payment;
		}
		/*if(paymentType.equals("militaryPoints")){
			MilitaryPointPayment payment = new MilitaryPointPayment(toSpend, needed);// direi che prima di fare ciò devo leggere le carte.
			return payment;
		}*/
		return null;
	}
	
	public Effect createImmediateEffect(String effectType){
		if(effectType.equals("singleTrade")){
			immediateTradeEffectGive1ResList=readImmediateTradeEffectGive1Resources();
			immediateTradeEffectReceive1ResList= readImmediateTradeEffectReceive1Resources();
			ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(immediateTradeEffectGive1ResList.get(0),immediateTradeEffectGive1ResList.get(1),immediateTradeEffectGive1ResList.get(2),immediateTradeEffectGive1ResList.get(3),immediateTradeEffectGive1ResList.get(4),immediateTradeEffectGive1ResList.get(5),immediateTradeEffectGive1ResList.get(6),immediateTradeEffectGive1ResList.get(7));
			ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(immediateTradeEffectReceive1ResList.get(0),immediateTradeEffectReceive1ResList.get(1),immediateTradeEffectReceive1ResList.get(2),immediateTradeEffectReceive1ResList.get(3),immediateTradeEffectReceive1ResList.get(4),immediateTradeEffectReceive1ResList.get(5),immediateTradeEffectReceive1ResList.get(6),immediateTradeEffectReceive1ResList.get(7));
			TradeEffect Effect = new TradeEffect(resourcesOrPointsGive1, null, resourcesOrPointsReceive1, null);
			return Effect;
			}
		if(effectType.equals("doubleTrade")){
			immediateTradeEffectGive1ResList=readImmediateTradeEffectGive1Resources();
			immediateTradeEffectReceive1ResList= readImmediateTradeEffectReceive1Resources();
			immediateTradeEffectGive2ResList=readImmediateTradeEffectGive2Resources();
			immediateTradeEffectReceive2ResList= readImmediateTradeEffectReceive2Resources();
			ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(immediateTradeEffectGive1ResList.get(0),immediateTradeEffectGive1ResList.get(1),immediateTradeEffectGive1ResList.get(2),immediateTradeEffectGive1ResList.get(3),immediateTradeEffectGive1ResList.get(4),immediateTradeEffectGive1ResList.get(5),immediateTradeEffectGive1ResList.get(6),immediateTradeEffectGive1ResList.get(7));
			ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(immediateTradeEffectReceive1ResList.get(0),immediateTradeEffectReceive1ResList.get(1),immediateTradeEffectReceive1ResList.get(2),immediateTradeEffectReceive1ResList.get(3),immediateTradeEffectReceive1ResList.get(4),immediateTradeEffectReceive1ResList.get(5),immediateTradeEffectReceive1ResList.get(6),immediateTradeEffectReceive1ResList.get(7));
			ResourcesOrPoints resourcesOrPointsGive2 = ResourcesOrPoints.newResourcesOrPoints(immediateTradeEffectGive2ResList.get(0),immediateTradeEffectGive2ResList.get(1),immediateTradeEffectGive2ResList.get(2),immediateTradeEffectGive2ResList.get(3),immediateTradeEffectGive2ResList.get(4),immediateTradeEffectGive2ResList.get(5),immediateTradeEffectGive2ResList.get(6),immediateTradeEffectGive2ResList.get(7));
			ResourcesOrPoints resourcesOrPointsReceive2 = ResourcesOrPoints.newResourcesOrPoints(immediateTradeEffectReceive2ResList.get(0),immediateTradeEffectReceive2ResList.get(1),immediateTradeEffectReceive2ResList.get(2),immediateTradeEffectReceive2ResList.get(3),immediateTradeEffectReceive2ResList.get(4),immediateTradeEffectReceive2ResList.get(5),immediateTradeEffectReceive2ResList.get(6),immediateTradeEffectReceive2ResList.get(7));	
			TradeEffect Effect = new TradeEffect(resourcesOrPointsGive1, resourcesOrPointsGive2, resourcesOrPointsReceive1, resourcesOrPointsReceive2);
			return Effect;
			}
		if(effectType.equals("cardsNumberToResources")){
			immediateCardsNumberToResourcesEffectResourcesAndPointsList=readImmediateCardsNumberToResourcesEffect();
			immediateCardsNumberToResourcesCardType=readImmediateCardsNumberToResourcesCardType();
			DevelopmentCardTypes type = getDevelopmentCardType(immediateCardsNumberToResourcesCardType);
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(0),immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(1),immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(2),immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(3),immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(4),immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(5),immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(6),immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(7));
			CardsNumberToResourcesEffect Effect = new CardsNumberToResourcesEffect(type, resourcesOrPoints);
			return Effect;
			}
		if(effectType.equals("addResourcesAndPoints")){
			immediateResourcesAndPointsList=readImmediateResourcesAndPoints();
			ReceiveResourcesOrPointsEffect Effect= new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResourcesOrPoints(immediateResourcesAndPointsList.get(0),immediateResourcesAndPointsList.get(1),immediateResourcesAndPointsList.get(2),immediateResourcesAndPointsList.get(3),immediateResourcesAndPointsList.get(4),immediateResourcesAndPointsList.get(5),immediateResourcesAndPointsList.get(6),immediateResourcesAndPointsList.get(7)));
			return Effect;
			}
		return null;
	}
	
	//forse ho duplicato le variabili senza motivo... intendo immediate e permanent.
	
	private DevelopmentCardTypes getDevelopmentCardType(String type){
			if(type.equals("territory")){ return DevelopmentCardTypes.TERRITORYCARD;}
			if(type.equals("building")){ return DevelopmentCardTypes.BUILDINGCARD;}
			if(type.equals("character")){ return DevelopmentCardTypes.CHARACTERCARD;}
			if(type.equals("venture")){ return DevelopmentCardTypes.VENTURECARD;}
			return null;
		}
}
	


