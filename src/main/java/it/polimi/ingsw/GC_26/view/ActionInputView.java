package it.polimi.ingsw.GC_26.view;

import it.polimi.ingsw.GC_26.messages.action.Action;
import it.polimi.ingsw.GC_26.utilities.observer.Observable;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents view for the input actions 
 *
 */
public class ActionInputView extends Observable<Action>{
	
	/**
	 * Method called to notify the observers about the Action contained in the parameter
	 * @param action
	 */
	public void notifyNewAction(Action action){
		notifyObservers(action);
	}

}
