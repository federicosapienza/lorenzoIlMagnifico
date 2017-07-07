package it.polimi.ingsw.GC_26.jsonReader;

import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.Effect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments.common.Payment;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the reader of the Character cards contained in the Json file
 *
 */
public class CharacterCardsReader extends CardsReader {
		
	private String name;
	private int period;
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
	 * Method that updates the CardsImplementation with the Character cards read from the file
	 * @param numberOfPeriod It's the number of the period of the Character cards to read
	 * @param cardsImplementation It's the object to update with the Character cards read from the file
	 */
	public void readCards(int numberOfPeriod, CardsImplementation cardsImplementation) {
		String[] listOfPaths = super.chooseListOfCards(numberOfPeriod, DevelopmentCardTypes.CHARACTERCARD);
		for(String s:listOfPaths){
			super.createJsonObjectFromFile(s);
			name = super.readString("name");
			period= super.readInt("period");
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
			createCharacterCard(cardsImplementation,numberOfPeriod);
			super.closeBufferedReader();
		}
	}

	
	/**
	 * Method that creates a Character card for the period indicated by the int numOfPeriod contained in the parameter
	 * @param cardsImplementation It's the object to update with the card that has been created
	 * @param numOfPeriod It's the number of period of the card that has to be created
	 */
	private void createCharacterCard(CardsImplementation cardsImplementation, int numOfPeriod){
		DevelopmentCard developmentCard= DevelopmentCardImplementation.characterCard(name, period, payment, immediateEffect,permanentEffect);
		cardsImplementation.getDevelopmentCards(numOfPeriod, DevelopmentCardTypes.CHARACTERCARD).add(developmentCard);
	}
			
}	