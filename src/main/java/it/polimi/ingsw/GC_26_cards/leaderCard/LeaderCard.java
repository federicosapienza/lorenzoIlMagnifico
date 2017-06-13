package it.polimi.ingsw.GC_26_cards.leaderCard;


import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_player.Player;

public interface LeaderCard {
	/**
	 * 
	 */


	public abstract String getName();
	public abstract boolean checkRequirement(Player player);
	public abstract void runEffect(Player player);
	
	


}
