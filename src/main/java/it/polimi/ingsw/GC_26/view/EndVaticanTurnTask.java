package it.polimi.ingsw.GC_26.view;

import java.util.TimerTask;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the TimerTask used to manage the end of the Vatican Report phase
 *
 */
public class EndVaticanTurnTask extends TimerTask{
	private StringInputView stringInputView;
	
	/**
	 * Constructor: it creates a EndVaticanTurnTask based on the StringInputView contained in the parameter
	 * @param stringInputView the StringInputView which this EndVaticanTurnTask is based on
	 */
	public EndVaticanTurnTask(StringInputView stringInputView) { 
		this.stringInputView=stringInputView;
	}
	
	/**
	 * Method called to run the EndVaticanTurnTask: the stringInputView will automatically respond to controllers with default action
	 * (which means excommunication)
	 */
	@Override
	public void run() { 
		stringInputView.notifyObservers(0);
	}	
}
