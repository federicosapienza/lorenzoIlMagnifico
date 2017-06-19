package it.polimi.ingsw.GC_26_actionsHandlers;

import java.util.List;


import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.Message;

public abstract class Handler {
	List<Player> players;
	
	public Handler(List<Player> players) {
		this.players=players;
	}
	
	/**
	 * Method used to share broadcast messages and notify observers when calling players
	 * @param message It's the broadcast message that are shared with this method
	 */
    public void notifyPlayers(Message message){  
       for(Player p: players){
    	   p.notifyObservers(message);
       }
       
       
    }

}
