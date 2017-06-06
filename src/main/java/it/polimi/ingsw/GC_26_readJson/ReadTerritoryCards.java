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


public class ReadTerritoryCards extends ReadDevelopmentCards {
		
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
	private CardsImplementation cards= new CardsImplementation();

		public static void main(String [] args){
			ReadTerritoryCards rtc = new ReadTerritoryCards();
			rtc.readCards(3);//per ora li metto manualmente qua i valori
		}
		
		private void readCards(int numberOfPeriod){
				String[] listOfPaths = chooseListOfCards(numberOfPeriod);
				for(String s:listOfPaths){
					jsonObject= super.createJsonObjectFromFile(s);
					name=super.readName();
					period=super.readPeriod();
					actionValue= super.readActionValue();
					immediateResourcesAndPointsList=super.readImmediateResourcesAndPoints();
					permanentResourcesAndPointsList=super.readPermanentResourcesAndPoints();
					stamp(); 
					createTerritoryCard(cards,numberOfPeriod);
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
		
		private void stamp(){
			System.out.println(name);
			System.out.println(period);
			System.out.println(actionValue);
			System.out.println(immediateResourcesAndPointsList);
			System.out.println(permanentResourcesAndPointsList);
			
		}
		
		//APPUNTO PER CREAZIONE CARTE-
		//DevelpmentCardImplementation.territoryCard(name, period, new MilitaryPointPayment(toSpend, needed), 
		//new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResourcesOrPoints(coins, servants, wood, stone, victoryP, militaryP, faithP, councilP)),
		//TradeEffect(...parametrivari.), actionValue)
		
		
		private void createTerritoryCard(CardsImplementation cardsImplementation,int numOfPeriod){
		    ReceiveResourcesOrPointsEffect immediateEffect = new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResourcesOrPoints(immediateResourcesAndPointsList.get(0), immediateResourcesAndPointsList.get(1), immediateResourcesAndPointsList.get(2), immediateResourcesAndPointsList.get(3), immediateResourcesAndPointsList.get(4), immediateResourcesAndPointsList.get(5), immediateResourcesAndPointsList.get(6), immediateResourcesAndPointsList.get(7)));
			ReceiveResourcesOrPointsEffect permanentEffect = new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResourcesOrPoints(permanentResourcesAndPointsList.get(0), permanentResourcesAndPointsList.get(1), permanentResourcesAndPointsList.get(2), permanentResourcesAndPointsList.get(3), permanentResourcesAndPointsList.get(4), permanentResourcesAndPointsList.get(5), permanentResourcesAndPointsList.get(6), permanentResourcesAndPointsList.get(7)));
		    DevelopmentCard developmentCard= DevelpmentCardImplementation.territoryCard(name, period, null, immediateEffect, permanentEffect , actionValue);
		   switch(numOfPeriod){
		   case 1:
			   cardsImplementation.getTerritoryCardsPeriod1().add(developmentCard);
		   case 2:
			   cardsImplementation.getTerritoryCardsPeriod2().add(developmentCard);
		   case 3:
			   cardsImplementation.getTerritoryCardsPeriod3().add(developmentCard);
		   }
		    
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
