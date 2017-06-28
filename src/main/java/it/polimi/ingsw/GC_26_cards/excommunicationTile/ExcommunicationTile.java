package it.polimi.ingsw.GC_26_cards.excommunicationTile;

import it.polimi.ingsw.GC_26_player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the interface that represents the Excommunication tile.
 *
 */
public interface ExcommunicationTile {  
	/**
	 * 
	 */

	public abstract void runEffect(Player player);
	
	public abstract int getPeriod();
	
	
	
	

}
