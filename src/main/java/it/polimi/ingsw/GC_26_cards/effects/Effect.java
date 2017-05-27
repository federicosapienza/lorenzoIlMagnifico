package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;

public interface Effect {
	void doEffect(Player player, boolean immediate); //immediate is needed for Santa Rita card
	String toString();

}
