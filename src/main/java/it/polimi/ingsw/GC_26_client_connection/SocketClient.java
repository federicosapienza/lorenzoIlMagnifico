package it.polimi.ingsw.GC_26_client_connection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

public class SocketClient implements ClientConnection{
	 Socket socket= null;
	    ObjectOutputStream objOut =  null;
	    ObjectInputStream objIn  = null;
		
		public SocketClient(Socket socket) throws IOException {
	        this.socket= socket;
			objOut = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
			objOut.flush();
			objIn  = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

	        }

		@Override
		public void run() {
			while (true) {
				try {
			        


					Object object = objIn.readObject();
					if(object instanceof String){
						String string = (String) object;
						System.out.println(string);
					}
					if(object instanceof Action){
						Action action = (Action) object;
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
					
					
					
				
				}catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				}
		}
		

}
