package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

//used  by some Development Cards as bonus and by excommunication tiles as malus
//creation rule: positive values for discount;  negative values for malus
public class ActionValueModifierEffect implements Effect{
	private final BoardZone zone;
	private final int value;
	
	public BoardZone getZone() {
		return zone;
	}
	

	public ActionValueModifierEffect(BoardZone zone, int value ) {
		this.zone = zone;
		this.value= value;
	}

	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().addActionModifier(zone, value);
	}
	
	
	@Override
	public String toString() {
		if(value>0)
			return "increment the actions of type "+ zone+ " of value "+value;
		else
			return "decrease the actions of type "+ zone + " of value "+value;
	}

}
