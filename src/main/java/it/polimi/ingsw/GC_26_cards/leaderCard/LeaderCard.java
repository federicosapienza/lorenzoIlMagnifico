package it.polimi.ingsw.GC_26_cards.leaderCard;


import it.polimi.ingsw.GC_26_player.Player;

public interface LeaderCard {
	/**
	 * 
	 */


	public abstract String getName();
	public abstract boolean checkRequirement(Player player);
	public abstract void runImmediateEffect(Player player);
	public abstract void runPermanentEffect(Player player);

	


}
