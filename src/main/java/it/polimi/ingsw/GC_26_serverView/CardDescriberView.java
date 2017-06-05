package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_cards.CardDescriber;

public class CardDescriberView implements Observer<CardDescriber>{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(CardDescriber cardDescriber) {
		Observer.super.update(cardDescriber);
		System.out.println("I am the view I have been notified by the model with an update CardDescriber");
		System.out.println("Received"  + cardDescriber.getName());
	}

}
