package it.polimi.ingsw.GC_26_cards_payments;

public interface Payments {
boolean canPlayerGetThis(Player player, ResourcesOrPoints resourcesUsedUntilNow);
void pay();
String toString();
}