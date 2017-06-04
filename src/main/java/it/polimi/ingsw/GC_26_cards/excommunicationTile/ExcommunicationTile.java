package it.polimi.ingsw.GC_26_cards.excommunicationTile;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_player.Player;

public interface ExcommunicationTile extends CardDescriber{  
	void runEffect(Player player);
	String getIdCode();//potrebbe essere inutile! vedremo!
	//ExcommunicationTile copy();

}
