package it.polimi.ingsw.GC_26_gameLogic;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_server.Observable;

//keeps memory of the Actions done during a turn!!! useful when player was suspended

public class ActionsPerformed extends Observable<ActionNotification>{
	private List<ActionNotification> actions;
	int actionId =0;
	
	public ActionsPerformed() {
		actions = new ArrayList<>();
	}
	
	public void addAction(ActionNotification actionNotification){
		actions.add(actionNotification);
		this.notifyObservers(actionNotification);
	}
	
	public void endRound(){
		actions.clear();
	}
}
