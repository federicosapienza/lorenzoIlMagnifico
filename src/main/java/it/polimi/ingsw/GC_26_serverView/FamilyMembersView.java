package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembersDescriber;

public class FamilyMembersView extends OutputView implements Observer<FamilyMembersDescriber>{

	public FamilyMembersView(ServerConnectionToClient connection) {
		super(connection);
	}

	
	
	public void update(FamilyMembersDescriber familyMembersDescriber) {
		super.getConnection().send(familyMembersDescriber);

	}
}