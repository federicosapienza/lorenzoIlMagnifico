package it.polimi.ingsw.gc_26.json_reader;


import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.effects.Effect;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.payments.common.Payment;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the reader for the Building cards contained in json
 *
 */
public class BuildingCardsReader extends CardsReader {
		
	private String name;
	private int period;
	private int actionValue;
	private String permanentEffectType;
	private String immediateEffectType;
	private String permanentEffectType2;
	private String immediateEffectType2;
	private String paymentType;
	private String paymentType2;
	private Payment payment;
	private Payment paymentTemp;
	private Effect immediateEffect;
	private Effect permanentEffect;
	private Effect immediateTemp;
	private Effect permanentTemp;
	private String doubleImmediateEffect;
	private String doublePermanentEffect;
	private String doublePayment;
	
	/**
	 * Method that updates the CardsImplementation with the Building cards read from Json
	 * @param numberOfPeriod It's the number of period of each card
	 * @param cardsImplementation It's the object to update with the cards that have been created
	 */
	public void readCards(int numberOfPeriod, CardsImplementation cardsImplementation) {
		String[] listOfPaths = super.chooseListOfCards(numberOfPeriod, DevelopmentCardTypes.BUILDINGCARD);
		for(String s:listOfPaths){
			super.createJsonObjectFromFile(s);
			name = super.readString("name");
			period= super.readInt("period");
			actionValue=super.readInt("actionValue");
			doublePayment = super.readString("doublePayment");
			if("false".equals(doublePayment)){
				paymentType=super.readString("typeOfPayment");
				payment=super.createPayment(paymentType);
				}
			if("true".equals(doublePayment)){
				paymentType= super.readString("typeOfPayment");
				paymentType2= super.readString("typeOfPayment2");
				payment= super.createPayment(paymentType);
				paymentTemp= super.createPayment(paymentType2);
				payment = super.createDoublePayment(payment,paymentTemp);
			}
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
				immediateEffectType = super.readString("typeOfImmediateEffect");
				immediateEffectType2 = super.readString("typeOfImmediateEffect2");
				immediateEffect = super.createImmediateEffect(immediateEffectType);
				immediateTemp = super.createImmediateEffect(immediateEffectType2);
				immediateEffect = super.createDoubleEffect(immediateEffect, immediateTemp);
			}
			createBuildingCard(cardsImplementation,numberOfPeriod);
			super.closeBufferedReader();
		}
	}

	/**
	 * Method that creates a Building card for the period indicated by the int numOfPeriod contained in the parameter
	 * @param cardsImplementation It's the object to update with the card that has been created
	 * @param numOfPeriod It's the number of period of the card that has to be created
	 */
	private void createBuildingCard(CardsImplementation cardsImplementation,int numOfPeriod){
		DevelopmentCard developmentCard= DevelopmentCardImplementation.buildingCard(name, period, payment, immediateEffect, permanentEffect, actionValue);
		cardsImplementation.getDevelopmentCards(numOfPeriod, DevelopmentCardTypes.BUILDINGCARD).add(developmentCard);
	}
			
}	