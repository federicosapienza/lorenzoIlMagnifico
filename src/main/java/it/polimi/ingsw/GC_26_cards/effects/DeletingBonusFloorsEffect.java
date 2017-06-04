package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;

//Preacher effects: revoke the chance of getting bonuses from towers positions

public class DeletingBonusFloorsEffect implements Effect{

	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setBonusRevokedOn();  
	}
	
	@Override
	public String toString() {
		return "revoke the chance of getting bonuses from towers positions";
	}

}
