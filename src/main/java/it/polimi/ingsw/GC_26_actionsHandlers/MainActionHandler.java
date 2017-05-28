package it.polimi.ingsw.GC_26_actionsHandlers;

import it.polimi.ingsw.GC_26_gameLogic.GameElements;

/* a list of the actions : all of them are called by different controllers.
 */
public class MainActionHandler {
	private GameElements gameElements;
	private FirstActionHandler firstActionHandler;
	private SecondActionHandler secondActionHandler;
	 
	public MainActionHandler() {
		firstActionHandler =new FirstActionHandler(gameElements);
		secondActionHandler= new SecondActionHandler(gameElements);
		//TODO
		
	}

	
	public FirstActionHandler getFirstActionHandler() {
		return firstActionHandler;
	}
	
	public SecondActionHandler getSecondActionHandler() {
		return secondActionHandler;
	}
	
}
