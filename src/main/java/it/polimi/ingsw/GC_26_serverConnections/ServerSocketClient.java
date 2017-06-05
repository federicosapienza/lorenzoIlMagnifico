package it.polimi.ingsw.GC_26_serverConnections;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import it.polimi.ingsw.GC_26_serverView.ClientMainServerView;
import it.polimi.ingsw.GC_26_serverView.Server;


public class ServerSocketClient  implements ClientConnection{
		private Socket socket;
		Server server;
		ObjectOutputStream objOut =  null;
        ObjectInputStream objIn  = null;

		public ServerSocketClient(Socket socket, Server server) throws IOException {
			this.socket=socket;
			objOut = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	        objIn  = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
	        this.server= server;
		
		}
		@Override
		public void run() {
			try{
				
				while(true){
						Boolean loginDone= false;
						String username=null;
						String password=null;
					while(!loginDone){
						objOut.writeUTF("Inserisci username");
						objOut.flush();
						username = objIn.readUTF();
						objOut.writeUTF("Inserisci password");
						objOut.flush();
						password = objIn.readUTF();
						loginDone= server.doLogin(username, password);
					}
					server.addClient(this, username);
					
						String line = objIn.readUTF();
						
						////
						if (line.equals("quit")) {
						// exits from the while
							break;
						}
						if(line.equals("name")){
						//	socketIn.nextLine();
						}
						else{
						}
						
						}
						// closes the scanner
					//	socketIn.close();
						// closes the printWriter
				//		socketOut.close();
				
			
			// closes the socket
				socket.close();
			} 
			catch (IOException e) {
			System.err.println(e.getMessage());
			}
			}
			
		
		public synchronized void write(Object obj){
			//System.out.println("Socket: sending the client the message: "+ obj);
	//		socketOut.println("SERVER: "+ obj);
	//		socketOut.flush();
		}
		@Override
		public void addViews(ClientMainServerView views) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public synchronized void send(Object object) {
			try {
				objOut.writeObject(object);
				objOut.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
			
		

}
