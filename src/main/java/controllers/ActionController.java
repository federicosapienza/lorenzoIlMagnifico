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
	
	public void update(){ //todo 
		//potrei mettere synronysed il controller per evitare problemi di 2 azioni in contemporane
	
		Action action=null; //inizializzazione provvisoria!
		if(player.getStatus() == PlayerStatus.PLAYING)
			 firstActionController(action);
		if(player.getStatus()== PlayerStatus.SECONDPLAY)
			secondActionController(action);
	}
	
	
	private void firstActionController(Action action){
		player.setPlayerActive();
		try {
			Boolean flag = handlers.getFirstActionHandler().isPossible(player, action);
			// if action not possible player is notified in IsPossible and linked methods
			if(!flag){
				handlers.getFirstActionHandler().perform(player,  action);
			if(player.isThereAsecondaryAction())
				synchronized (player.getStatus()) {
					player.setStatus(PlayerStatus.SECONDPLAY);
				}
			else synchronized (player.getStatus()) {
				player.setStatus(PlayerStatus.ACTIONPERFORMED);
			}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			player.setStatus(PlayerStatus.PLAYING);
			player.notifyObservers("action not valid");
			
		}
		catch ( IllegalStateException e1 ) {
			e1.printStackTrace();
			player.setStatus(PlayerStatus.PLAYING);
			player.notifyObservers("action not valid");
		}
	}
	
	private void secondActionController(Action action){
		try {
			Boolean flag = handlers.getSecondActionHandler().isPossible(player, action);
			// if action not possible player is notified in IsPossible and linked methods
			if(!flag){
				handlers.getSecondActionHandler().perform(player,  action);
			 synchronized (player.getStatus()) {
				player.setStatus(PlayerStatus.ACTIONPERFORMED);
				player.resetSecondAction();
			}
		}
			 
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			player.setStatus(PlayerStatus.PLAYING);
			player.notifyObservers("action not valid");
			
		}
		catch ( IllegalStateException e1 ) {
			e1.printStackTrace();
			player.setStatus(PlayerStatus.PLAYING);
			player.notifyObservers("action not valid");
		}
	}
	
}


/*
 * STATO PRECEDENTE
 * if(player.getStatus() == PlayerStatus.PLAYING){
 
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
} */