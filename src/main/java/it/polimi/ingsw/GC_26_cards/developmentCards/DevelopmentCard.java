package it.polimi.ingsw.GC_26_cards.developmentCards;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_player.Player;

public abstract class DevelopmentCard implements CardDescriber{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract boolean canPlayerGetThis(Player player);
	public abstract void pay(Player player);
	public abstract void runImmediateEffect(Player player);
	public abstract void runPermanentEffect(Player player);	
	public abstract int getActionValue();
	public abstract  DevelopmentCardTypes getType();
	
	@Override
	public String getTypeOfCard() {
		return "Development Card";
	}
	
	@Override
	public String getRequirementDescriber() {
		return null;
	}
	
}
