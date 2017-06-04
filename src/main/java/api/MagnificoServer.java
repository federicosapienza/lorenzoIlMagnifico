package api;

import java.rmi.*;
import java.util.*;

/**
 * Interface of the remote object MagnificoServer.
 */
public interface MagnificoServer extends Remote {

	/**
	 * it allows to register a player to the server, given a username. 
	 * When registering, it's not possible to have two or more players with the same username 
	 * @param p Registering player 
	 * @param playerName player's username
	 * @return true if the registration has been successful, i.e. there isn't any player with the same username, 
	 *  else it returns false.
	 * @throws RemoteException
	 */
	boolean register(Player p, String playerName) throws RemoteException;

	/**
	 * Consente di creare una nuova partita a un player, dato un idGame
	 * it allows a player to creare a new game, given an idGame
	 * @param p player that creates the game
	 * @param idGame must have the following format: 
	 * 			     nickname-time_of_creation_of_the_game
	 *               Es: mrossi-12:23:22
	 * @throws RemoteException
	 */
	void createGame(Player p, String idGame) throws RemoteException;

	/**
	 * Consente ad un player di aggiungersi a una partita,avente come id
	 * <code>idgame</code>, creata da un altro player e cominciare cosi a
	 * giocare.
	 * It allows a player to add himself to a game with id idGame, created by another player, and start to play, if timeout ends.
	 * @param p player who wants to join the game
	 * @param idGame must have the following format: 
	 * 			     nickname-time_of_creation_of_the_game
	 *               Es: mrossi-12:23:22
	 * @throws RemoteException
	 * @throws GameJoinException
	 *             if the game is full of players or it's not available because deleted.
	 */
	void joinGame(Player p, String idGame) throws RemoteException,
			GameJoinException;

	/**
	 * It returns the list with all the players that have created a game and are waiting for other players 
	 * or are still playing a game.
	 * @return list of players waiting for other players or still playing a game.
	 * @throws RemoteException
	 */
	ArrayList<String> getAllChallengers() throws RemoteException;

	/**
	 * Consente di rimuovere una partita dalla lista delle partite mantenuta sul
	 * server.
	 * It allows to remove a game from the list of the games maintained on the server
	 * @param idGame id of the game that has to be removed. It must have the following format: 
	 * 			     nickname-time_of_creation_of_the_game
	 *               Es: mrossi-12:23:22
	 * @return Updated list of the players after removing.
	 * @throws RemoteException
	 */
	ArrayList<String> removeGame(String idGame) throws RemoteException;

	/**
	 * It allows to unregister a player, given his username, when he disconnects from the game.
	 * @param playerName
	 *            username of the player that has to be unregistered.
	 * @throws RemoteException
	 */
	void unregister(String playerName) throws RemoteException;

}
