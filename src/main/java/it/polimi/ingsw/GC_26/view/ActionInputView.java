package it.polimi.ingsw.GC_26.view;

import it.polimi.ingsw.GC_26.model.game.action.Action;
import it.polimi.ingsw.GC_26.server.Observable;

public class ActionInputView extends Observable<Action>{
	
	
	public void notifyNewAction(Action action){
		notifyObservers(action);
	}

}
