package it.polimi.ingsw.GC_26_client_connection;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.GC_26_client_clientLogic.ClientController;

public class ClientMain {
	private final static int PORT = 29997;
	private final static String IP="127.0.0.1";
	
	private void start(){
		//scegliere connessione!
		ClientConnection connection=null;
		try {
			connection= new SocketClient(PORT , IP);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		ClientController controller= new ClientController(connection);
		pool.submit(controller);
		connection.setController(controller);
		pool.submit(connection);
		
		
		
	}
	
	
}
