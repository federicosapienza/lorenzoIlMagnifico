package it.polimi.ingsw.GC_26.view;

import it.polimi.ingsw.GC_26.messages.action.Action;
import it.polimi.ingsw.GC_26.utilities.observer.Observable;

public class ActionInputView extends Observable<Action>{
	
	
	public void notifyNewAction(Action action){
		notifyObservers(action);
	}

}
