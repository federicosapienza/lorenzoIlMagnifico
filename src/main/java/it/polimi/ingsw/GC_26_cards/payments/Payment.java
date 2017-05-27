package it.polimi.ingsw.GC_26_cards.payments;

import it.polimi.ingsw.GC_26_player.Player;

public interface Payment {
boolean canPlayerGetThis(Player player);
void pay(Player player);
String toString();
}