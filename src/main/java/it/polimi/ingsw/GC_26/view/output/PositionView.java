package it.polimi.ingsw.GC_26.view.output;

import it.polimi.ingsw.GC_26.messages.describers.PositionDescriber;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26.utilities.observer.Observer;

public class PositionView extends OutputView implements Observer<PositionDescriber>{

	public PositionView(ServerConnectionToClient connection) {
		super(connection);
	}
	
	@Override
	public void update(PositionDescriber position) {
		super.getConnection().send(position);

	}
	
}
