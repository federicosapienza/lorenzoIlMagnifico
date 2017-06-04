package it.polimi.ingsw.GC_26_client;

/**
 * Interfaccia utile a implementare il pattern Observer. Rappresenta
 * l'osservatore di un subject.
 * Interface made because it can be useful to implement the Observer pattern.
 * It represents the observer of a subject.
 * 
 */
public interface Watcher {
	/**
	 * Metodo che viene eseguito non appena viene rilevato un cambiamento nello
	 * stato del subject osservato.
	 * Method called every time a change in the status of the subject is detected.
	 */
	void update();
}
