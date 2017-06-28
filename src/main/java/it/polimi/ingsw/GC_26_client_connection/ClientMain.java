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
	
	
	
	private synchronized void start(){
		ClientConnection connection=null;
		Socket socket=null;
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
		this.notify();
		inputlogicCli.close();
		socketINClient.close();
		
		Scanner scanIN=new Scanner(System.in);

		while(true){
			System.out.println("Do you want to start a new game? Y/N");
		
			while(!scanIN.hasNextLine()) { 
			    scanIN.next();
			    System.out.println("not valid input");
			}
			String value = scanIN.nextLine();
			if(value.equals("Y")){
				start();
				break;
			}
			if(value.equals("N"))
				break;			
	}
		scanIN.close();
	}
	
	
	public static void main(String[] args){
		ClientMain client = new ClientMain();
		client.start();
	}
}
