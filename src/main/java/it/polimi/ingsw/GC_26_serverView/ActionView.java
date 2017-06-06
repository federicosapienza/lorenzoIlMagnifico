package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;

public class ActionView extends OutputView implements Observer<Action>{

	public ActionView(ServerConnectionToClient connection) {
		super(connection);
	}

	
	public void update(Action action) {
		super.getConnection().send(action);

	}
}