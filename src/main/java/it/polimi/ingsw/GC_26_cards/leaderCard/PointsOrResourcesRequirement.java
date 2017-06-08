package it.polimi.ingsw.GC_26_cards.leaderCard;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class PointsOrResourcesRequirement implements Requirement{
	private final ResourcesOrPoints needed;
	
	public PointsOrResourcesRequirement(ResourcesOrPoints needed) {
		this.needed= needed;
	}
	
	
	@Override
	public boolean checkRequirement(Player player) {
		return player.getWarehouse().areResourcesEnough(needed);
	}
	
	@Override
	public String toString() {
		return "Player needs "+needed;
	}

		

}
