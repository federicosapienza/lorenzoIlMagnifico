package it.polimi.ingsw.gc_26.model.game.game_components.cards.effects;

import it.polimi.ingsw.gc_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the effect that doubles the number of servants needed to do a certain action
 *
 */
public class DoubleServantsNeededEffect implements Effect{

	/**
	 * Method called to apply the effect to the player who is involved in the effect: it activates the flag for the effect
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setDoubleServantsOn();
	}

	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		return " Player has to spend 2 servants to increase his action value by 1";
	}
}
