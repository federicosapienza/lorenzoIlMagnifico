package it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects;


import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the interface that represents the effect of a general card
 *
 */
public interface Effect {
	/**
	 * Method called to apply the effect to the player involved in it. Immediate is needed for Santa Rita card
	 * @param player is the player involved in the effect
	 * @param immediate is a boolean needed for the Leader card Santa Rita 
	 */
	void doEffect(Player player, boolean immediate); 
	
	@Override
	String toString();

}
