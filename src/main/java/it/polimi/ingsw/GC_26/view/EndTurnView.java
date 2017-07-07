package it.polimi.ingsw.GC_26.view;

import it.polimi.ingsw.GC_26.utilities.observer.Observable;


/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the view for the end of the turn
 *
 */
public class EndTurnView extends Observable<Boolean>{
	
	/**
	 * Method that notifies the observers about the end of the turn: if the boolean timeout is true, it means that the turn is ended,
	 * if it's false, it means that the turn isn't ended
	 * @param timeout It's the boolean which indicates if the turn is ended or not
	 */
	public void notifyEndTurn(boolean timeout){
		notifyObservers(timeout);
	}

}
