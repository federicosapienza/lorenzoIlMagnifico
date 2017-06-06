package it.polimi.ingsw.GC_26_client_connection;

import it.polimi.ingsw.GC_26_client_clientLogic.ClientController;

public interface ClientConnection extends Runnable{

	void setController(ClientController controller);
	

}
