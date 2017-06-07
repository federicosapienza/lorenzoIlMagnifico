package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;

//AriostoEffect

public class GoingToOccupiedActionSpacesAllowedEffect implements Effect{

	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setGoingInOccupiedPositionsAllowed();
	}
	
	
	@Override
	public String toString() {
		return "Player can place family member in occupied action spaces";
	}

}
