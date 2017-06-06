package it.polimi.ingsw.GC_26_serverView;


import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

public class PlayerWalletView extends OutputView implements Observer<PlayerWallet>{

	public PlayerWalletView(ServerConnectionToClient connection) {
		super(connection);
	}

	
	public void update(PlayerWallet wallet) {
		super.getConnection().send(wallet);
		
	}



	
	
}
