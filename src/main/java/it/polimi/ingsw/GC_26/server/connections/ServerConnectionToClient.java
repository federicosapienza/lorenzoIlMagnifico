package it.polimi.ingsw.GC_26.server.connections;

import it.polimi.ingsw.GC_26.messages.Message;
import it.polimi.ingsw.GC_26.model.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.describers.PositionDescriber;
import it.polimi.ingsw.GC_26.model.game.action.ActionNotification;
import it.polimi.ingsw.GC_26.model.game.gameComponents.familyMembers.FamilyMembersDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.PlayerWallet;
import it.polimi.ingsw.GC_26.view.ClientMainServerView;

public interface ServerConnectionToClient extends Runnable{

	void addViews(ClientMainServerView views);

	void send(PlayerWallet wallet);

	void send(Message message);

	void send(CardDescriber cardDescriber);

	void send(ActionNotification action);

	void send(PositionDescriber position);

	void send(FamilyMembersDescriber familyMembersDescriber);
	
	
	
	
	

}
