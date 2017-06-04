package it.polimi.ingsw.GC_26_cards.payments;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_player.Player;

public interface Payment {
boolean canPlayerGetThis(Player player, DevelopmentCardTypes type);
void pay(Player player,  DevelopmentCardTypes type); //type is needed to know if discounts are present
String toString();
}