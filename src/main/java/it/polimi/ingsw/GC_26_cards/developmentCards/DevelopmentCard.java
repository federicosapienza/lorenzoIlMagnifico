package it.polimi.ingsw.GC_26_cards.developmentCards;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public interface DevelopmentCard {
	boolean canPlayerGetThis(Player player, ResourcesOrPoints resourcesUsedUntilNow); /* the second parameter allows to check if
	the player has enough resources even after other previous payments (such as coins or servants) */
	void pay(Player player);
	void runImmediateEffect(Player player);
	void runPermanentEffect(Player player);	
	public int getActionValue();
	//DevelopmentCard copy();
}
