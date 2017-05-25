package it.polimi.ingsw.GC_26_cards.developmentCards;

import it.polimi.ingsw.GC_26_player.Player;

public interface DevelopmentCard {
	boolean canPlayerGetThis(Player player);
	void pay(Player player);
	void runImmediateEffect(Player player);
	void runPermanentEffect(Player player);	
	public int getActionValue();
	public DevelopmentCardTypes getType();
	//DevelopmentCard copy();
}
