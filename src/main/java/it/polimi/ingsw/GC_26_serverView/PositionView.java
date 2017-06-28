package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_board.PositionDescriber;
import it.polimi.ingsw.GC_26_server.Observer;
import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;

public class PositionView extends OutputView implements Observer<PositionDescriber>{

	public PositionView(ServerConnectionToClient connection) {
		super(connection);
	}
	
	
	public void update(PositionDescriber position) {
		super.getConnection().send(position);

	}
	
}
