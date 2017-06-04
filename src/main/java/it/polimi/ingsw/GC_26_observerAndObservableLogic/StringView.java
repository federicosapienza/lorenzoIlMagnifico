package it.polimi.ingsw.GC_26_observerAndObservableLogic;


public class StringView implements Observer<String>{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(String string) {
		Observer.super.update(string);
		System.out.println("I am the view I have been notified by the model with an update String");
		System.out.println("Received" + string);

	
	}
	

}
