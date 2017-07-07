package it.polimi.ingsw.GC_26.view.output;

import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the view for the output
 *
 */
public abstract class OutputView {
	private ServerConnectionToClient connection;
	
	/**
	 * Constructor: it creates an OutputView object based on the ServerConnectionToClient contained in the parameter
	 * @param connection
	 */
	public OutputView(ServerConnectionToClient connection) {
		this.connection=connection;
	}

	/**
	 * Method that returns the ServerConnectionToClient which this OutputView is based on
	 * @return the ServerConnectionToClient which this OutputView is based on
	 */
	protected ServerConnectionToClient getConnection() {
		return connection;
	}
	
		

}
