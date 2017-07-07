package it.polimi.ingsw.GC_26.server;

import java.util.TimerTask;

import it.polimi.ingsw.GC_26.server.main.Server;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the task that extends the TimerTask, used to manage the timer for the start of a new game
 *
 */
public class StartGameTask extends TimerTask{
	Server server;
	
	/**
	 * Constructor: it creates a StartGameTask for the Server contained in the parameter
	 * @param server It's the Server which needs to use this StartGameTask to manage the timer for the start of a new game
	 */
	public StartGameTask(Server server) {
		this.server=server;
	}
	
	/**
	 * Method called to run the StartGameTask
	 */
	@Override
	public void run() {
		server.newGame();
	}
	

}
