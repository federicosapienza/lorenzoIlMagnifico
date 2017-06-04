package it.polimi.ingsw.GC_26_cards.leaderCard;


import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_player.Player;

public interface LeaderCard extends CardDescriber{
	String getName();
	boolean checkRequirement(Player player);
	void runEffect(Player player);

}
