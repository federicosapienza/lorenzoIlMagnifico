package it.polimi.ingsw.gc_26.server.connections;

import it.polimi.ingsw.gc_26.messages.Message;
import it.polimi.ingsw.gc_26.messages.action.ActionNotification;
import it.polimi.ingsw.gc_26.messages.describers.CardDescriber;
import it.polimi.ingsw.gc_26.messages.describers.FamilyMembersDescriber;
import it.polimi.ingsw.gc_26.messages.describers.PositionDescriber;
import it.polimi.ingsw.gc_26.model.game.game_components.resourcesAndPoints.PlayerWallet;
import it.polimi.ingsw.gc_26.view.ClientMainServerView;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This interface represents the server connection to the client
 *
 */
public interface ServerConnectionToClient extends Runnable{

	void addViews(ClientMainServerView views);

	void send(PlayerWallet wallet);

	void send(Message message);

	void send(CardDescriber cardDescriber);

	void send(ActionNotification action);

	void send(PositionDescriber position);

	void send(FamilyMembersDescriber familyMembersDescriber);
	
	
	
	
	

}
