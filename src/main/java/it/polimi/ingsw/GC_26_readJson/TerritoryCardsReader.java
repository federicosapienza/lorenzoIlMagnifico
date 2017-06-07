package it.polimi.ingsw.GC_26_readJson;

import java.io.BufferedReader;
import java.io.IOException;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.*;

import com.google.gson.*;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.effects.ReceiveResourcesOrPointsEffect;

import java.util.ArrayList;
import java.util.List;

public class TerritoryCardsReader extends DevelopmentCardsReader {
		
	private String name;
	private int period;
	private int actionValue;
	private String permanentEffectType;
	private String immediateEffectType;
	private Effect permanentEffect;
	private Effect immediateEffect;
	private BufferedReader br= null;
	private JsonObject jsonObject= null;
	private JsonPathData jsonPathData = new JsonPathData();

		
		
		public void readCards(int numberOfPeriod,CardsImplementation cardsImplementation){
				String[] listOfPaths = chooseListOfCards(numberOfPeriod);
				for(String s:listOfPaths){
					jsonObject= super.createJsonObjectFromFile(s);
					name=super.readString("name");
					period=super.readInt("period");
					actionValue= super.readInt("actionValue");
					immediateEffectType= super.readString("typeOfImmediateEffect");
					permanentEffectType= super.readString("typeOfPermanentEffect");
					immediateEffect= super.createImmediateEffect(immediateEffectType);
					permanentEffect=super.createPermanentEffect(permanentEffectType);
					createTerritoryCard(cardsImplementation, numberOfPeriod);
					stamp(); ;
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
		}
		
		
		private void createTerritoryCard(CardsImplementation cardsImplementation,int numOfPeriod){
		    DevelopmentCard developmentCard= DevelopmentCardImplementation.territoryCard(name, period, null, immediateEffect, permanentEffect , actionValue);
		   switch(numOfPeriod){
		   case 1:
			   cardsImplementation.getTerritoryCardsPeriod1().add(developmentCard);
			   break;
		  
		   case 2:
			   cardsImplementation.getTerritoryCardsPeriod2().add(developmentCard);
			   break;
		   case 3:
			   cardsImplementation.getTerritoryCardsPeriod3().add(developmentCard);
			   break;

		   default:
			   throw new IllegalArgumentException();
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
