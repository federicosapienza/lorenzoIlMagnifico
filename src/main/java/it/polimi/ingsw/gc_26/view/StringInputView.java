package it.polimi.ingsw.gc_26.view;

import it.polimi.ingsw.gc_26.utilities.observer.Observable;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the view for the String input
 *
 */
public class StringInputView extends Observable<Integer>{
	
	private EndTurnView endTurnView ;
	
	/**
	 * Constructor: it creates a StringInputView based on the EndTurnView contained in the parameter
	 * @param endTurnView It's the EndTurnView which this StringInputView is based on
	 */
	public StringInputView(EndTurnView endTurnView) {
		this.endTurnView= endTurnView;
	}
	
	/**
	 * Method called to notify the observers about the content of the new String contained in the parameter 
	 * @param string the String whose content has to be notified to the observers
	 */
	public void notifyNewString(String string){
		
		String temp = "end turn";
		if(string.equalsIgnoreCase(temp) || "999".equals(string)){
				endTurnView.notifyEndTurn(false);
				return;
		}
		if(isInteger(string)){
			int number = Integer.parseInt(string);
			notifyObservers(number);
		}
		
		
	}
	
	/**
	 * Method that checks if the String contained in the parameter represents an Integer or not.
	 * Note: we got inspired by the following link
	 * https://stackoverflow.com/questions/5585779/how-to-convert-a-string-to-an-int-in-java
	 * @param str It's the String to check
	 * @return true if the String represents a number; false if it doesn't
	 */
	//
	public static boolean isInteger(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}

}
