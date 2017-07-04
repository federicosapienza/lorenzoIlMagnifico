package it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects;

import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the effect that doubles the immediate resources obtained from the cards. 
 *
 */
public class DoubleImmediateResourcesFromCards implements Effect{

	/**
	 * Method called to apply the effect to the player who is involved in the effect: it activates the flag for the effect 
	 */
	@Override
	public void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setDoubleEarningOn();
	}
	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		return " Each time you receive resources as an immediate effect from Development Cards you receive the resources twice.";
	}
	

}
