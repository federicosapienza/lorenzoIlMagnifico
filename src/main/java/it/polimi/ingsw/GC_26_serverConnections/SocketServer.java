package it.polimi.ingsw.GC_26_serverConnections;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.GC_26_serverView.Server;

public class SocketServer  {
	private final Server server;
	private final int port;
    

    public SocketServer (Server server, int PORT) {
       this.server= server;
       port=PORT;
    }

    
    
	
	public void run(){
		ServerSocket welcomeSocket;
		ExecutorService pool;
    
    		try {
    			 welcomeSocket = new ServerSocket(port);
     	    	System.out.println("server ready at port "+port);
     	    	pool = Executors.newCachedThreadPool();
 
    			while(true){
    				Socket socket = welcomeSocket.accept();
    				ServerConnectionToClient socketconnect =new ServerSocketToClient(socket, server); 
        			System.out.println("test");
        			pool.submit(socketconnect);
    	    	
    	    	
    			
    			
    			
    			
    		}
    		
			}catch (Exception e) {
				e.printStackTrace();
    		}
    	
    	
	}
	
}
