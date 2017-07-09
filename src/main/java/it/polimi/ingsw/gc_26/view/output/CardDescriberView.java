package it.polimi.ingsw.gc_26.view.output;

import it.polimi.ingsw.gc_26.messages.describers.CardDescriber;
import it.polimi.ingsw.gc_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.gc_26.utilities.observer.Observer;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that represents the view for the card describers
 */
public class CardDescriberView extends OutputView implements Observer<CardDescriber>{

	/**
	 * Constructor: it creates a CardDescriberView based on the ServerConnectionToClient contained in the parameter
	 * @param connection It's the ServerConnectionToClient which this CardDescriberView is based on
	 */
	public CardDescriberView(ServerConnectionToClient connection) {
		super(connection);
	}

	/**
	 * Method called to send the CardDescriber contained in the parameter on the ServerConnectionToClient which this 
	 * CardDescriberView is based on
	 */
	@Override
	public void update(CardDescriber cardDescriber) {
		super.getConnection().send(cardDescriber);
	}

}
