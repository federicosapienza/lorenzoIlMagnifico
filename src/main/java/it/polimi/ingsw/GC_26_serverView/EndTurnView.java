package it.polimi.ingsw.GC_26_serverView;

public class EndTurnView  extends Observable<Boolean>{
		
		public void notifyEndTurn(boolean timeout){
				notifyObservers(timeout);
			}

}
