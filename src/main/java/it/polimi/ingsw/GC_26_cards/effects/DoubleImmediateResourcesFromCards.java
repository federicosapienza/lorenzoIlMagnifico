package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;

public class DoubleImmediateResourcesFromCards implements Effect{

	@Override
	public void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setDoubleEarningOn();
	}
	
	@Override
	public String toString() {
		return "Each time you receiveresources as an immediate effect from Development Cards  you receive theresources twice.";
	}
	

}
