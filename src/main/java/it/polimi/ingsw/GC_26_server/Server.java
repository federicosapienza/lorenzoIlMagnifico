package it.polimi.ingsw.GC_26_server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;
import it.polimi.ingsw.GC_26_readJson.ReadAll;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26_serverConnections.SocketServer;
import it.polimi.ingsw.GC_26_serverView.ClientMainServerView;

public class Server {
	private GameInitialiserAndController game;
	private Map<String ,String> listOfPlayers =new HashMap<>();
	private Cards cards;
	private BonusInterface bonus;
	private TimerValuesInterface times;
	private Timer startGameTimer;
	private final static int PORT = 29997;
	
	public void start() {
		ReadFromFile gamesSpecific= new ReadAll();
		gamesSpecific.start();
		cards= gamesSpecific.getCards();
		bonus = gamesSpecific.getBonus();
		times= gamesSpecific.getTimes();
		game= new GameInitialiserAndController(cards, bonus, times);
		
		SocketServer socketServer = new SocketServer(this, PORT);
		try {
			socketServer.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		System.out.println("new Client");
		game.addClient(clientView);
		int num = game.getNumOfPlayer();
		if(num==2){  
			startGameTimer = new Timer(true);
			startGameTimer.schedule(new StartGameTask(this),(long) times.getStartingTimer()*1000 );
			return;
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

	
	
	public void endGame(GameInitialiserAndController game){
		//TODO
	}
	
	
	public static void main(String args[]){
		Server server =  new Server();
			server.start();
	}
	
	
}
