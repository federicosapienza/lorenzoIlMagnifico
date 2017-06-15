package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_server.Observable;

public class StringInputView extends Observable<Integer>{
	
	EndTurnView endTurnView ;
	
	public StringInputView(EndTurnView endTurnView) {
		this.endTurnView= endTurnView;}
		
	public void notifyNewString(String string){
		
		String temp = "end turn";
		if(string.equalsIgnoreCase(temp) || string.equals("999")){
				endTurnView.notifyEndTurn(false);
				return;
		}
		if(isInteger(string)){
			int number = Integer.parseInt(string);
			notifyObservers(number);
		}
		
		
		
		//TODO endturn chiama chiusura connessione, il socket magari legge e chiude subito , poi notifichiamo da qui
	}
		
		//https://stackoverflow.com/questions/5585779/how-to-convert-a-string-to-an-int-in-java
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
