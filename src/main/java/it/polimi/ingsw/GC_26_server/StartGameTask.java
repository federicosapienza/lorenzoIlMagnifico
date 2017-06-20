package it.polimi.ingsw.GC_26_server;

import java.util.TimerTask;

public class StartGameTask extends TimerTask{
	Server server;
	
	public StartGameTask(Server server) {
		this.server=server;
	}
	
	@Override
	public void run() {
		server.newGame();
		
	}
	

}
