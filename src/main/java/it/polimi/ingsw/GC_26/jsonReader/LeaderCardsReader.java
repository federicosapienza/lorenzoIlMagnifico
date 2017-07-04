package it.polimi.ingsw.GC_26.jsonReader;

import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.Effect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.LeaderCardImplementation;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.Requirement;


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
			if("false".equals(doubleRequirements)){
				requirementType = super.readString("typeOfRequirements");
				requirement = super.createRequirement(requirementType);
			}
			if ("true".equals(doubleRequirements)){
				requirementType = super.readString("typeOfRequirements");
				requirementType2 = super.readString("typeOfRequirements2");
				requirement = super.createRequirement(requirementType);
				requirementTemp = super.createRequirement(requirementType2);
				requirement = super.createDoubleRequirement(requirement,requirementTemp);
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
			createLeaderCard(cardsImplementation);
			super.closeBufferedReader();
		}
	}
	
	private void createLeaderCard(CardsImplementation cardsImplementation){
		LeaderCard leaderCard = new LeaderCardImplementation(name, requirement, immediateEffect, permanentEffect);
		cardsImplementation.getLeaderCards().add(leaderCard);
	}
}
