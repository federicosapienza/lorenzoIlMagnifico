package it.polimi.ingsw.GC_26_serverConnections;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import it.polimi.ingsw.GC_26_board.PositionDescriber;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.ActionNotification;
import it.polimi.ingsw.GC_26_serverView.ClientMainServerView;
import it.polimi.ingsw.GC_26_serverView.Server;
import it.polimi.ingsw.GC_26_utilities.Message;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembersDescriber;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;


public class ServerSocketToClient  implements ServerConnectionToClient{
		private final Socket socket;
		private Server server;
		private ObjectOutputStream objOut =  null;
		private  ObjectInputStream objIn  = null;
		private ClientMainServerView views= null;

		public ServerSocketToClient(Socket socket, Server server) throws IOException {
			this.socket=socket;
			this.server= server;
			objOut = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
			objOut.flush();
			System.out.println("arrivi 11111qui");
	        objIn  = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
		}
		
		@Override
		public void addViews(ClientMainServerView views) {
			this.views= views;
		}
		
		
		
		@Override
		public void run() {
			try{
				System.out.println("vai");
				objOut.writeUTF("Welcome!");
				objOut.flush();
				System.out.println("qui");
				Boolean loginDone= false;
				String username=null;
				String password=null;
				while(!loginDone){
					objOut.writeUTF("Insert username and password");
					objOut.flush();
					username = objIn.readUTF();
					password = objIn.readUTF();
					loginDone= server.doLogin(username, password);
					if(!loginDone){
						objOut.writeUTF("Password wrong! Or perhaps you are trying to sign in with an already used username");
					}
					else
						objOut.writeUTF("Login or signing in successful");
					objOut.flush();
				}
				server.addClient(this, username);
				while(true){
					
					//reading objects
					Object object = objIn.readObject();
					if(object instanceof String){
						String string = (String) object;
						views.getStringInputView().notifyNewString(string);;
						
					
						
					}
					if(object instanceof Action){
						Action action = (Action) object;
						views.getActionInputView().notifyNewAction(action);
				} 
					
						
						}
						// closes the scanner
					//	socketIn.close();
						// closes the printWriter
				//		socketOut.close();
				
			
			// closes the socket
			//	socket.close();
			} 
			catch (IOException e) {
			System.err.println(e.getMessage());
			//TODO fare
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		
		
		
		private synchronized void sendMethod(Object object) {
			try {
				objOut.writeObject(object);
				objOut.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		@Override
		public void send(PlayerWallet wallet) {
			sendMethod(wallet);
		}
		@Override
		public void send(Message message) {
			sendMethod(message);
			
		}
		@Override
		public void send(CardDescriber cardDescriber) {
			sendMethod(cardDescriber);
			
		}
		@Override
		public void send(ActionNotification action) {
			sendMethod(action);
			
		}

		@Override
		public void send(PositionDescriber position) {
			sendMethod(position);
		}

		@Override
		public void send(FamilyMembersDescriber familyMembersDescriber) {
			sendMethod(familyMembersDescriber);
			
		}
		
			
		

}
