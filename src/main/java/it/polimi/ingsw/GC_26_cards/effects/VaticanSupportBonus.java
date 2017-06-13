package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class VaticanSupportBonus implements Effect{
	private final ResourcesOrPoints bonus;
	
	public VaticanSupportBonus(ResourcesOrPoints bonus) {
		this.bonus=bonus; 
	}

	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().gainAdditionalVP(bonus.getVictoryPoints());
	}
	
	
	@Override
	public String toString() {
			return "Player gains "+ bonus.getVictoryPoints() +" Victory Points when supporting the Church in a Vatican Report phase.";
	}

}
