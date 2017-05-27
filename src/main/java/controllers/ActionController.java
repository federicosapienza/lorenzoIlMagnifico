package controllers;

import it.polimi.ingsw.GC_26_actionsHandlers.Action;
import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

public class ActionController {  //TODO extends actionObserver etc
	Player player;
	MainActionHandler handlers;
	
	public ActionController(Player player, MainActionHandler handlers) {
		this.player=player;
		this.handlers= handlers;
	}
	
	void update(){ //todo
		Action action=null; //inizializzazione provvisoria!
		
		if(player.getStatus() == PlayerStatus.PLAYING){
			synchronized (player.getStatus()) {
				player.setStatus(PlayerStatus.VALIDATING);
			}	
		handlers.getFirstActionHandler().startAction(player, action);
		}
		if(player.getStatus()== PlayerStatus.SECONDPLAY){
			synchronized (player.getStatus()) {
				player.setStatus(PlayerStatus.VALIDATING);
			}
			handlers.getSecondActionHandler().startAction(player, action);
		}
	}
}
