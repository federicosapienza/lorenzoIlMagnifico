package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;

public abstract class OutputView {
	private ServerConnectionToClient connection;
	
	public OutputView(ServerConnectionToClient connection) {
		this.connection=connection;
	}

	protected ServerConnectionToClient getConnection() {
		return connection;
	}
	
		

}
