package it.polimi.ingsw.GC_26.model.handlers;

import java.util.List;

import it.polimi.ingsw.GC_26.messages.Message;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This abstract class represents the handler used to handle various situations of the game
 *
 */
public abstract class Handler {
	List<Player> players;
	
	public Handler(List<Player> players) {
		this.players=players;
	}
	
	/**
	 * Method used to share broadcast messages and notify observers when calling players
	 * @param message It's the broadcast message that is shared with this method
	 */
	public void notifyPlayers(Message message){  
		for(Player p: players){
			p.notifyObservers(message);
		}
	}

}
