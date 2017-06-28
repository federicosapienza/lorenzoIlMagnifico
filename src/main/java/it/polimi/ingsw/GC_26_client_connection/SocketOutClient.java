package it.polimi.ingsw.GC_26_client_connection;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.GC_26_gameLogic.Action;

public class SocketOutClient implements ClientConnection{
	
	 Socket socket= null;
	 ObjectOutputStream objOut =  null;
		
	public SocketOutClient(Socket socket) throws IOException {
        this.socket= socket;
		objOut = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
		objOut.flush();
		
        }
	

	@Override
	public void login(String username) {
		try {

			objOut.writeUTF(username);
			objOut.flush();
			

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendResponce(String string) {
		try {
			objOut.writeObject(string);
			objOut.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void performAction(Action action) {
		try {
			objOut.writeObject(action);
			objOut.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void close() {
		try { //socket is closed by input stream
			objOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
