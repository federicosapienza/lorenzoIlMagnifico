package it.polimi.ingsw.GC_26_cards;

import java.io.Serializable;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * It's the interface that describes the card object.
 */
public interface CardDescriber extends Serializable{
	
	// getter method to get the type of the card as a string
	public  String getTypeOfCard();
	
	//getter method to get the name of the card
	public  String getName();
	
	//getter method to get the value of the action
	public  int getActionValue();
	
	//getter method to get the type of the card as type of development card
	public  DevelopmentCardTypes getCardType();
	
	//getter method to get the period of the card
	public  int getPeriod();
	
	//getter method to get the immediate effect describer of the card as a string
	public  String getImmediateEffectDescriber();
	
	//getter method to get the permanent effect describer of the card as a string
	public  String getPermanentEffectDescriber();
	
	//getter method to get the requirement describer of the card as a string
	public  String getRequirementDescriber();

}
