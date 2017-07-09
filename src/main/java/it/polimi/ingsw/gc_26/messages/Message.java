package it.polimi.ingsw.gc_26.messages;

import java.io.Serializable;

import it.polimi.ingsw.gc_26.utilities.observer.Observable;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Abstract class that represents the messages to send and receive through the connection
 *
 */
public abstract class Message extends Observable<Message> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
