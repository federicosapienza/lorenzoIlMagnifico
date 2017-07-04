package it.polimi.ingsw.GC_26.view;

import it.polimi.ingsw.GC_26.model.describers.CardDescriber;
import it.polimi.ingsw.GC_26.server.Observer;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;

public class CardDescriberView extends OutputView implements Observer<CardDescriber>{

	public CardDescriberView(ServerConnectionToClient connection) {
		super(connection);
	}

	
	
	public void update(CardDescriber cardDescriber) {
		super.getConnection().send(cardDescriber);
	}

}
