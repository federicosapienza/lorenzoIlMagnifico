package it.polimi.ingsw.GC_26.server;

import java.util.ArrayList;
import java.util.List;


public abstract class Observable<C> {
	private int countObservers;

	private List<Observer<C>> observers;

	public Observable() {

		observers = new ArrayList<Observer<C>>();
	}

	public void registerObserver(Observer<C> o) {
		observers.add(o);
		countObservers++;

	}

	public void unregisterObserver(Observer<C> o) {
		this.observers.remove(o);
		countObservers--;
	}



	public void notifyObservers(C c) {
		for (Observer<C> o : this.observers) {
			o.update(c);
		}

	}
	
	
	public int getCountObservers() {
		return countObservers;
	}
}
