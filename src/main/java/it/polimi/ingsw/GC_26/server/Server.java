package it.polimi.ingsw.GC_26.server;

import java.io.IOException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.GC_26.controller.GameInitialiserAndController;
import it.polimi.ingsw.GC_26.jsonReader.BonusInterface;
import it.polimi.ingsw.GC_26.jsonReader.Cards;
import it.polimi.ingsw.GC_26.jsonReader.ReadAll;
import it.polimi.ingsw.GC_26.jsonReader.TimerValuesInterface;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26.server.connections.SocketServer;
import it.polimi.ingsw.GC_26.view.ClientMainServerView;

public class Server {
	private static Server instance=null ; //singleton 
	private GameInitialiserAndController game;
	private Cards cards;
	private BonusInterface bonus;
	private TimerValuesInterface times;
	private Timer startGameTimer;
	private final static int PORT = 29997;
	private static final Logger LOG = Logger.getLogger(Server.class.getName());

	
	public static synchronized Server getServer(){ //singleton
		if(instance==null){
			instance=new Server();
		}
		return instance;
	}
	
	public void start() {
		ReadFromFile gamesSpecific= new ReadAll();
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

	
	public synchronized void addClient(ServerConnectionToClient connection, String username){
		if (connection == null || username == null) {
			throw new NullPointerException();
		}
		ClientMainServerView views= new ClientMainServerView(username, connection, times);
		connection.addViews( views);
		enterInANewGame(views); 
		
	}

	
	
	private  void enterInANewGame(ClientMainServerView clientView){
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

	
	
	public void newGame() {
		synchronized (this) {//to avoid synchronization risks between new requests and timer
		game.initialiseGame();
		this.game= new GameInitialiserAndController(cards, bonus, times);
	}
	}

	

	
	public static void main(String[] args){
		Server server =  Server.getServer();
		server.start();
	}
	
	
}
