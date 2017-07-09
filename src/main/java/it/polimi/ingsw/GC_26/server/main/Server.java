package it.polimi.ingsw.GC_26.server.main;

import java.io.IOException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.GC_26.controller.GameInitialiserAndController;
import it.polimi.ingsw.GC_26.json_reader.BonusInterface;
import it.polimi.ingsw.GC_26.json_reader.Cards;
import it.polimi.ingsw.GC_26.json_reader.ReadAll;
import it.polimi.ingsw.GC_26.json_reader.TimerValuesInterface;
import it.polimi.ingsw.GC_26.server.ReadFromFile;
import it.polimi.ingsw.GC_26.server.StartGameTask;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26.server.connections.SocketServer;
import it.polimi.ingsw.GC_26.view.ClientMainServerView;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the Server
 *
 */
public class Server {
	private static Server instance=null ; //singleton 
	private GameInitialiserAndController game;
	private Cards cards;
	private BonusInterface bonus;
	private TimerValuesInterface times;
	private Timer startGameTimer;
	private final static int PORT = 29997;
	private static final Logger LOG = Logger.getLogger(Server.class.getName());

	/**
	 * Method that returns the instance of this class. If the instance is null, a new Server is created and the instance is set to this 
	 * new Server 
	 * @return the instance of this class
	 */
	public static synchronized Server getServer(){ //singleton
		if(instance==null){
			instance=new Server();
		}
		return instance;
	}
	
	/**
	 * Method called to start the Server, reading all the elements from the Json file and getting the corresponding SocketServer 
	 */
	public void start() {
		ReadFromFile gamesSpecific = new ReadAll();
		gamesSpecific.start();
		cards= gamesSpecific.getCards();
		bonus = gamesSpecific.getBonus();
		times= gamesSpecific.getTimes();
		game= new GameInitialiserAndController(cards, bonus, times);
		
		SocketServer socketServer = SocketServer.getSocketServer(this, PORT);
		try {
			socketServer.run();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Can't establish Socket connection. ", e);	

		}
		
		
	}

	/**
	 * Method called to add a new client to the Server.
	 * @param connection It's the ServerConnectionToClient that connects the Server with the client
	 * @param username It's the username of the new client that has to be added
	 */
	public synchronized void addClient(ServerConnectionToClient connection, String username){
		if (connection == null || username == null) {
			throw new NullPointerException();
		}
		ClientMainServerView views= new ClientMainServerView(username, connection, times);
		connection.addViews(views);
		enterInANewGame(views); 
		
	}

	/**
	 * Method called to add the ClientMainServerView contained in the parameter to the GameInitialiserAndController of this Server:
	 * this means that the client associated with the ClientMainServerView contained in the parameter is added to a new game managed
	 * by this Server
	 * @param clientView
	 */
	private void enterInANewGame(ClientMainServerView clientView){
		if (clientView == null) {
			throw new NullPointerException();
		}
		synchronized (this) {  //to avoid synchronization risks between new requests and timer

		game.addClient(clientView);
		int num = game.getNumOfPlayer();
		if(num==2){  
			startGameTimer = new Timer(true);
			startGameTimer.schedule(new StartGameTask(this),(long) times.getStartingTimer()*1000 );
			return ;
		}
		if(num==4){
			startGameTimer.cancel();
			newGame();
		}

		}	
	}

	/**
	 * Method called to create a new game managed by this Server
	 */
	public void newGame() {
		synchronized (this) {//to avoid synchronization risks between new requests and timer
		game.initialiseGame();
		this.game= new GameInitialiserAndController(cards, bonus, times);
		}
	}

	/**
	 * Main method to call to start the Server 
	 * @param args
	 */
	public static void main(String[] args){
		Server server = Server.getServer();
		server.start();
	}
	
	
}
