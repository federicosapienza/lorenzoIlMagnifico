package it.polimi.ingsw.GC_26.view;

import it.polimi.ingsw.GC_26.utilities.observer.Observable;

public class EndTurnView  extends Observable<Boolean>{
		
		public void notifyEndTurn(boolean timeout){
				notifyObservers(timeout);
			}

}
