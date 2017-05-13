package it.polimi.ingsw.GC_26_cards.payments;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public interface Payment {
boolean canPlayerGetThis(Player player, ResourcesOrPoints resourcesUsedUntilNow);
void pay(Player player);
String toString();
Payment copy();   // passes an object which is identical to this : allows deep-cloning
}