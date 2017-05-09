package it.polimi.ingsw.GC_26_cards_developmentCards;

import javax.annotation.Resources;

public interface DevelopmentCard {
	String getName();
	boolean canPlayerGetThis(Player player, ResourcesOrPoints resourcesUsedUntilNow);
	void pay(Player player);
	void runImmediateEffect(Player player);
	void runPermanentEffect(Player player);	
}
