package it.polimi.ingsw.GC_26_cards.leaderCard;

import it.polimi.ingsw.GC_26_cards.effects.Effect;

public interface LeaderCard {
	String getName();
	Requirement checkRequirement();
	Effect getEffect();
	LeaderCard copy();

}
