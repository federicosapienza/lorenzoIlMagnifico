package it.polimi.ingsw.GC_26_client;

public interface Subject {
	/**
	 * It allows to add an observer to the subject
	 * @param aWatcher Observer to add.
	 */
	void add(Watcher aWatcher);

	/**
	 * It allows to remove an observer that was added before.
	 * @param aWatcher Observer to remove.
	 */
	void remove(Watcher aWatcher);

	/**
	 * It allows to notify the change of the status of the subject to all the observers added.
	 */
	void sendNotification();
}
