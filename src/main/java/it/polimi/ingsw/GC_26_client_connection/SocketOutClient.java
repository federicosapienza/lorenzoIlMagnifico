package it.polimi.ingsw.GC_26_client_connection;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.GC_26_gameLogic.Action;

public class SocketOutClient implements ClientConnection{
	
	 Socket socket= null;
	    ObjectOutputStream objOut =  null;
		
		public SocketOutClient(int port, String ip) throws IOException {
	        this.socket= new Socket(ip, port);
			objOut = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
			objOut.flush();
			
	        }
	

	@Override
	public void login(String username, String password) {
		try {
			objOut.writeObject(username);
			objOut.writeObject(password);
			objOut.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendResponce(int responce) {
		try {
			objOut.writeObject(responce);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void performAction(Action action) {
		try {
			objOut.writeObject(action);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
