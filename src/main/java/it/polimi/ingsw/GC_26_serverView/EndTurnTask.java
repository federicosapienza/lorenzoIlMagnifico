package it.polimi.ingsw.GC_26_serverView;

import java.util.TimerTask;

public class EndTurnTask extends TimerTask{
	private EndTurnView endTurnView;
	
	public EndTurnTask(EndTurnView endTurnView) { 
		//notify the server view that the turn is ended
		this.endTurnView=endTurnView;
	}
	

	@Override
	public void run() {
		endTurnView.notifyObservers(true);
		
	}

}
