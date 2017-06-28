package it.polimi.ingsw.GC_26_client_connection;

import it.polimi.ingsw.GC_26_gameLogic.Action;

public interface ClientConnection {

	

	void login(String username);

	void sendResponce(String temp);

	void performAction(Action action);

	void close();
	

}