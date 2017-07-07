package it.polimi.ingsw.GC_26.view;

import java.util.TimerTask;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the TimerTask to use when the turn ends
 *
 */
public class EndTurnTask extends TimerTask{
	private EndTurnView endTurnView;
	
	/**
	 * Constructor: it creates an EndTurnTask based on the EndTurnView contained in the parameter
	 * @param endTurnView the EndTurnView which this EndTurnTask is based on
	 */
	public EndTurnTask(EndTurnView endTurnView) { 
		this.endTurnView=endTurnView;
	}
	
	/**
	 * Method called to run the EndTurnTask. It notifies the observers that turn is ended
	 */
	@Override
	public void run() {
		endTurnView.notifyObservers(true);
		
	}

}
