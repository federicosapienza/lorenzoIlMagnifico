package it.polimi.ingsw.gc_26.model.game.game_components.cards.effects;

import it.polimi.ingsw.gc_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This class represents the Excommunication tile effect that inhibit the affected player to place his Family Members in 
 * the Market action spaces
 *
 */
public class MarketBanEffect implements Effect{

	/**
	 * Method called to apply the effect to the player who is involved in the effect: it activates the flag for the ban to the
	 * Market action spaces
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setMarketBanFlagOn();
	}
	
	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		return " Player can’t place FamilyMembers in the Market action spaces.";
	}

}
