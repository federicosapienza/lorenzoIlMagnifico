package it.polimi.ingsw.GC_26.view.output;

import it.polimi.ingsw.GC_26.messages.action.ActionNotification;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26.utilities.observer.Observer;

public class ActionView extends OutputView implements Observer<ActionNotification>{
	
	public ActionView(ServerConnectionToClient connection) {
		super(connection);
	}

	@Override
	public void update(ActionNotification action) {
		super.getConnection().send(action);

	}
}