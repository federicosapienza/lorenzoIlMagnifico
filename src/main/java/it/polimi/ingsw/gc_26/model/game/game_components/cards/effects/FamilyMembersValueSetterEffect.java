package it.polimi.ingsw.gc_26.model.game.game_components.cards.effects;

import it.polimi.ingsw.gc_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the effects of the Leader cards Ludovico Il Moro and Fdederico da Montefeltro (this one has not been implemented and won't
 * be part of the videogame): they change the value of certain Family Members to a new fixed value
 *
 */

public class FamilyMembersValueSetterEffect implements Effect{
	private final int value;
	private final int howManyDicesSetted;
	
	/**
	 * Constructor: it creates the effect that changes the values of certain Family Members to a new fixed value, defined in the parameter
	 * @param howManyDicesSetted It's the number of Family Members involved in the effect
	 * @param value It's the new value that the coloured Family Members will assume
	 */
	public FamilyMembersValueSetterEffect(int howManyDicesSetted, int value) {
		this.howManyDicesSetted= howManyDicesSetted;
		this.value = value;
	}

	
	/**
	 * Method called to apply the effect to the player who is involved in the effect
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setDicesSetted(howManyDicesSetted, value);
		player.getFamilyMembers().changeValues();
		
	}
	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		return howManyDicesSetted+ " of your colored Family Members has a value of " +value+ " ,regardless of its related dice.";
	}

}
