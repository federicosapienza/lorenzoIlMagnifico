package it.polimi.ingsw.gc_26.view.output;


import it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points.PlayerWallet;
import it.polimi.ingsw.gc_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.gc_26.utilities.observer.Observer;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that represents the view for the PlayerWallet
 */
public class PlayerWalletView extends OutputView implements Observer<PlayerWallet>{

	/**
	 * Constructor: it creates a PlayerWalletView based on the ServerConnectionToClient contained in the parameter
	 * @param connection It's the ServerConnectionToClient which this PlayerWalletView is based on
	 */
	public PlayerWalletView(ServerConnectionToClient connection) {
		super(connection);
	}

	/**
	 * Method called to send the PlayerWallet contained in the parameter on the ServerConnectionToClient 
	 * which this PlayerWalletView is based on
	 */
	@Override
	public void update(PlayerWallet wallet) {
		super.getConnection().send(wallet);
		
	}



	
	
}
