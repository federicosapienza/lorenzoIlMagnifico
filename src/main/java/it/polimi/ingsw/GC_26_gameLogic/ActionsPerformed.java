package it.polimi.ingsw.GC_26_gameLogic;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_serverView.Observable;

//keeps memory of the Actions done during a turn!!! useful when player was suspended

public class ActionsPerformed extends Observable<Action>{
	private List<Action> actions;
	int actionId =0;
	
	public ActionsPerformed() {
		actions = new ArrayList<>();
	}
	
	public void addAction(Action action){
		actions.add(action);
		this.notifyObservers(action);
	}
	
	public void endRound(){
		actions.clear();
	}
}
