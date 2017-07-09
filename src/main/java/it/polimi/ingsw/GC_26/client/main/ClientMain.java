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

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that contains the main method used to create a client and connect it to the Server
 *
 */
public class ClientMain {
	private final static int PORT = 29997;
	private static final Logger LOG = Logger.getLogger(ClientMain.class.getName());
	private SocketINClient socketINClient;
	private InputLogic inputLogic;
	private Socket socket;
	private Scanner scanner= new Scanner(System.in);
	
	/**
	 * Method that starts the creation of the client and connects it to the Server
	 * @param firstMatch It's a flag that indicates if the client is playing his first match or not 
	 * @param username It's the username of the client
	 */
	public synchronized void start(boolean firstMatch, String username) { 
		//username is set to null if first match, saves the name chosen in first match otherwise
		ClientConnection connection=null;
		
		try {
			socket= new Socket("127.0.0.1", PORT);
			connection= new SocketOutClient(socket);
			socketINClient = new SocketINClient(socket);
		 }catch (IOException e) {
			LOG.log( Level.SEVERE, "error in socket connection ", e);
			return;
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
	
	/**
	 * Method called to close the current game
	 */
	public synchronized void closeGame(){
		inputLogic.close();
		socketINClient.close();
		try {
			socket.close();
		} catch (IOException e) {
			LOG.log( Level.SEVERE, "error in socket connection ", e);	
		}
		
	}
	
	/**
	 * Method called to close completely the program
	 */
	public void closeProgram(){
		scanner.close();
		
	}
	
	/**
	 * Main method called to create a new ClientMain and start its initialization
	 * @param args
	 */
	public static void main(String[] args){
		ClientMain client = new ClientMain();
		client.start( true, null);
	}
}
