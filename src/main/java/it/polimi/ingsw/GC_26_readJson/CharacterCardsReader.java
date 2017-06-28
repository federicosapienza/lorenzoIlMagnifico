package it.polimi.ingsw.GC_26_readJson;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.payments.Payment;

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
		
		public void readCards(int numberOfPeriod,CardsImplementation cardsImplementation){
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

		
		private void createCharacterCard(CardsImplementation cardsImplementation,int numOfPeriod){
			DevelopmentCard developmentCard= DevelopmentCardImplementation.characterCard(name, period, payment, immediateEffect,permanentEffect);
			switch(numOfPeriod){
			   case 1:
				   cardsImplementation.getDevelopmentCards(numOfPeriod, DevelopmentCardTypes.CHARACTERCARD).add(developmentCard);
				   break;
			   case 2:
				   cardsImplementation.getDevelopmentCards(numOfPeriod, DevelopmentCardTypes.CHARACTERCARD).add(developmentCard);
				   break;
			   case 3:
				   cardsImplementation.getDevelopmentCards(numOfPeriod, DevelopmentCardTypes.CHARACTERCARD).add(developmentCard);
				   break;
			   default:
				   throw new IllegalArgumentException();
			   }
		   
			}
			
}	