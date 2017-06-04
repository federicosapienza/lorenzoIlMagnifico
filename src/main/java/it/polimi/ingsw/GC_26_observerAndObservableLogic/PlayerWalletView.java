package it.polimi.ingsw.GC_26_observerAndObservableLogic;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

public class PlayerWalletView implements Observer<PlayerWallet>{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(PlayerWallet wallet) {
		Observer.super.update(wallet);
		System.out.println("I am the view I have been notified by the model with an update CardDescriber");
		System.out.println("Received"  + wallet.getPlayerName());
	}
	
}
