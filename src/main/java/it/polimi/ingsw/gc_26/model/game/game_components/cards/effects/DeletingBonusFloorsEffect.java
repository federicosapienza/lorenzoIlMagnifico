package it.polimi.ingsw.gc_26.model.game.game_components.cards.effects;

import it.polimi.ingsw.gc_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the one of effects applied by Preacher: it revokes the chance to get bonuses from towers positions
 *
 */

public class DeletingBonusFloorsEffect implements Effect{

	/**
	 * Method called to apply the effect to the player who is involved in the effect
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setBonusRevokedOn();  
	}
	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		return " Revoke the chance of getting bonuses from tower's positions";
	}

}
