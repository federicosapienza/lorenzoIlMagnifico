package it.polimi.ingsw.gc_26.view.output;

import it.polimi.ingsw.gc_26.messages.describers.FamilyMembersDescriber;
import it.polimi.ingsw.gc_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.gc_26.utilities.observer.Observer;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that represents the view for the Family Members describers
 */
public class FamilyMembersView extends OutputView implements Observer<FamilyMembersDescriber>{

	/**
	 * Constructor: it creates a FamilyMembersView based on the ServerConnectionToClient contained in the parameter
	 * @param connection It's the ServerConnectionToClient which this FamilyMembersView is based on
	 */
	public FamilyMembersView(ServerConnectionToClient connection) {
		super(connection);
	}

	/**
	 * Method called to send the FamilyMembersDescriber contained in the parameter on the ServerConnectionToClient 
	 * which this FamilyMembersView is based on
	 */
	@Override
	public void update(FamilyMembersDescriber familyMembersDescriber) {
		super.getConnection().send(familyMembersDescriber);

	}
}