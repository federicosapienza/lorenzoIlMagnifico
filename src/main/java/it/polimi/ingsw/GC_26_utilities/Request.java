package it.polimi.ingsw.GC_26_utilities;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

public class Request extends Message{

	
	//sent unicast to the Player
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final PlayerStatus status;
	private final String Message;
	private final CardDescriber card;
	
	public Request(PlayerStatus status, String message, CardDescriber card) {
		this.status = status;
		Message = message;
		this.card = card;
	}

	public PlayerStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return Message;
	}

	public CardDescriber getCard() {
		return card;
	}
	
	
	
	
}
