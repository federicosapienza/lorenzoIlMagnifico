package it.polimi.ingsw.GC_26.view.output;

import it.polimi.ingsw.GC_26.messages.describers.PositionDescriber;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26.utilities.observer.Observer;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that represents the view for the position
 */
public class PositionView extends OutputView implements Observer<PositionDescriber>{

	/**
	 * Constructor: it creates a PositionView based on the ServerConnectionToClient contained in the parameter
	 * @param connection It's the ServerConnectionToClient which this PositionView is based on
	 */
	public PositionView(ServerConnectionToClient connection) {
		super(connection);
	}
	
	/**
	 * Method called to send the PositionDescriber contained in the parameter on the ServerConnectionToClient 
	 * which this PositionView is based on
	 */
	@Override
	public void update(PositionDescriber position) {
		super.getConnection().send(position);

	}
	
}
