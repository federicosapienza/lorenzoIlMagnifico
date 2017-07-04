package it.polimi.ingsw.GC_26.view;

import it.polimi.ingsw.GC_26.model.game.gameComponents.familyMembers.FamilyMembersDescriber;
import it.polimi.ingsw.GC_26.server.Observer;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;

public class FamilyMembersView extends OutputView implements Observer<FamilyMembersDescriber>{

	public FamilyMembersView(ServerConnectionToClient connection) {
		super(connection);
	}

	
	
	public void update(FamilyMembersDescriber familyMembersDescriber) {
		super.getConnection().send(familyMembersDescriber);

	}
}