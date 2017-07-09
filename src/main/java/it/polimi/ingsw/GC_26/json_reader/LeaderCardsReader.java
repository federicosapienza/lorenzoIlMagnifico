package it.polimi.ingsw.GC_26.json_reader;

import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.Effect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.leaderCard.LeaderCardImplementation;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.leaderCard.Requirement;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the reader of the Leader Cards
 *
 */
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
	
	/**
	 * Method that updates the CardsImplementation with the Leader Cards read from the file
	 * @param cardsImplementation It's the object to update with the Character cards read from the file
	 */
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
	
	/**
	 * Method that creates a Leader Card 
	 * @param cardsImplementation It's the object to update with the card that has been created
	 */
	private void createLeaderCard(CardsImplementation cardsImplementation){
		LeaderCard leaderCard = new LeaderCardImplementation(name, requirement, immediateEffect, permanentEffect);
		cardsImplementation.getLeaderCards().add(leaderCard);
	}
}
