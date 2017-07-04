package it.polimi.ingsw.GC_26.view;

import it.polimi.ingsw.GC_26.server.Observable;

public class EndTurnView  extends Observable<Boolean>{
		
		public void notifyEndTurn(boolean timeout){
				notifyObservers(timeout);
			}

}
