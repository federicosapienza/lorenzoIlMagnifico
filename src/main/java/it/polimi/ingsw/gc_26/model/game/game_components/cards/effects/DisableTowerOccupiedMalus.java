package it.polimi.ingsw.gc_26.model.game.game_components.cards.effects;

import it.polimi.ingsw.gc_26.model.player.Player;


/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the effect applied by the Leader card Filippo Brunelleschi: the player who activates this effect, 
 * doesn't have to pay anymore 3 coins when placing Family Members in a Tower that is already occupied.
 *
 */
public class DisableTowerOccupiedMalus implements Effect{

	/**
	 * Method called to apply the effect to the player has activated the effect
	 */
	@Override
	public void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setTowerOccupiedMalusDisabled();
		
	}
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		return " Player does not have to spend 3 coins when you place your Family Members in a Tower that is already occupied.";
	}

}
