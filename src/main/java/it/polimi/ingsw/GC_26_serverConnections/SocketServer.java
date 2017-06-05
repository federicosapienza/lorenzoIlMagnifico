package it.polimi.ingsw.GC_26_serverConnections;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.GC_26_serverView.Server;

public class SocketServer implements Runnable {

//singleton pattern
private static SocketServer socketServer=null ;
		private static final int PORT = 29999;

	
    //Singleton pattern
    private SocketServer(){
    	@SuppressWarnings("unused")
    	SocketServer server= new SocketServer();
    }

    public static synchronized SocketServer newServer(Server server) {
        if (socketServer == null) {
        	socketServer = new SocketServer();
        }
        return socketServer;
    }

    
    
	@SuppressWarnings("resource")
	@Override
	public void run(){
		ServerSocket welcomeSocket;
		ExecutorService pool;
    	while(true){
    		try {
    			 welcomeSocket = new ServerSocket(PORT);
    	    	System.out.println("server ready at port "+PORT);
    	    	
    	    	pool = Executors.newCachedThreadPool();
    	    	
    			Socket socket = welcomeSocket.accept();
    			
    			
    		}catch (Exception e) {
    		
			}
    		}
    	
    	
	}
	
}
