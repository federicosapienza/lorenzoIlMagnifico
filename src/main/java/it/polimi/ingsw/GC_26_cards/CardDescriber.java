package it.polimi.ingsw.GC_26_cards;

import java.io.Serializable;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;

public interface CardDescriber extends Serializable{
	
	public  String getTypeOfCard();
	public  String getName();
	
	public  int getActionValue();
	public  DevelopmentCardTypes getCardType();
	public  int getPeriod();
	public  String getImmediateEffectDescriber();
	public  String getPermanentEffectDescriber();
	
	public  String getRequirementDescriber();

}
