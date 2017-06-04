package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class PermanentResourcesMalusEffect implements Effect{
	private final ResourcesOrPoints malus;
	
	
	
	public PermanentResourcesMalusEffect(ResourcesOrPoints malus) {
		
		this.malus = malus;
	}



	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setMalus(malus);		
	}
	
	@Override
	public String toString() {
		return "Each time player gains something, gains "+ malus+ " less";
	}

}
