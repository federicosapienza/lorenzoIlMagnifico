package it.polimi.ingsw.GC_26_gameLogic;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_observerAndObservableLogic.Observable;

//keeps memory of the Actions done during a turn!!! useful when player was suspended

public class ActionsPerformed extends Observable<ActionDescriber>{
	private List<ActionDescriber> actions;
	
	public ActionsPerformed() {
		actions = new ArrayList<>();
	}
	
	public void addAction(ActionDescriber action){
		actions.add(action);
		this.notifyObservers(action);
	}
	
	public void endTurn(){
		actions.clear();
	}
}
