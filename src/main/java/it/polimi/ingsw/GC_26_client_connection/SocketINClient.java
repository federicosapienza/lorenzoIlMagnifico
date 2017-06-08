package it.polimi.ingsw.GC_26_client_connection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.sound.midi.ControllerEventListener;

import it.polimi.ingsw.GC_26_board.PositionDescriber;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_client.ClientController;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.ActionNotification;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

public class SocketINClient implements Runnable{
	private	 Socket socket= null;
	 private   ObjectInputStream objIn  = null;
	 private  ClientController controller=null;;
		
		public SocketINClient(int port, String ip) throws IOException {
	        this.socket= new Socket(ip, port);
			objIn  = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

	        }
		
		public void setController(ClientController controller) {
			this.controller = controller;
		}

		@Override
		public void run() {
			 
				try {
					while (true){
			        String test = objIn.readUTF();
			        if(test.equals("Login or signing in successful")){//any change here must be changed also in server
			        	controller.setLoginDone();
			        	break;
			        }
					}
					
					
					
					while(true){
					Object object = objIn.readObject();
					if(object instanceof String){
						String string = (String) object;
						System.out.println(string);
					}
					if(object instanceof ActionNotification){
						ActionNotification action = (ActionNotification) object;
						//TODO updateBoard
					}
					if(object instanceof CardDescriber){
						CardDescriber card = (CardDescriber) object;
						//TODO dipende dalla situa
					}
					if(object instanceof PlayerWallet){
						PlayerWallet wallet = (PlayerWallet) object;
						//TODO sempre ok
					}
					if(object instanceof PositionDescriber){
						PositionDescriber positionDescriber = (PositionDescriber) object;
						
					
					}
					}
					
				
				}catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				catch(IOException e1){
					//TODO
				}
			
		
		}

		
}
