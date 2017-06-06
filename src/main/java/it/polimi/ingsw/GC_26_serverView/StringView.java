package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;

public class StringView extends OutputView implements Observer<String>{

	public StringView(ServerConnectionToClient connection) {
		super(connection);
	}

	
	
	public void update(String string) {
		super.getConnection().send(string);

		

	
	}
	

}
