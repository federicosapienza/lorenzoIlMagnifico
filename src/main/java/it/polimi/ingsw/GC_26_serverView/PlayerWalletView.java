package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesDescriber;

public class PlayerWalletView implements Observer<ResourcesDescriber>{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(ResourcesDescriber wallet) {
		Observer.super.update(wallet);
		System.out.println("I am the view I have been notified by the model with an update CardDescriber");
		System.out.println("Received"  + wallet.getPlayerName());
	}
	
}
