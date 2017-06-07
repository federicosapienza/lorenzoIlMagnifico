package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;


//Brunelleschi Effect
public class DisableTowerOccupiedMalus implements Effect{

	@Override
	public void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setTowerOccupiedMalusDisabled();
		
	}
	
	@Override
	public String toString() {
		return "Player does not  have to spend 3 coins when you place your Family Members in a Tower that is already occupied.";
	}

}
