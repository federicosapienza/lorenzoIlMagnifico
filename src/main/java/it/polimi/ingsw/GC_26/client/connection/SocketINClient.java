package it.polimi.ingsw.GC_26.client.connection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.GC_26.client.ClientController;
import it.polimi.ingsw.GC_26.messages.Message;
import it.polimi.ingsw.GC_26.model.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.describers.PositionDescriber;
import it.polimi.ingsw.GC_26.model.game.action.ActionNotification;
import it.polimi.ingsw.GC_26.model.game.gameComponents.familyMembers.FamilyMembersDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.PlayerWallet;

public class SocketINClient implements Runnable{
	private	 Socket socket= null;
	private   ObjectInputStream objIn  = null;
	private  ClientController controller=null;
	private boolean running= true;
	private final static Logger LOG = Logger.getLogger(SocketINClient.class.getName());
		
		public SocketINClient(Socket socket) throws IOException {
	        this.socket= socket;
			objIn  = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

	        }
		
		public void setController(ClientController controller) {
			this.controller = controller;
		}

		@Override
		public void run() {
					try {
						while (running){
				        String string = objIn.readUTF();
				        System.out.println(string);
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
					
				
				}catch (ClassNotFoundException e) {
					LOG.log(Level.SEVERE, "class not found ", e);	

				}
				
				catch(IOException e1){
					LOG.log(Level.SEVERE, "Socket interruption ", e1);	


				}
				catch(IllegalArgumentException e2){
					LOG.log(Level.SEVERE, "Object received not known ", e2);	

				}
			
				finally {
					close();
				}
		}
		
	public void close(){
		try {
			socket.close();
			objIn.close();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Can't establish Socket connection. ", e);	
		}
	}

		
}