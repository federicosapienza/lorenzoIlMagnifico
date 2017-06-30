package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the effect that disables the military points requirement for the player who is involved in the effect
 *
 */
public class DisableMilitaryPointsRequirement implements Effect{

	/**
	 * Method called to apply the effect to the player who is involved in the effect
	 */
	@Override
	public  synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setMilitaryPointRequirementNotNeeded();
	}
	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		return " Player does not need to satisfy the Military Points requirement when takes Territory Cards.";
	}

}
