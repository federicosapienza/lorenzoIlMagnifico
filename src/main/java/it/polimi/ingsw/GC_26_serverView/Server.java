package it.polimi.ingsw.GC_26_serverView;

import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import it.polimi.ingsw.GC_26_gameLogic.Game;
import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;
import it.polimi.ingsw.GC_26_readJson.ReadAll;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26_serverConnections.SocketServer;

public class Server {
	private GameInitialiserAndController game;
	private Set<GameInitialiserAndController> games = new HashSet<>();
	private Map<String ,String> listOfPlayers =new HashMap<>();
	private Cards cards;
	private BonusInterface bonus;
	private TimerValuesInterface times;
	
	
	private static Server server=null ;
	
	
    //Singleton pattern
    private Server(){
    	@SuppressWarnings("unused")
		Server server= new Server();
    }

    public static synchronized Server newServer() {
        if (server == null) {
        	server = new Server();
        }
        return server;
    }
	

	
	
	public void start(){
		ReadFromFile gamesSpecific= new ReadAll();
		
		gamesSpecific.start();
		cards= gamesSpecific.getCards();
		bonus = gamesSpecific.getBonus();
		times= null;
		
		//game = new GameInitialiserAndController(this , null, null, null , null);
		
		SocketServer socketServer=  SocketServer.newServer(this);
		socketServer.run();
		game= new GameInitialiserAndController(cards, bonus, times);
		
	}
	
	public synchronized void addClient(ServerConnectionToClient connection, String username){
		
		ClientMainServerView views= new ClientMainServerView(username, connection);
		connection.addViews( views);
		GameInitialiserAndController game= findPlayerInExistingGame(username);
		if(game!=null){
			game.addClientAgain(views);
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
		game.addClient(clientView);
		if(game.getNumOfPlayer()==4)
			newGame();
	}
	
	
	
	private void newGame() {
		GameInitialiserAndController temp= game;
		game= new GameInitialiserAndController(cards, bonus, times);
		
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
	
	
}
