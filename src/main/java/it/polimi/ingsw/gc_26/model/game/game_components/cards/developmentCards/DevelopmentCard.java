package it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards;

import it.polimi.ingsw.gc_26.model.player.Player;

/**
*
* @author David Yun (david.yun@mail.polimi.it)
* @author Federico Sapienza (federico.sapienza@mail.polimi.it)
* @author Leonardo Varè (leonardo.vare@mail.polimi.it)
*
* Interface that represents the development card of the game
*
*/
public interface DevelopmentCard {
	/**
	 * 
	 */

	public abstract boolean canPlayerGetThis(Player player);
	public abstract void pay(Player player);
	public abstract void runImmediateEffect(Player player);
	public abstract void runPermanentEffect(Player player);	
	public abstract int getActionValue();
	public abstract DevelopmentCardTypes getType();
	
	
}