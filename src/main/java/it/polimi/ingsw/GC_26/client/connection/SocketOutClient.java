package it.polimi.ingsw.GC_26.client.connection;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.GC_26.messages.action.Action;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the part of the Socket that manages all the messages and commands that the client sends to the Server
 *
 */
public class SocketOutClient implements ClientConnection{
	Socket socket= null;
	ObjectOutputStream objOut =  null;
	private static final Logger LOG = Logger.getLogger(SocketOutClient.class.getName());

	/**
	 * Constructor: it creates a SocketOutClient based on the Socket contained in the parameter
	 * @param socket It's the Socket that is going to be used by this SocketOutClient
	 * @throws IOException
	 */
	public SocketOutClient(Socket socket) throws IOException {
        this.socket= socket;
		objOut = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
		objOut.flush();
		
        }
	
	/**
	 * Method called to do the login
	 */
	@Override
	public void login(String username) {
		try {
			objOut.writeUTF(username);
			objOut.flush();
			

			
		} catch (IOException e) {
			LOG.log(Level.SEVERE, " Socket connection error ", e);	

		}
		
	}

	/**
	 * Method that sends the response as a String to the Server
	 */
	@Override
	public void sendResponse(String string) {
		try {
			objOut.writeObject(string);
			objOut.flush();
			
		} catch (IOException e) {
			LOG.log(Level.SEVERE, " Socket connection error ", e);		
		}
	}
	
	/**
	 * Method called to perform the action contained in the parameter
	 */
	@Override
	public void performAction(Action action) {
		try {
			objOut.writeObject(action);
			objOut.flush();

		} catch (IOException e) {
			LOG.log( Level.SEVERE, "error in socket connection ", e);		
		}
		
	}
	
	/**
	 * Method called to close the connection
	 */
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
