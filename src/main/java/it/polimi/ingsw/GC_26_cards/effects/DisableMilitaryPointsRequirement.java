package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;

public class DisableMilitaryPointsRequirement implements Effect{

	@Override
	public  synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setMilitaryPointRequirementNotNeeded();
	}
	
	@Override
	public String toString() {
		return "Player does not need to satisfy the Military Points requirement when takes Territory Cards.";
	}

}
