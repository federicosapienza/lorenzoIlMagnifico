package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_gameLogic.ActionNotification;
import it.polimi.ingsw.GC_26_server.Observer;
import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;

public class ActionView extends OutputView implements Observer<ActionNotification>{

	public ActionView(ServerConnectionToClient connection) {
		super(connection);
	}

	
	public void update(ActionNotification action) {
		super.getConnection().send(action);

	}
}