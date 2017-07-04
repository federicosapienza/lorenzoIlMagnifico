package it.polimi.ingsw.GC_26.view;


import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.PlayerWallet;
import it.polimi.ingsw.GC_26.server.Observer;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;

public class PlayerWalletView extends OutputView implements Observer<PlayerWallet>{

	public PlayerWalletView(ServerConnectionToClient connection) {
		super(connection);
	}

	
	public void update(PlayerWallet wallet) {
		super.getConnection().send(wallet);
		
	}



	
	
}
