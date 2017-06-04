package it.polimi.ingsw.GC_26_cards.developmentCards;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_player.Player;

public interface DevelopmentCard extends CardDescriber{
	boolean canPlayerGetThis(Player player);
	void pay(Player player);
	void runImmediateEffect(Player player);
	void runPermanentEffect(Player player);	
	public int getActionValue();
	public DevelopmentCardTypes getType();
}
