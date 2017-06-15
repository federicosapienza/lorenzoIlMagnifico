package it.polimi.ingsw.GC_26_readJson;


import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.effects.Effect;


public class TerritoryCardsReader extends CardsReader {
		
	private String name;
	private int period;
	private int actionValue;
	private String permanentEffectType;
	private String immediateEffectType;
	private String permanentEffectType2;
	private String immediateEffectType2;
	private Effect permanentEffect;
	private Effect immediateEffect;
	private Effect immediateTemp;
	private Effect permanentTemp;	
	private String doubleImmediateEffect;
	private String doublePermanentEffect;	
		
		public void readCards(int numberOfPeriod,CardsImplementation cards){
				String[] listOfPaths = super.chooseListOfCards(numberOfPeriod,DevelopmentCardTypes.TERRITORYCARD);
				for(String s:listOfPaths){
					super.createJsonObjectFromFile(s);
					name=super.readString("name");
					period=super.readInt("period");
					actionValue= super.readInt("actionValue");
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
						immediateEffectType = super.readString("typeOfPermanentEffect");
						immediateEffectType2 = super.readString("typeOfPermanentEffect2");
						immediateEffect = super.createImmediateEffect(immediateEffectType);
						immediateTemp = super.createImmediateEffect(immediateEffectType2);
						immediateEffect = super.createDoubleEffect(immediateEffect, immediateTemp);
					}
					createTerritoryCard(cards, numberOfPeriod);
					super.closeBufferedReader();
					}
				}
		
		
		private void createTerritoryCard(CardsImplementation cards,int numOfPeriod){
		    DevelopmentCard developmentCard= DevelopmentCardImplementation.territoryCard(name, period, null, immediateEffect, permanentEffect , actionValue);
		   switch(numOfPeriod){
		   case 1:
			   cards.getDevelopmentCards(numOfPeriod, DevelopmentCardTypes.TERRITORYCARD).add(developmentCard);
			   break;
		   case 2:
			   cards.getDevelopmentCards(numOfPeriod, DevelopmentCardTypes.TERRITORYCARD).add(developmentCard);
			   break;
		   case 3:
			   cards.getDevelopmentCards(numOfPeriod, DevelopmentCardTypes.TERRITORYCARD).add(developmentCard);
			   break;

		   default:
			   throw new IllegalArgumentException();
		   }
	   
		}


}
