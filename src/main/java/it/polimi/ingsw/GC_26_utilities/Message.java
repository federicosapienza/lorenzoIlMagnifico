package it.polimi.ingsw.GC_26_utilities;

import java.io.Serializable;

import it.polimi.ingsw.GC_26_serverView.Observable;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Abstract class representing a message
 *
 */
public abstract class Message extends Observable<Message> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
