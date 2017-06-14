package it.polimi.ingsw.GC_26_client_connection;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import it.polimi.ingsw.GC_26_client.ClientController;
import it.polimi.ingsw.GC_26_client_clientLogic.InputlogicCli;
import it.polimi.ingsw.GC_26_client_clientLogic.MainClientView;
import it.polimi.ingsw.GC_26_client_clientLogic.Output;
import it.polimi.ingsw.GC_26_client_clientLogic.OutputCLI;

public class ClientMain {
	private final static int PORT = 29997;
	private final static String IP="127.0.0.1";
	
	
	
	
	
	
	
	private void start() throws IOException{
		//scegliere connessione!
		ClientConnection connection=null;
		Socket socket=null;
		try {
			 socket= new Socket(IP, PORT);
			connection= new SocketOutClient(socket);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainClientView view = new MainClientView();
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Output output= new OutputCLI();
		InputlogicCli iOlogic= new InputlogicCli(connection, view, output);
		SocketINClient socketINClient = new SocketINClient(socket);
		socketINClient.setController( new ClientController(iOlogic,view, output));
		pool.submit(iOlogic);
		pool.submit(socketINClient);
		//magari mettere a runnable il controller
		
	}
	
	public static void main(String args[]){
		ClientMain client = new ClientMain();
		try {
			client.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
