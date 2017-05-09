package it.polimi.ingsw.GC_26_cards_developmentCards;


public interface DevelopmentCard {
	String getName();
	boolean canPlayerGetThis(Player player, ResourcesOrPoints resourcesUsedUntilNow); /* the second parameter allows to check 
	the player has enough resources even after other previous payments (such as coins or servants) */
	void pay(Player player);
	void runImmediateEffect(Player player);
	void runPermanentEffect(Player player);	
}
