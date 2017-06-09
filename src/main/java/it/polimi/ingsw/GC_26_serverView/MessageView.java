package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26_utilities.Message;

public class MessageView extends OutputView implements Observer<Message>{

	public MessageView(ServerConnectionToClient connection) {
		super(connection);
	}

	
	
	public void update(Message message) {
		super.getConnection().send(message);

		

	
	}
	

}
