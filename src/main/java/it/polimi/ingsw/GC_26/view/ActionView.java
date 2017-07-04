package it.polimi.ingsw.GC_26.view;

import it.polimi.ingsw.GC_26.model.game.action.ActionNotification;
import it.polimi.ingsw.GC_26.server.Observer;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;

public class ActionView extends OutputView implements Observer<ActionNotification>{

	public ActionView(ServerConnectionToClient connection) {
		super(connection);
	}

	
	public void update(ActionNotification action) {
		super.getConnection().send(action);

	}
}