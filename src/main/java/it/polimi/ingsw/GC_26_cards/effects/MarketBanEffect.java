package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;

public class MarketBanEffect implements Effect{

	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setMarketBanFlagOn();
	}
	
	
	 
	@Override
	public String toString() {
		return "Player can’t place FamilyMembers in the Market action spaces.";
	}

}
