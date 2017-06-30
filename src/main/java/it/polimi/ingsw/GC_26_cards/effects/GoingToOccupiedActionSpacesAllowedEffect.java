package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the effect applied by the Leader Card Ludovico Ariosto: it allows the player who owns this card, to place his Family Members 
 * in occupied action spaces.
 *
 */

public class GoingToOccupiedActionSpacesAllowedEffect implements Effect{

	/**
	 * Method called to apply the effect to the player who is involved in the effect: it activates the flag which indicates the possibility to place 
	 * Family Members in occupied action spaces
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setGoingInOccupiedPositionsAllowed();
	}
	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		return " Player can place family member in occupied action spaces";
	}

}
