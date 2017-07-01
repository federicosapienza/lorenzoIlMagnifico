package it.polimi.ingsw.GC_26_client_connection;

import it.polimi.ingsw.GC_26_gameLogic.Action;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the interface for the client's connection
 *
 */
public interface ClientConnection {

	

	void login(String username);

	void sendResponce(String temp);

	void performAction(Action action);

	void close();
	

}