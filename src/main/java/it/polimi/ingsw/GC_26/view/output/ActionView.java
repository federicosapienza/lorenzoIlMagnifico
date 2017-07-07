package it.polimi.ingsw.GC_26.view.output;

import it.polimi.ingsw.GC_26.messages.action.ActionNotification;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26.utilities.observer.Observer;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that represents the view for the actions
 */
public class ActionView extends OutputView implements Observer<ActionNotification>{
	
	/**
	 * Constructor: it creates an ActionView based on the ServerConnectionToClient contained in the parameter
	 * @param connection It's the ServerConnectionToClient which this ActionView is based on
	 */
	public ActionView(ServerConnectionToClient connection) {
		super(connection);
	}

	/**
	 * Method called to send the ActionNotification contained in the parameter on the ServerConnectionToClient which this 
	 * ActionView is based on
	 */
	@Override
	public void update(ActionNotification action) {
		super.getConnection().send(action);

	}
}