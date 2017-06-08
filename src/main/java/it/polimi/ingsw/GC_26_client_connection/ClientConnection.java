package it.polimi.ingsw.GC_26_client_connection;

import it.polimi.ingsw.GC_26_gameLogic.Action;

public interface ClientConnection {

	

	void login(String username, String password);

	void sendResponce(int responce);

	void performAction(Action action);
	

}
