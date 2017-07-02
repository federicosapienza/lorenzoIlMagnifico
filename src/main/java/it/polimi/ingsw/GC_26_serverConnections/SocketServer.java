package it.polimi.ingsw.GC_26_serverConnections;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import it.polimi.ingsw.GC_26_server.Server;

public class SocketServer  {
	private static SocketServer instance= null; //singleton
	private final Server server;
	private final int port;
	public boolean stopped=false;
    

    private SocketServer (Server server, int PORT) {
       this.server= server;
       port=PORT;
    }

    public static  synchronized SocketServer getSocketServer(Server server, int port){ //singleton
    	if(instance==null)
    		instance= new SocketServer(server, port);
    	return instance;
    }
    
	
	public void run() throws IOException{
		ServerSocket welcomeSocket  = new ServerSocket(port);;
	    	ExecutorService pool=  Executors.newCachedThreadPool();

    		try {
   
     	    	System.out.println("server ready at port "+port);
 
    			while(true){
    				Socket socket = welcomeSocket.accept();
    				ServerConnectionToClient socketconnect =new ServerSocketToClient(socket, server); 
        			pool.submit(socketconnect);
    		}
    		
			}catch (Exception e) {
				e.printStackTrace();
    		}
    		finally {
				welcomeSocket.close();
			}
    	
    	
	}
	
}
