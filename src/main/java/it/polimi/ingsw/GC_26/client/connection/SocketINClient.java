package it.polimi.ingsw.GC_26.client.connection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.GC_26.client.ClientController;
import it.polimi.ingsw.GC_26.messages.Message;
import it.polimi.ingsw.GC_26.messages.action.ActionNotification;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.messages.describers.FamilyMembersDescriber;
import it.polimi.ingsw.GC_26.messages.describers.PositionDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.PlayerWallet;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the part of Socket that manages all the input that the Server receives from the client 
 *
 */
public class SocketINClient implements Runnable{
	private Socket socket= null;
	private ObjectInputStream objIn  = null;
	private ClientController controller=null;
	private boolean running= true;
	private final static Logger LOG = Logger.getLogger(SocketINClient.class.getName());
	
	/**
	 * Constructor: it creates the SocketINClient based on the Socket contained in the parameter
	 * @param socket It's the Socket that is going to be used by this SocketINClient
	 * @throws IOException
	 */
	public SocketINClient(Socket socket) throws IOException {
        this.socket= socket;
		objIn  = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
	}
	
	/**
	 * Method that sets the controller to the ClientController contained in the parameter
	 * @param controller It's the ClientController that has to be set as the controller of this SocketINClient
	 */
	public void setController(ClientController controller) {
		this.controller = controller;
	}

	/**
	 * Method to run the SocketINClient
	 */
	@Override
	public void run() {
		try {
			while (running){
				String string = objIn.readUTF();
				if("Entering in a game".equals(string)){//any change here must be changed also in server
					controller.setLoginDone();
					break; 
				}
			}
		
		controller.setLoginDone();

		while(running){
			Object object = objIn.readObject();
			if(object instanceof Message){
				Message message = (Message) object;
				controller.receiveMessage(message);
			}
			else if(object instanceof ActionNotification){
				ActionNotification action = (ActionNotification) object;
				controller.receiveAction(action);

			}
			else if(object instanceof CardDescriber){
				CardDescriber card = (CardDescriber) object;
				controller.receiveCard(card);

			}
			else if(object instanceof PlayerWallet){
				PlayerWallet wallet = (PlayerWallet) object;
				controller.receivePlayerPocket(wallet);
			}
			else if(object instanceof PositionDescriber){
				PositionDescriber positionDescriber = (PositionDescriber) object;
				controller.receivePosition(positionDescriber);
			}
			
			else if(object instanceof FamilyMembersDescriber){
				FamilyMembersDescriber familyMembersDescriber= (FamilyMembersDescriber) object;
				controller.receiveFamilyMembers(familyMembersDescriber);

			}
			
			else throw new IllegalArgumentException();
		
			
		}
		
	} catch (ClassNotFoundException e) {
		LOG.log(Level.SEVERE, "class not found ", e);	
		close();
	}
	
	catch(IOException e1){
		LOG.log(Level.SEVERE, "Socket interruption ", e1);
		close();


	}
	catch(IllegalArgumentException e2){
		LOG.log(Level.SEVERE, "Object received not known ", e2);	
		close();
	}

	finally {
		close();
	}
	}
	
	/**
	 * Method that closes the Socket
	 */
	public void close(){
		try {
			if(!socket.isClosed())
				socket.close();
			objIn.close();
			running=false;
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Can't close Socket connection. ", e);	
		}
	}

		
}