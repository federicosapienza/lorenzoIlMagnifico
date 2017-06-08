package it.polimi.ingsw.GC_26_client_connection;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.GC_26_client.ClientController;
import it.polimi.ingsw.GC_26_client_clientLogic.IOlogic;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

public class ClientMain {
	private final static int PORT = 29997;
	private final static String IP="127.0.0.1";
	
	
	
	
	
	
	
	private void start() throws IOException{
		//scegliere connessione!
		ClientConnection connection=null;
		try {
			connection= new SocketOutClient(PORT , IP);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExecutorService pool = Executors.newFixedThreadPool(2);
		IOlogic iOlogic= new IOlogic(connection);
		SocketINClient socketINClient = new SocketINClient(PORT, IP);
		socketINClient.setController( new ClientController());
		pool.submit(iOlogic);
		pool.submit(socketINClient);
		//magari mettere a runnable il controller
		
	}
	
	
}
