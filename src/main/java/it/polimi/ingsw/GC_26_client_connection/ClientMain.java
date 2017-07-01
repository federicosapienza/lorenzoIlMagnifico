package it.polimi.ingsw.GC_26_client_connection;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.*;

import it.polimi.ingsw.GC_26_client.ClientController;
import it.polimi.ingsw.GC_26_client_clientLogic.InputlogicCli;
import it.polimi.ingsw.GC_26_client_clientLogic.MainClientView;
import it.polimi.ingsw.GC_26_client_clientLogic.Output;
import it.polimi.ingsw.GC_26_client_clientLogic.OutputCLI;

public class ClientMain {
	private final static int PORT = 29997;
	private final static String IP="127.0.0.1";
	private static final Logger LOG = Logger.getLogger(ClientMain.class.getName());
	
	private ExecutorService pool;
	private SocketINClient socketINClient;
	private InputlogicCli inputlogicCli;
	private Socket socket;
	
	
	private synchronized void start(){
		ClientConnection connection=null;
		try {
			 socket= new Socket(IP, PORT);
			connection= new SocketOutClient(socket);
			socketINClient = new SocketINClient(socket);
		} catch (IOException e) {
			LOG.log( Level.SEVERE, "error in socket connection ", e);		
		}
		MainClientView view = new MainClientView();
		pool = Executors.newFixedThreadPool(2);
		Output output= new OutputCLI();
		inputlogicCli= new InputlogicCli(connection, view, output);
		socketINClient.setController( new ClientController(inputlogicCli,view, output,this));
		
		pool.submit(inputlogicCli);
		pool.submit(socketINClient);
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public synchronized void restart(){
		this.notifyAll();
		inputlogicCli.close();
		socketINClient.close();
		try {
			socket.close();
		} catch (IOException e) {
			LOG.log( Level.SEVERE, "error in socket connection ", e);	
		}
		
		boolean flag=true;;
		/*
		Scanner scanIN=new Scanner(System.in);
		while(true){
			System.out.println("Do you want to start a new game? 1 to continue , 0 to exit");
		
		
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
	*/
		System.out.println( "here2");
	//	scanIN.close();
		System.out.println("sto iniziando");
		if(flag)
			start();
		return;
	}
	
	
	public static void main(String[] args){
		ClientMain client = new ClientMain();
		client.start();
	}
}
