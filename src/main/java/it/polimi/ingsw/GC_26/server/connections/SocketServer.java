package it.polimi.ingsw.GC_26.server.connections;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.GC_26.server.main.Server;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the Socket Server
 *
 */
public class SocketServer  {
	private static SocketServer instance = null; //singleton
	private final Server server;
	private final int port;
	private boolean stopped=false;
    
	/**
	 * Constructor: it creates a SocketServer based on the server and the port indicated in the parameters
	 * @param server It's the server of this SocketServer
	 * @param PORT It's the port of the SocketServer
	 */
	private SocketServer (Server server, int PORT) {
		this.server= server;
		port=PORT;
	}
	
	/**
	 * Method that returns the instance of this class. If the instance is null, it is set to a new SocketServer created with the
	 * Server and port contained in the parameters
	 * @param server It's the Server of the instance of SocketServer to create if the instance is null
	 * @param port It's the port of the instance of SocketServer to create if the instance is null
	 * @return the instance of this class
	 */
	public static synchronized SocketServer getSocketServer(Server server, int port) { //singleton
		if(instance==null)
			instance = new SocketServer(server, port);
		return instance;
    }
    
	/**
	 * Method called to run the SocketServer
	 * @throws IOException if failed or interrupted I/O operations have occurred
	 */
	public void run() throws IOException{
		ServerSocket welcomeSocket  = new ServerSocket(port);
	    	ExecutorService pool=  Executors.newCachedThreadPool();

    		try {
   
     	    	System.out.println("server ready at port "+port);
 
    			while(!stopped){
    				Socket socket = welcomeSocket.accept();
    				ServerConnectionToClient socketconnect =new ServerSocketToClient(socket, server); 
        			pool.submit(socketconnect);
        		}
    		
			
    		}
    		finally {
    			welcomeSocket.close();
		}
	}
	
}
