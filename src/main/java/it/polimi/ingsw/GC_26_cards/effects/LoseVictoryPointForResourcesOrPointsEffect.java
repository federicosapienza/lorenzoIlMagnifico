package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

//Lose victoryPoints at the end of the game. excommunication tile Effect
	// resourcesOrPoints are here used to save what and how many resources or points causes loss on victoryPoints: 
	// 0 stands for no malus, 1,2 ... stand for how many victory point for type.

public class LoseVictoryPointForResourcesOrPointsEffect implements Effect{
	ResourcesOrPoints malus;
	
	public LoseVictoryPointForResourcesOrPointsEffect(ResourcesOrPoints resources) {
		malus= resources;
	}

	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().addParameterForLosingPoints(malus);
	}
	
	@Override
	public String toString() {
		return "At the end of the game, playes loses  Victory Point : (means:  number of loss for every type written) " + malus;
	}

}
