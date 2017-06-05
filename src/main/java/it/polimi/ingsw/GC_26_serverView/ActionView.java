package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_gameLogic.Action;

public class ActionView implements Observer<Action>{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(Action action) {
		Observer.super.update(action);
		System.out.println("I am the view I have been notified by the model with an update CardDescriber");
		
	}
}