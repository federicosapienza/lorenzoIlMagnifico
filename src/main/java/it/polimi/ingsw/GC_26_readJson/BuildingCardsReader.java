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
	private String permanentEffectType2;
	private String immediateEffectType2;
	private String paymentType;
	private String paymentType2;
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
	private Payment paymentTemp;
	private Effect immediateEffect;
	private Effect permanentEffect;
	private Effect immediateTemp;
	private Effect permanentTemp;
	private String doubleImmediateEffect;
	private String doublePermanentEffect;
	private String doublePayment;
		
		public void readCards(int numberOfPeriod,CardsImplementation cardsImplementation){
			String[] ListOfPaths = chooseListOfCards(numberOfPeriod);
			for(String s:ListOfPaths){
				jsonObject= super.createJsonObjectFromFile(s);
				name = super.readString("name");
				period= super.readInt("period");
				actionValue=super.readInt("actionValue");
				doublePayment = super.readString("doublePayment");
				if(doublePayment.equals("false")){
					paymentType=super.readString("typeOfPayment");
					payment=super.createPayment(paymentType);
					}
				if(doublePayment.equals("true")){
					paymentType= super.readString("typeOfPayment");
					paymentType2= super.readString("typeOfPayment2");
					payment= super.createPayment(paymentType);
					paymentTemp= super.createPayment(paymentType2);
					payment = super.createDoublePayment(payment,paymentTemp);
				}
				doubleImmediateEffect= super.readString("doubleImmediateEffect");
				doublePermanentEffect= super.readString("doublePermanentEffect");
				if(doubleImmediateEffect.equals("false")){
						immediateEffectType= super.readString("typeOfImmediateEffect");
						immediateEffect= super.createImmediateEffect(immediateEffectType);
						}
				if(doublePermanentEffect.equals("false")){
					permanentEffectType= super.readString("typeOfPermanentEffect");
					permanentEffect=super.createPermanentEffect(permanentEffectType);
					}
				if(doublePermanentEffect.equals("true")){
					permanentEffectType = super.readString("typeOfPermanentEffect");
					permanentEffectType2= super.readString("typeOfPermanentEffect2");
					permanentEffect= super.createPermanentEffect(permanentEffectType);
					permanentTemp= super.createPermanentEffect(permanentEffectType2);
					permanentEffect = super.createDoubleEffect(permanentEffect,permanentTemp);
				}
				if(doubleImmediateEffect.equals("true")){
					immediateEffectType = super.readString("typeOfImmediateEffect");
					immediateEffectType2 = super.readString("typeOfImmediateEffect2");
					immediateEffect = super.createImmediateEffect(immediateEffectType);
					immediateTemp = super.createImmediateEffect(immediateEffectType2);
					immediateEffect = super.createDoubleEffect(immediateEffect, immediateTemp);
				}
				createBuildingCard(cardsImplementation,numberOfPeriod);
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

		
		private void createBuildingCard(CardsImplementation cardsImplementation,int numOfPeriod){
			DevelopmentCard developmentCard= DevelopmentCardImplementation.buildingCard(name, numOfPeriod, payment, immediateEffect, permanentEffect, actionValue);
			switch(numOfPeriod){
			   case 1:
				   cardsImplementation.getRandomDevelopmentCards(numOfPeriod, DevelopmentCardTypes.BUILDINGCARD).add(developmentCard);
				   break;
			   case 2:
				   cardsImplementation.getRandomDevelopmentCards(numOfPeriod, DevelopmentCardTypes.BUILDINGCARD).add(developmentCard);
				   break;
			   case 3:
				   cardsImplementation.getRandomDevelopmentCards(numOfPeriod, DevelopmentCardTypes.BUILDINGCARD).add(developmentCard);
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		