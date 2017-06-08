package it.polimi.ingsw.GC_26_cards.leaderCard;


import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_player.Player;

public abstract class LeaderCard implements CardDescriber{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public abstract String getName();
	public abstract boolean checkRequirement(Player player);
	public abstract void runEffect(Player player);
	
	
	
	@Override
	public String getTypeOfCard() {
		return "Leader Card";
	}


	@Override
	public int getActionValue() {
		return 0;
	}


	@Override
	public DevelopmentCardTypes getCardType() {
		return null;
	}


	@Override
	public int getPeriod() {
		return 0;
	}


	@Override
	public String getImmediateEffectDescriber() {
		return null;
	}

}
