package it.polimi.ingsw.GC_26_serverConnections;

import it.polimi.ingsw.GC_26_board.PositionDescriber;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.ActionNotification;
import it.polimi.ingsw.GC_26_serverView.ClientMainServerView;
import it.polimi.ingsw.GC_26_utilities.Message;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembersDescriber;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

public interface ServerConnectionToClient extends Runnable{

	void addViews(ClientMainServerView views);

	void send(PlayerWallet wallet);

	void send(Message message);

	void send(CardDescriber cardDescriber);

	void send(ActionNotification action);

	void send(PositionDescriber position);

	void send(FamilyMembersDescriber familyMembersDescriber);
	
	
	
	
	

}
