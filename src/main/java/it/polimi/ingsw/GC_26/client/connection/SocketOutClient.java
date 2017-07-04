package it.polimi.ingsw.GC_26.client.connection;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.GC_26.model.game.action.Action;

public class SocketOutClient implements ClientConnection{
	
	 Socket socket= null;
	 ObjectOutputStream objOut =  null;
	private static final Logger LOG = Logger.getLogger(SocketOutClient.class.getName());

		
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
			LOG.log(Level.SEVERE, " Socket connection error ", e);	

		}
		
	}

	@Override
	public void sendResponce(String string) {
		try {
			objOut.writeObject(string);
			objOut.flush();
			
		} catch (IOException e) {
			LOG.log(Level.SEVERE, " Socket connection error ", e);		
		}
	}

	@Override
	public void performAction(Action action) {
		try {
			objOut.writeObject(action);
			objOut.flush();

		} catch (IOException e) {
			LOG.log( Level.SEVERE, "error in socket connection ", e);		
		}
		
	}
	
	@Override
	public void close() {
		try { //socket is closed by input stream
			objOut.close();
			socket.close();
		} catch (IOException e) {
			LOG.log( Level.SEVERE, "error in socket connection ", e);		
		}
		
	}
	

}
