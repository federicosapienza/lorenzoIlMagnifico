package it.polimi.ingsw.GC_26_serverConnections;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_serverView.ClientMainServerView;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

public interface ServerConnectionToClient extends Runnable{

	void addViews(ClientMainServerView views);

	void send(PlayerWallet wallet);

	void send(String string);

	void send(CardDescriber cardDescriber);

	void send(Action action);
	
	
	
	
	

}
