package it.polimi.ingsw.GC_26_client_connection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import it.polimi.ingsw.GC_26_board.PositionDescriber;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_client.ClientController;
import it.polimi.ingsw.GC_26_gameLogic.ActionNotification;
import it.polimi.ingsw.GC_26_utilities.Message;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

public class SocketINClient implements Runnable{
	private	 Socket socket= null;
	 private   ObjectInputStream objIn  = null;
	 private  ClientController controller=null;;
		
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
					while (true){
			        String string = objIn.readUTF();
			        System.out.println(string);
			        if(string.equals("Login or signing in successful")){//any change here must be changed also in server
			        	controller.setLoginDone();
			        	break;
			        }
					}
					controller.setLoginDone();

					while(true){
					Object object = objIn.readObject();

					if(object instanceof Message){
						Message message = (Message) object;
						controller.receiveMessage(message);
						continue;
					}
					if(object instanceof ActionNotification){
						ActionNotification action = (ActionNotification) object;
						controller.receiveAction(action);
						continue;

					}
					if(object instanceof CardDescriber){
						CardDescriber card = (CardDescriber) object;
						controller.receiveCard(card);
						continue;

					}
					if(object instanceof PlayerWallet){
						PlayerWallet wallet = (PlayerWallet) object;
						controller.receivePlayerPocket(wallet);
						continue;

 					}
					if(object instanceof PositionDescriber){
						PositionDescriber positionDescriber = (PositionDescriber) object;
						controller.receivePosition(positionDescriber);
						continue;}

					throw new IllegalArgumentException();
				
					
					}
					
				
				}catch (ClassNotFoundException e) {
					e.printStackTrace();
;
				}
				
				catch(IOException e1){
							//TODO

				}
				catch(IllegalArgumentException e2){
					//TODO
				}
				
				
				finally {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
		
		}

		
}
