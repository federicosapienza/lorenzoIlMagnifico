package it.polimi.ingsw.GC_26.server.connections;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.GC_26.messages.Message;
import it.polimi.ingsw.GC_26.messages.action.Action;
import it.polimi.ingsw.GC_26.messages.action.ActionNotification;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.messages.describers.FamilyMembersDescriber;
import it.polimi.ingsw.GC_26.messages.describers.PositionDescriber;
import it.polimi.ingsw.GC_26.model.game.game_components.resourcesAndPoints.PlayerWallet;
import it.polimi.ingsw.GC_26.server.main.Server;
import it.polimi.ingsw.GC_26.view.ClientMainServerView;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class which implements the ServerConnectionToClient interface
 *
 */
public class ServerSocketToClient implements ServerConnectionToClient{
	private final Socket socket;
	private Server server;
	private ObjectOutputStream objOut =  null;
	private ObjectInputStream objIn  = null;
	private ClientMainServerView views= null;
	private boolean closed=false;
	private static final Logger LOG = Logger.getLogger(ServerSocketToClient.class.getName());
	private static final String errorString = "error in socket connection ";

	/**
	 * Constructor: it creates a new ServerSocketToClient with the Socket and Server contained in the parameters and with a new
	 * flushed ObjectOutputStream and new ObjectInputStream based on the Socket contained in the parameter
	 * @param socket
	 * @param server
	 * @throws IOException
	 */
	public ServerSocketToClient(Socket socket, Server server) throws IOException {
		this.socket = socket;
		this.server = server;
		objOut = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
		objOut.flush();
		objIn  = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));

	}
	
	/**
	 * Method which sets the ClientMainServerView contained in the parameter to the current view
	 */
	@Override
	public void addViews(ClientMainServerView views) {
		this.views = views;
	}
	
	/**
	 * Method called to run the ServerSocketToClient
	 */
	@Override
	public void run() {
		try{

			objOut.writeUTF("Welcome!");
			objOut.flush();
			Boolean loginDone= false;
			String username=null;
			while(!loginDone){
				objOut.writeUTF("Insert username");
				objOut.flush();
				username = objIn.readUTF();
				if(username==null){
					continue;

				}
				else{
					loginDone=true;
					objOut.writeUTF("Entering in a game");
				objOut.flush();}
			}
			server.addClient(this, username);
			while(!closed){
				
				//reading objects
				Object object = objIn.readObject();
				if(object instanceof String){
					String string = (String) object;
					views.getStringInputView().notifyNewString(string);
					
				
					
				}
				if(object instanceof Action){
					Action action = (Action) object;
					views.getActionInputView().notifyNewAction(action);
			} 
			}	
		} 
		catch (IOException e) {
			closed= true;
			LOG.log( Level.FINE, errorString , e);
		} catch (ClassNotFoundException e1) {
			LOG.log( Level.SEVERE, "object not recognised ", e1);		

		}
		finally{
			closeSocket();
		}
	}
	
	/**
	 * Method that closes the Socket, the ObjectOutputStream and the ObjectInputStream
	 */
	private void closeSocket(){
		try {
			socket.close();
			objOut.close();
			objIn.close();
		} catch (IOException e) {
			LOG.log( Level.SEVERE, errorString, e);		

		}
	}
	
	/**
	 * Method that sends the Object contained in the parameter 
	 * @param object It's the object which has to be sent
	 */
	private synchronized void sendMethod(Object object) {
		if(!closed){
		try {
			objOut.writeObject(object);
			objOut.flush();
		} catch (IOException e) {
			LOG.log( Level.SEVERE, errorString, e);		
		}
		}
		
	}
	
	/**
	 * Method that sends the PlayerWallet contained in the parameter
	 */
	@Override
	public void send(PlayerWallet wallet) {
		sendMethod(wallet);
	}
	
	/**
	 * Method that sends the Message contained in the parameter
	 */
	@Override
	public void send(Message message) {
		sendMethod(message);
		
	}
	
	/**
	 * Method that sends the CardDescriber contained in the parameter
	 */
	@Override
	public void send(CardDescriber cardDescriber) {
		sendMethod(cardDescriber);
		
	}
	
	/**
	 * Method that sends the ActionNotification contained in the parameter
	 */
	@Override
	public void send(ActionNotification action) {
		sendMethod(action);
		
	}

	/**
	 * Method that sends the PositionDescriber contained in the parameter
	 */
	@Override
	public void send(PositionDescriber position) {
		sendMethod(position);
	}

	/**
	 * Method that sends the FamilyMembersDescriber contained in the parameter
	 */
	@Override
	public void send(FamilyMembersDescriber familyMembersDescriber) {
		sendMethod(familyMembersDescriber);
		
	}

}