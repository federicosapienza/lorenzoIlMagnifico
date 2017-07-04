package it.polimi.ingsw.GC_26.view;

import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26.utilities.observer.Observer;

public class CardDescriberView extends OutputView implements Observer<CardDescriber>{

	public CardDescriberView(ServerConnectionToClient connection) {
		super(connection);
	}

	
	
	public void update(CardDescriber cardDescriber) {
		super.getConnection().send(cardDescriber);
	}

}
