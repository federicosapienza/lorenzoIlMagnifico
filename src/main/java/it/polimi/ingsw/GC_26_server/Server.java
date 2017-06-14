package it.polimi.ingsw.GC_26_server;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;
import it.polimi.ingsw.GC_26_readJson.ReadAll;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26_serverConnections.SocketServer;
import it.polimi.ingsw.GC_26_serverView.ClientMainServerView;

public class Server {
	private GameInitialiserAndController game;
	private Set<GameInitialiserAndController> games = new HashSet<>();
	private Map<String ,String> listOfPlayers =new HashMap<>();
	private Cards cards;
	private BonusInterface bonus;
	private TimerValuesInterface times;
	private final static int PORT = 29997;
	
	public void start() {
		ReadFromFile gamesSpecific= new ReadAll();
		
		gamesSpecific.start();
		System.out.println("Server30");
		cards= gamesSpecific.getCards();
		bonus = gamesSpecific.getBonus();
		times= null;
		game= new GameInitialiserAndController(cards, bonus, times);
		
		SocketServer socketServer= new SocketServer(this, PORT);
		try {
			socketServer.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public synchronized void addClient(ServerConnectionToClient connection, String username){
		ClientMainServerView views= new ClientMainServerView(username, connection);
		connection.addViews( views);
		GameInitialiserAndController gamestarted= findPlayerInExistingGame(username);
		if(gamestarted!=null){
			gamestarted.addClientAgain(views);
			return;
		}
		else enterInANewGame(views);
		
	}
//	public synchronized boolean isPlayerSignedIn(String username){
	//	return listOfPlayers.containsKey(username);
//	}
	
	public synchronized boolean doLogin(String username, String password){
		if(listOfPlayers.containsKey(username)){
			if(listOfPlayers.get(username).equals(password)){
				return true;}
			else return false;		
		}
		listOfPlayers.put(username, password);
		return true;
	}
	
	
	private  void enterInANewGame(ClientMainServerView clientView){
		System.out.println("new Client");
		game.addClient(clientView);
		if(game.getNumOfPlayer()==2){  //TODO  //preparing for a new game
			game.initialiseGame();
			games.add(game);
			newGame();
			
		}	
	}

	
	
	private void newGame() {
		this.game= new GameInitialiserAndController(cards, bonus, times);
	}

	private GameInitialiserAndController findPlayerInExistingGame(String playerName){
		for(GameInitialiserAndController game: games){
			if(game.isPlayerHere(playerName))
				return game;
		}
		 return null;
	}
	
	
	public void endGame(GameInitialiserAndController game){
		games.remove(game);
	}
	
	
	public static void main(String args[]){
		Server  server =  new Server();
			server.start();
	}
	
	
}
