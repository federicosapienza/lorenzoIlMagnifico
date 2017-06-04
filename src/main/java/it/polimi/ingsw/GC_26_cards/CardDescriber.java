package it.polimi.ingsw.GC_26_cards;

public interface CardDescriber {
	
	public  String getTypeOfCard();
	public  String getName();
	
	public  int getActionValue();
	public  String getCardType();
	public  int getPeriod();
	public  String getImmediateEffectDescriber();
	public  String getPermanentEffectDescriber();
	
	public  String getRequirementDescriber();

}
