package it.polimi.ingsw.GC_26_serverView;

import java.net.Socket;
import java.util.HashSet;
import java.util.Set;


import it.polimi.ingsw.GC_26_cards.Cards;
import it.polimi.ingsw.GC_26_gameLogic.Game;
import it.polimi.ingsw.GC_26_serverConnections.ClientConnection;
import it.polimi.ingsw.GC_26_serverConnections.SocketServer;

public class Server {
	private GameInitialiserAndController game;
	private Set<GameInitialiserAndController> games = new HashSet<>();
	
	
	
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
		//TODO lettura da file: ottengo 2 classi cards e bonus
		
		game = new GameInitialiserAndController(this , null, null, null , null);
		
		SocketServer socket=  SocketServer.newServer(this);
		Thread threadSocket = new Thread(socket);
		threadSocket.run();
		
	}
	
	public synchronized void addClient(ClientConnection connection, String username){
		
		ClientMainServerView views= new ClientMainServerView(username, connection);
		connection.addViews( views);
		GameInitialiserAndController game= findPlayerInExistingGame(username);
		if(game!=null){
			game.addClientAgain(views);
			return;
		}			
		else enterInANewGame(views);
		
	}
	
	public synchronized boolean doLogin(String username, String password){
		//todo
		return true;
	}
	
	
	private  void enterInANewGame(ClientMainServerView clientView){
		game.addClient(clientView);
	}
	
	private void startGame(Game game){
		game.initialiseGame();
		
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
