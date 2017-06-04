package it.polimi.ingsw.GC_26_api;

import java.rmi.*;

/**
 * Interfaccia dell'oggetto di callback Player. Consente al server di inviare al
 * client tutte le notifiche necessarie per il corretto svolgimento della
 * partita.
 * Player's interface: it allows to send to the server all the notifications that are necessary for the correct progress of a game
 */
public interface Player extends Remote {
	
	/**
	 * this allows to notify that the player has joined the game correctly.
	 * 
	 * @param g: game that the player has joined.
	 * @throws RemoteException
	 */
	void joinGame(Game g) throws RemoteException;

	/**
	 * the following method notifies that it's the turn of current player
	 * @throws RemoteException
	 */
	void itsYourTurn() throws RemoteException;

	/**
	 * the following method notifies that it's not your turn 
	 * @throws RemoteException
	 */
	void waitingYourTurn() throws RemoteException;

	/**
	 * the following method tests the connection of a player, in order to detect any possible disconnection 
	 * @throws RemoteException
	 */
	void ping() throws RemoteException;

	/**
	 * The following method notifies that foe number 1 has disconnected
	 * @throws RemoteException
	 */
	void foe1Disconnected() throws RemoteException;
	
	/**
	 * The following method notifies that foe number 2 has disconnected
	 * @throws RemoteException
	 */
	void foe2Disconnected() throws RemoteException;
	
	/**
	 * The following method notifies that foe number 3 has disconnected
	 * @throws RemoteException
	 */
	void foe3Disconnected() throws RemoteException;

	/**
	 * The following method notifies that foe number 1 has performed his action.
	 * @param x It's the ID of the action.
	 * @throws RemoteException
	 */
	void foe1HasMoved(int x) throws RemoteException;
	
	/**
	 * The following method notifies that foe number 2 has performed his action.
	 * @param y It's the ID of the action.
	 * @throws RemoteException
	 */
	void foe2HasMoved(int y) throws RemoteException;
	
	/**
	 * The following method notifies that foe number 3 has performed his action.
	 * @param z It's the ID of the action.
	 * @throws RemoteException
	 */
	void foe3HasMoved(int z) throws RemoteException;
	
	/**
	 * The following method notifies the player about the final result of the game.
	 * @param res YOU_WIN: you've won.
	 * 			  YOU_LOSE: you've lost
	 * @throws RemoteException
	 */
	void sendGameResult(Event res) throws RemoteException;
}
