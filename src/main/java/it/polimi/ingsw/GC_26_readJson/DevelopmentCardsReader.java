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
	private List<Integer> immediateTraseEffectGive1ResList = new ArrayList<Integer>();
	private List<Integer> immediateTradeEffectReceive1ResList = new ArrayList<Integer>();
	private List<Integer> immediateTradeEffectGive2ResList = new ArrayList<Integer>();
	private List<Integer> immedaiteTradeEffectReceive2ResList = new ArrayList<Integer>();
	
	private String permanentEffectType;
	private String immediateEffectType;
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
	
	public String readPermanentEffectType(){
		jsonElement= jsonObject.get("typeOfPermanentEffect");
		return permanentEffectType= jsonElement.getAsString();
	}
	
	public String readImmediateEffectType(){
		jsonElement= jsonObject.get("typeOfImmediateEffect");
		return immediateEffectType= jsonElement.getAsString();
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
			jsonElement = jsonObject.get("payment").getAsJsonArray();
			return paymentList= new Gson().fromJson(jsonObject.get("payment"), listTypeInt);
	}
	
	public List<Integer> readImmediateResourcesAndPoints(){
			jsonElement = jsonObject.get("immediateResourcesAndPoints").getAsJsonArray();
			return immediateResourcesAndPointsList = new Gson().fromJson(jsonObject.get("immediateResourcesAndPoints"), listTypeInt);
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
	
	public Effect createEffect(String effectType){
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
	
	private DevelopmentCardTypes getDevelopmentCardType(String type){
			if(type.equals("territory")){ return DevelopmentCardTypes.TERRITORYCARD;}
			if(type.equals("building")){ return DevelopmentCardTypes.BUILDINGCARD;}
			if(type.equals("character")){ return DevelopmentCardTypes.CHARACTERCARD;}
			if(type.equals("venture")){ return DevelopmentCardTypes.VENTURECARD;}
			return null;
		}
}
	
	/*public void readRightEffectAndCreateCard(String effectType,CardsImplementation cardsImplementation,int numOfPeriod){
		if(effectType.equals("singleTrade")){
											permanentTradeEffectGive1ResList=readPermanentTradeEffectGive1Resources();
											permanentTradeEffectReceive1ResList= readPermanentTradeEffectReceive1Resources();
											createBuildingCardWithSingleTradeEffect(cardsImplementation, numOfPeriod);
		}
		if(effectType.equals("doubleTrade")){
											permanentTradeEffectGive1ResList=readPermanentTradeEffectGive1Resources();
											permanentTradeEffectReceive1ResList= readPermanentTradeEffectReceive1Resources();
											permanentTradeEffectGive2ResList=readPermanentTradeEffectGive2Resources();
											permanentTradeEffectReceive2ResList=readPermanentTradeEffectReceive2Resources();
											
											}
		if(effectType.equals("cardsNumberToResources")){
											permanentCardsNumberToResourcesEffectResourcesAndPointsList=readPermanentCardsNumberToResourcesEffect();
											permanentCardsNumberToResourcesCardType=readPermanentCardsNumberToResourcesCardType();
											}
		if(effectType.equals("addResourcesAndPoints")){
											permanentResourcesAndPointsList=readPermanentResourcesAndPoints();
											}
	}*/
	
	/*private void createBuildingCardWithSingleTradeEffect(CardsImplementation cardsImplementation,int numOfPeriod){
		ReceiveResourcesOrPointsEffect immediateEffect = new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResourcesOrPoints(immediateResourcesAndPointsList.get(0), immediateResourcesAndPointsList.get(1), immediateResourcesAndPointsList.get(2), immediateResourcesAndPointsList.get(3), immediateResourcesAndPointsList.get(4), immediateResourcesAndPointsList.get(5), immediateResourcesAndPointsList.get(6), immediateResourcesAndPointsList.get(7)));
		ResourcesOrPoints resourcesOrPointsGive1= ResourcesOrPoints.newResourcesOrPoints(permanentTradeEffectGive1ResList.get(0),permanentTradeEffectGive1ResList.get(1),permanentTradeEffectGive1ResList.get(2),permanentTradeEffectGive1ResList.get(3),permanentTradeEffectGive1ResList.get(4),permanentTradeEffectGive1ResList.get(5),permanentTradeEffectGive1ResList.get(6),permanentTradeEffectGive1ResList.get(7));			
		ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(permanentTradeEffectReceive1ResList.get(0),permanentTradeEffectReceive1ResList.get(1),permanentTradeEffectReceive1ResList.get(2),permanentTradeEffectReceive1ResList.get(3),permanentTradeEffectReceive1ResList.get(4),permanentTradeEffectReceive1ResList.get(5),permanentTradeEffectReceive1ResList.get(6),permanentTradeEffectReceive1ResList.get(7));		
		ResourcesOrPoints paymentRes = ResourcesOrPoints.newResourcesOrPoints(paymentList.get(0),paymentList.get(1),paymentList.get(2),paymentList.get(3),paymentList.get(4),paymentList.get(5),paymentList.get(6),paymentList.get(7));
		Payment payment = new ResourcesPayment(paymentRes);
		TradeEffect permanentEffect = new TradeEffect(resourcesOrPointsGive1, null, resourcesOrPointsReceive1, null);
		DevelopmentCard developmentCard = DevelopmentCardImplementation.buildingCard(name, period, payment, immediateEffect, permanentEffect, actionValue);
		choosePeriod(numOfPeriod, developmentCard,cardsImplementation);
	}
	
	private void createBuildingCardWithDoubleTradeEffect(CardsImplementation cardsImplementation,int numOfPeriod){
		// devo fare in modo di poter scrivere tutti gli immediate?
	}
	
	private void choosePeriod(int numOfPeriod,DevelopmentCard developmentCard,CardsImplementation cardsImplementation){
		switch (numOfPeriod) {
		case 1:
			cardsImplementation.getBuildingCardsPeriod1().add(developmentCard);
			break;
		case 2:
			cardsImplementation.getBuildingCardsPeriod2().add(developmentCard);
			break;
		case 3:
			cardsImplementation.getBuildingCardsPeriod3().add(developmentCard);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}*/ //TODO
	

