package it.polimi.ingsw.GC_26_cards.developmentCards;

import it.polimi.ingsw.GC_26_player.Player;

public interface DevelopmentCard {
	/**
	 * 
	 */

	public abstract boolean canPlayerGetThis(Player player);
	public abstract void pay(Player player);
	public abstract void runImmediateEffect(Player player);
	public abstract void runPermanentEffect(Player player);	
	public abstract int getActionValue();
	public abstract DevelopmentCardTypes getType();
	
	
}