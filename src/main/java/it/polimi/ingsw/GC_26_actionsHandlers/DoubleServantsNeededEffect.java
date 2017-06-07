package it.polimi.ingsw.GC_26_actionsHandlers;

import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_player.Player;

public class DoubleServantsNeededEffect implements Effect{

	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setDoubleServantsOn();
	}

	
	
	@Override
	public String toString() {
		return "player has to spend 2 servants to increase his action value by 1";
	}
}
