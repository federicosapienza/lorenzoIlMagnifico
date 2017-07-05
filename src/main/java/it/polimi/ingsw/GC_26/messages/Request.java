package it.polimi.ingsw.GC_26.messages;

import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.player.PlayerStatus;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the requests that have to be created in order to change the status of the players 
 *
 */
public class Request extends Message{

	
	//sent unicast to the Player
	private static final long serialVersionUID = 1L;
	private final PlayerStatus status;
	private final String Message;
	private final CardDescriber card;
	
	/**
	 * Constructor: it creates a request with the status, message and Card describer contained in the parameters
	 * @param status It's the new status of the player to set after the request 
	 * @param message It's the message that notifies about the request
	 * @param card It's the eventual card describer of the card contained in the request 
	 */
	public Request(PlayerStatus status, String message, CardDescriber card) {
		this.status = status;
		Message = message;
		this.card = card;
	}

	/**
	 * Method that returns the status of the player which is required by the request
	 * @return the status of the player which is required by the request
	 */
	public PlayerStatus getStatus() {
		return status;
	}

	/**
	 * Method that returns the message contained in the request
	 * @return the message contained in the request
	 */
	public String getMessage() {
		return Message;
	}

	/**
	 * Method that returns the CardDescriber contained in the request
	 * @return the CardDescriber contained in the request
	 */
	public CardDescriber getCard() {
		return card;
	}
	
	
	
	
}
