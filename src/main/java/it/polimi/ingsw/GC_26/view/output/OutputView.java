package it.polimi.ingsw.GC_26.view.output;

import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;

public abstract class OutputView {
	private ServerConnectionToClient connection;
	
	public OutputView(ServerConnectionToClient connection) {
		this.connection=connection;
	}

	protected ServerConnectionToClient getConnection() {
		return connection;
	}
	
		

}
