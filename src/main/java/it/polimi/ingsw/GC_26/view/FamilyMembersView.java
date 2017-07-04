package it.polimi.ingsw.GC_26.view;

import it.polimi.ingsw.GC_26.messages.describers.FamilyMembersDescriber;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26.utilities.observer.Observer;

public class FamilyMembersView extends OutputView implements Observer<FamilyMembersDescriber>{

	public FamilyMembersView(ServerConnectionToClient connection) {
		super(connection);
	}

	
	@Override
	public void update(FamilyMembersDescriber familyMembersDescriber) {
		super.getConnection().send(familyMembersDescriber);

	}
}