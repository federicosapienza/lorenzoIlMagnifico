package it.polimi.ingsw.gc_26.json_reader;


import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.effects.Effect;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the reader of the Territory Cards
 *
 */
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
	
	/**
	 * Method that updates the CardsImplementation with the Territory cards read from the file
	 * @param numberOfPeriod It's the number of the period of the Territory cards to read
	 * @param cards It's the object to update with the Territory cards read from the file
	 */
	public void readCards(int numberOfPeriod,CardsImplementation cards){
		String[] listOfPaths = super.chooseListOfCards(numberOfPeriod,DevelopmentCardTypes.TERRITORYCARD);
		for(String s:listOfPaths){
			super.createJsonObjectFromFile(s);
			name=super.readString("name");
			period=super.readInt("period");
			actionValue= super.readInt("actionValue");
			doubleImmediateEffect= super.readString("doubleImmediateEffect");
			doublePermanentEffect= super.readString("doublePermanentEffect");
			if("false".equals(doubleImmediateEffect)){
					immediateEffectType= super.readString("typeOfImmediateEffect");
					immediateEffect= super.createImmediateEffect(immediateEffectType);
					}
			if("false".equals(doublePermanentEffect)){
				permanentEffectType= super.readString("typeOfPermanentEffect");
				permanentEffect=super.createPermanentEffect(permanentEffectType);
				}
			if("true".equals(doublePermanentEffect)){
				permanentEffectType = super.readString("typeOfPermanentEffect");
				permanentEffectType2= super.readString("typeOfPermanentEffect2");
				permanentEffect= super.createPermanentEffect(permanentEffectType);
				permanentTemp= super.createPermanentEffect(permanentEffectType2);
				permanentEffect = super.createDoubleEffect(permanentEffect,permanentTemp);
			}
			if("true".equals(doubleImmediateEffect)){
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

	/**
	 * Method that creates a Territory card for the period indicated by the int numOfPeriod contained in the parameter
	 * @param cards It's the object to update with the card that has been created
	 * @param numOfPeriod It's the number of period of the card that has to be created
	 */
	private void createTerritoryCard(CardsImplementation cards,int numOfPeriod){
		DevelopmentCard developmentCard= DevelopmentCardImplementation.territoryCard(name, period, null, immediateEffect, permanentEffect , actionValue);
	    cards.getDevelopmentCards(numOfPeriod, DevelopmentCardTypes.TERRITORYCARD).add(developmentCard);
	}
}

