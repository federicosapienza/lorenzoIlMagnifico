package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_server.Observable;

public class EndTurnView  extends Observable<Boolean>{
		
		public void notifyEndTurn(boolean timeout){
				notifyObservers(timeout);
			}

}
