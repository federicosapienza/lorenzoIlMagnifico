package it.polimi.ingsw.GC_26.view;

import java.util.TimerTask;



public class EndVaticanTurnTask extends TimerTask{
	private StringInputView stringInputView;
	
	public EndVaticanTurnTask(StringInputView stringInputView) { 
		//notify the server view that the turn is ended
		this.stringInputView=stringInputView;
	}
	

	@Override
	public void run() { //the stringInputView will automatically respond to controllers with default action
		stringInputView.notifyObservers(0);
	}	
	}
