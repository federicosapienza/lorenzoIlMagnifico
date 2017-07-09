package it.polimi.ingsw.GC_26.model.game.game_components.cards.effects;

import it.polimi.ingsw.GC_26.model.game.game_components.board.BoardZone;
import it.polimi.ingsw.GC_26.model.player.Player;


/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Class used by some Development Cards as bonuses and by Excommunication tiles as maluses.
 * The rule used for the creation is the following: positive values for discount; negtive values for maluses.
 * 
 *
 */

public class ActionValueModifierEffect implements Effect{
	private final BoardZone zone;
	private final int value;
	
	/**
	 * Method that returns the zone of the board involved in the effect of the card
	 * @return the zone of the board involved in the effect of the card
	 */
	public BoardZone getZone() {
		return zone;
	}
	

	/**
	 * Constructor that creates an effect that changes the value of the action, in the zone and with value expressed in the parameters.
	 * @param zone the zone involved in the effect
	 * @param value the value that the effect has to change
	 */
	public ActionValueModifierEffect(BoardZone zone, int value ) {
		this.zone = zone;
		this.value= value;
	}

	/**
	 * Method called to apply the effect to the player who is involved in the effect
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().addActionModifier(zone, value);
	}
	
	/**
	 * Method that describes the applied effect
	 */
	@Override
	public String toString() {
		if(value>0)
			return " Increment the actions of type "+ zone+ " of value "+value;
		else
			return " Decrease the actions of type "+ zone + " of value "+value;
	}

}
