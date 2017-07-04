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
	private final static String IP="127.0.0.1";
	private static final Logger LOG = Logger.getLogger(ClientMain.class.getName());
	private SocketINClient socketINClient;
	private InputLogic inputLogic;
	private Socket socket;
	
	
	private synchronized void start( boolean firstMatch){
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
		inputLogic= new InputlogicCli( connection, view, output);
		socketINClient.setController( new ClientController(inputLogic,view, output,this));
		
		if(firstMatch){
			
			output.printTitle();
		}
		
		pool.submit(inputLogic);
		pool.submit(socketINClient);
		
	}
	

	public synchronized void restart(){
		this.notifyAll();
		inputLogic.close();
		socketINClient.close();
		try {
			socket.close();
		} catch (IOException e) {
			LOG.log( Level.SEVERE, "error in socket connection ", e);	
		}
		
		boolean flag=true;
		System.out.println("Do you want to start a new game? 1 to continue , 0 to exit");

		
		Scanner scanIN=new Scanner(System.in);
		while(true){
		
		
			while(!scanIN.hasNextInt()) { //used to be sure integer is an input
			    scanIN.next();
			    System.out.println("not valid input");
			}
			int value = scanIN.nextInt();
			System.out.println("here");
			if(value==1){
				flag=true;
				break;
			}
			if(value==0){
				flag=false;
				break;	
			}
			else System.out.println("not valid input");
			
	}
	
		System.out.println( "here2");
		System.out.println("sto iniziando");
		if(flag)
			start( false);
		else{
			scanIN.close();
			System.out.println("See you!");
		}
		return;
	}
	
	
	public static void main(String[] args){
		ClientMain client = new ClientMain();
		client.start( true);
	}
}
