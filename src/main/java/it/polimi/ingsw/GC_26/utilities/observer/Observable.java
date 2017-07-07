package it.polimi.ingsw.GC_26.utilities.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This abstract class represents everything observed by the observers
 *
 */
public abstract class Observable<C> {
	private int countObservers;

	private List<Observer<C>> observers;

	/**
	 * Constructor: it creates a new list of observers
	 */
	public Observable() {

		observers = new ArrayList<Observer<C>>();
	}

	/**
	 * Method called to add the observer contained in the parameter to the list of observers that are observing the object of this class
	 * @param o the observer to add to the list of observers that are observing the object this class
	 */
	public void registerObserver(Observer<C> o) {
		observers.add(o);
		countObservers++;

	}

	/**
	 * Method called to remove the observer contained in the parameter from the list of observers that are observing the object of this object
	 * @param o the observer to remove from the list of observers that are observing the object of this class
	 */
	public void unregisterObserver(Observer<C> o) {
		this.observers.remove(o);
		countObservers--;
	}

	/**
	 * Method called to notify the observers of the object of this class about a change happened to it
	 * @param c It's the change happened to the object of this class
	 */
	public void notifyObservers(C c) {
		for (Observer<C> o : this.observers) {
			o.update(c);
		}

	}

	/**
	 * Method that returns the number of observers associated to the object of this class 
	 * @return the number of observers associated to the object of this class 
	 */
	public int getCountObservers() {
		return countObservers;
	}
}
