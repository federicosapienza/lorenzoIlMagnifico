package it.polimi.ingsw.GC_26_readJson;

import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCardImplementation;
import it.polimi.ingsw.GC_26_cards.leaderCard.Requirement;
import it.polimi.ingsw.GC_26_cards.payments.Payment;

public class LeaderCardsReader extends CardsReader {
		
	private JsonPathData pathData = new JsonPathData();
	private String name;
	private String permanentEffectType;
	private String immediateEffectType;
	private String permanentEffectType2;
	private String immediateEffectType2;
	private String requirementType;
	private String requirementType2;
	private Requirement requirement;
	private Requirement requirementTemp;
	private Effect immediateEffect;
	private Effect permanentEffect;
	private Effect immediateTemp;
	private Effect permanentTemp;
	private String doubleImmediateEffect;
	private String doublePermanentEffect;
	private String doubleRequirements;
	
	public void readCards(CardsImplementation cardsImplementation){
		String [] listOfPaths = pathData.getLeaderCards();
		for(String s:listOfPaths){
			super.createJsonObjectFromFile(s);
			name = super.readString("name");
			doubleRequirements = super.readString("doubleRequirements");
			if(doubleRequirements.equals("false")){
				requirementType = super.readString("typeOfRequirements");
				requirement = super.createRequirement(requirementType);
			}
			if (doubleRequirements.equals("true")){
				requirementType = super.readString("typeOfRequirements");
				requirementType2 = super.readString("typeOfRequirements2");
				requirement = super.createRequirement(requirementType);
				requirementTemp = super.createRequirement(requirementType2);
				requirement = super.createDoubleRequirement(requirement,requirementTemp);
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
			createLeaderCard(cardsImplementation);
			super.closeBufferedReader();
		}
	}
	
	private void createLeaderCard(CardsImplementation cardsImplementation){
		LeaderCard leaderCard = new LeaderCardImplementation(name, requirement, immediateEffect, permanentEffect);
		cardsImplementation.getLeaderCards().add(leaderCard);
	}
}
