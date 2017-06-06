package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;

public class CardDescriberView extends OutputView implements Observer<CardDescriber>{

	public CardDescriberView(ServerConnectionToClient connection) {
		super(connection);
	}

	
	
	public void update(CardDescriber cardDescriber) {
		super.getConnection().send(cardDescriber);

	}

}
