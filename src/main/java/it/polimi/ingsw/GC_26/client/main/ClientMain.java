package it.polimi.ingsw.GC_26.client.main;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.*;

import it.polimi.ingsw.GC_26.client.ClientController;
import it.polimi.ingsw.GC_26.client.cli.InputLogic;
import it.polimi.ingsw.GC_26.client.cli.InputlogicCli;
import it.polimi.ingsw.GC_26.client.cli.Output;
import it.polimi.ingsw.GC_26.client.cli.OutputCLI;
import it.polimi.ingsw.GC_26.client.connection.ClientConnection;
import it.polimi.ingsw.GC_26.client.connection.SocketINClient;
import it.polimi.ingsw.GC_26.client.connection.SocketOutClient;
import it.polimi.ingsw.GC_26.client.view.MainClientView;

public class ClientMain {
	private final static int PORT = 29997;
	private final static String IP="127.0.0.1";  //connect to local host
	private static final Logger LOG = Logger.getLogger(ClientMain.class.getName());
	private SocketINClient socketINClient;
	private InputLogic inputLogic;
	private Socket socket;
	private Scanner scanner= new Scanner(System.in);
	
	
	public synchronized void start( boolean firstMatch, String username){ 
		//username is set to null if first match, saves the name chosen in first match otherwise
		ClientConnection connection=null;
		try {
			 socket= new Socket(IP, PORT);
			connection= new SocketOutClient(socket);
			socketINClient = new SocketINClient(socket);
		} catch (IOException e) {
			LOG.log( Level.SEVERE, "error in socket connection ", e);		
		}
		MainClientView view = new MainClientView();
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Output output= new OutputCLI();
		inputLogic= new InputlogicCli(username, this, scanner, connection, view, output);
		socketINClient.setController( new ClientController(inputLogic,view, output,this));
		
		if(firstMatch){
			
			output.printTitle();
		}
		
		pool.submit(inputLogic);
		pool.submit(socketINClient);
		pool.shutdown();
		
	}
	
	
	
	
	public synchronized void closeGame(){
		inputLogic.close();
		socketINClient.close();
		try {
			socket.close();
		} catch (IOException e) {
			LOG.log( Level.SEVERE, "error in socket connection ", e);	
		}
		
	}
	public void closeProgram(){
		scanner.close();
		
	}
	
	
	public static void main(String[] args){
		ClientMain client = new ClientMain();
		client.start( true, null);
	}
}
