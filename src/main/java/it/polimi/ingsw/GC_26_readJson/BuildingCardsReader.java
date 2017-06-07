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
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
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


public class BuildingCardsReader extends DevelopmentCardsReader {
		
	private String name;
	private int period;
	private List<Integer> immediateResourcesAndPointsList = new ArrayList<Integer>(); 
	private List<Integer> permanentResourcesAndPointsList = new ArrayList<Integer>();
	private List<Integer> permanentCardsNumberToResourcesEffectResourcesAndPointsList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectGive1ResList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectReceive1ResList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectGive2ResList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectReceive2ResList = new ArrayList<Integer>();
	private int actionValue;
	private String permanentEffectType;
	private String immediateEffectType;
	private String paymentType;
	private BufferedReader br= null;
	private Gson gson = new Gson();
	private JsonObject jsonObject= null;
	private JsonElement jsonElement= null;
	private Type listTypeInt = new TypeToken<List<Integer>>() {}.getType();
	//private List<DevelopmentCard> buildingCards = new ArrayList<DevelopmentCard>();//getter! -> non credo sia utile,passo i file direttamente ad un'altra classe
	private JsonPathData jsonPathData = new JsonPathData();
	private DevelopmentCardTypes permanentCardNumberToResourcesCardType;
	private String permanentCardsNumberToResourcesCardType;
	private Payment payment;
	private Effect immediateEffect;
	private Effect permanentEffect;
		
		public void readCards(int numberOfPeriod,CardsImplementation cardsImplementation){
			String[] ListOfPaths = chooseListOfCards(numberOfPeriod);
			for(String s:ListOfPaths){
				jsonObject= super.createJsonObjectFromFile(s);
				name = super.readName();
				period= super.readPeriod();
				actionValue=super.readActionValue();
				paymentType=super.readPaymentType();
				payment=super.createPayment(paymentType);
				permanentEffectType=super.readPermanentEffectType();
				immediateEffectType=super.readImmediateEffectType();
				immediateEffect = super.createImmediateEffect(immediateEffectType);
				permanentEffect = super.createPermanentEffect(permanentEffectType);
				createBuildingCard(cardsImplementation,numberOfPeriod);
				//stamp(); 
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
		
		
		
		/*private void stamp(){
			System.out.println("name : " + name);
			System.out.println("period :" + period);
			System.out.println("immediateResourcesAndPointseffect:" + immediateResourcesAndPointsList);
			System.out.println("permanentResourcesAndPointsEffect:" + permanentResourcesAndPointsList);
			System.out.println("permanentCardsNumberToResourcesEffect: " + permanentCardsNumberToResourcesEffectResourcesAndPointsList);
			System.out.println("permanentCardsNumberToResourcesEffect: " + permanentCardsNumberToResourcesCardType);
			System.out.println("permanentTradeEffectGive1: "+ permanentTradeEffectGive1ResList);
			System.out.println("permanentTradeEffectReceive1: "+ permanentTradeEffectReceive1ResList);
			System.out.println("permanentTradeEffectGive2: "+ permanentTradeEffectGive2ResList);
			System.out.println("permanentTradeEffectReceive2: "+ permanentTradeEffectReceive2ResList);
			System.out.println("actionValue: " + actionValue);
		}*/
		
		private void createBuildingCard(CardsImplementation cardsImplementation,int numOfPeriod){
			DevelopmentCard developmentCard= DevelopmentCardImplementation.buildingCard(name, numOfPeriod, payment, immediateEffect, permanentEffect, actionValue);
			switch(numOfPeriod){
			   case 1:
				   cardsImplementation.getBuildingCardsPeriod1().add(developmentCard);
				   System.out.println(cardsImplementation.getBuildingCardsPeriod1().get(0).getName());
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
		   
			}
		
		
		private String[] chooseListOfCards(int numOfList){
			switch(numOfList) {
			case 1:
				return jsonPathData.getBuildingCardsPeriod1PathArray();
			case 2:
				return jsonPathData.getBuildingCardsPeriod2PathArray();
			case 3:
				return jsonPathData.getBuildingCardsPeriod3PathArray();
			default:
				throw new IllegalArgumentException();
			}
		}
			
}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		