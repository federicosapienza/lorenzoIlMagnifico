package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_server.Observable;

public class ActionInputView extends Observable<Action>{
	
	
	public void notifyNewAction(Action action){
		notifyObservers(action);
	}

}
