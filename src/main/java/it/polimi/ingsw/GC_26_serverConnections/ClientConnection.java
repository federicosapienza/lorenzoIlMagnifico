package it.polimi.ingsw.GC_26_serverConnections;

import it.polimi.ingsw.GC_26_serverView.ClientMainServerView;

public interface ClientConnection extends Runnable{

	void addViews(ClientMainServerView views);
	
	void send(Object object);

}
