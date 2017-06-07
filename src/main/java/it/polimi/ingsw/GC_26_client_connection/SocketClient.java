package it.polimi.ingsw.GC_26_client_connection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.GC_26_board.PositionDescriber;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_client.ClientoutputController;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

public class SocketClient implements ClientConnection{
	 Socket socket= null;
	    ObjectOutputStream objOut =  null;
	    ObjectInputStream objIn  = null;
		
		public SocketClient(int port, String ip) throws IOException {
	        this.socket= new Socket(ip, port);
			objOut = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
			objOut.flush();
			objIn  = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

	        }

		@Override
		public void run(ClientoutputController controller) {
			 
				try {
					while (true){
			        String test = objIn.readUTF();
			        if(test.equals("Login or signing in successful"))  //any change here must be changed also in server
			        	break;
			        //TODO chiedere username e password
					}
					
					
					
					while(true){
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
