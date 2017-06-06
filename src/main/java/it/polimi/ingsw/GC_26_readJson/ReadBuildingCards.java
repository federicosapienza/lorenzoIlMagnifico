package it.polimi.ingsw.GC_26_readJson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.*;


import com.google.gson.*;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelpmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26_cards.leaderCard.PointsOrResourcesRequirement;
import it.polimi.ingsw.GC_26_cards.payments.Payment;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Points;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;


public class ReadBuildingCards {
		
	private String name;
	private int period;
	private List<Integer> immediateResourcesAndPointsList = new ArrayList<Integer>(); 
	private List<Integer> permanentResourcesAndPointsList = new ArrayList<Integer>();
	private List<Integer> permanentCardsNumberToResourcesEffectResourcesAndPointsList = new ArrayList<Integer>();
	private List<Integer> paymentList = new ArrayList<Integer>();
	private int actionValue;
	private BufferedReader br= null;
	private Gson gson = new Gson();
	private JsonObject jsonObject= null;
	private JsonElement jsonElement= null;
	private Type listTypeInt = new TypeToken<List<Integer>>() {}.getType();
	//private List<DevelopmentCard> buildingCards = new ArrayList<DevelopmentCard>();//getter! -> non credo sia utile,passo i file direttamente ad un'altra classe
	private JsonPathData jsonPathData = new JsonPathData();
	private DevelopmentCardTypes permanentCardNumberToResourcesCardType;
	private String permanentCardsNumberToResourcesCardType;

		public static void main(String [] args){
			ReadBuildingCards rbc = new ReadBuildingCards();
			rbc.readCards(1);//per ora li metto manualmente qua i valori
			
		}
		
		private void readCards(int numberOfList){
			//String[] ListOfPaths = chooseListOfCards(numberOfList);
			for(int i=0;i<8;i++){
				createJsonObjectFromFile("src/Cards/Building_cards/Period_1/Mint.json");
				readName();
				readPeriod();
				readActionValue();
				readPayment();
				readImmediateResourcesAndPoints();
				readPermanentResourcesAndPoints();
				readPermanentCardsNumberToResourcesEffect();
				readPermanentCardsNumberToResourcesCardType();
				stamp(); 
				// TODO createBuildingCard();
				if(br!= null){
					try {
						br.close();
						}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		private void createJsonObjectFromFile(String path){
			try {
				br = new BufferedReader(new FileReader(path));
				jsonObject= gson.fromJson(br, JsonObject.class);
			} catch (FileNotFoundException e) {e.printStackTrace();}
		}
		
		private void readName(){
			jsonElement= jsonObject.get("name");
			name= jsonElement.getAsString();
		}
		
		private void readPeriod(){
			jsonElement = jsonObject.get("period");
			period = jsonElement.getAsInt();
		}
		
		private void readPayment(){
			try {
				jsonElement = jsonObject.get("payment").getAsJsonArray();
				paymentList = new Gson().fromJson(jsonObject.get("payment"), listTypeInt);
									  
			} catch (NullPointerException e) {
				e.printStackTrace();
				paymentList=null;
			}
			
		}
		
		private void readImmediateResourcesAndPoints(){
			try {
				jsonElement = jsonObject.get("immediateResourcesAndPoints").getAsJsonArray();
				immediateResourcesAndPointsList = new Gson().fromJson(jsonObject.get("immediateResourcesAndPoints"), listTypeInt);
									  
			} catch (NullPointerException e) {
				e.printStackTrace();
				immediateResourcesAndPointsList=null;
			}
			
		}
		
		private void readPermanentResourcesAndPoints(){
			try {
				jsonElement = jsonObject.get("permanentResourcesAndPoints").getAsJsonArray(); 
				permanentResourcesAndPointsList = new Gson().fromJson(jsonObject.get("permanentResourcesAndPoints"), listTypeInt);
				
			} catch (NullPointerException e) {
				e.printStackTrace();
				permanentResourcesAndPointsList= null; //mi servirà per scegliere poi l'effetto giusto!
			}
		}
		
		
		private void readPermanentCardsNumberToResourcesEffect(){
			try {
				jsonElement = jsonObject.get("permanentCardsNumberToResourcesResources").getAsJsonArray(); 
				permanentCardsNumberToResourcesEffectResourcesAndPointsList = new Gson().fromJson(jsonObject.get("permanentCardsNumberToResourcesResources"), listTypeInt);
				
			} catch (NullPointerException e) {
				e.printStackTrace();
				permanentCardsNumberToResourcesEffectResourcesAndPointsList= null; //mi servirà per scegliere poi l'effetto giusto!
			}
		}
		
		private void readPermanentCardsNumberToResourcesCardType(){
			try {
				jsonElement = jsonObject.get("permanentCardsNumberToResourcesCardType");
				permanentCardsNumberToResourcesCardType = jsonElement.getAsString();
			} catch (NullPointerException e) {
				permanentCardNumberToResourcesCardType=null;
				e.printStackTrace();
			}
		}
		
		
		private void readActionValue(){
			jsonElement = jsonObject.get("actionValue");
			actionValue = jsonElement.getAsInt();
		}
		
		
		private void stamp(){
			System.out.println(name);
			System.out.println(period);
			System.out.println(paymentList);
			System.out.println(immediateResourcesAndPointsList);
			System.out.println(permanentResourcesAndPointsList);
			System.out.println(permanentCardsNumberToResourcesEffectResourcesAndPointsList);
			System.out.println(permanentCardsNumberToResourcesCardType);
			System.out.println(actionValue);
		}
		
		private String[] chooseListOfCards(int numOfList){
			switch(numOfList) {
			case 1:
				return jsonPathData.getTerritoryCardsPeriod1PathArray();
			case 2:
				return jsonPathData.getTerritoryCardsPeriod2PathArray();
			case 3:
				return jsonPathData.getTerritoryCardsPeriod3PathArray();
			default:
				throw new IllegalArgumentException();
			}
		}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		