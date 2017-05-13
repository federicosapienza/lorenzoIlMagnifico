package it.polimi.ingsw.GC_26_cards.excommunicationTile;

import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_player.Player;

public interface ExcommunicationTile {  
	void runEffect(Player player);
	String getIdCode();//potrebbe essere inutile! vedremo!
	ExcommunicationTile copy();

}
