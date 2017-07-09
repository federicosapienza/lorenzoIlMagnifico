package it.polimi.ingsw.GC_26.messages;

import it.polimi.ingsw.GC_26.model.game.game_logic.GameStatus;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Class that contains the info about the status of the game, the referred player and the message
 *
 */
public class Info extends Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final GameStatus status;
	private final String playerReferred; //can be null
	private final String message;
	
	/**
	 * Constructor of info: it sets the status of the game, the referred player and the message to the ones contained 
	 * in the parameters.
	 * @param status It's the current status of the game
	 * @param playerReferred It's the referred player
	 * @param message It's the message
	 */
	public Info(GameStatus status, String playerReferred, String message) {
		this.status = status;
		this.playerReferred = playerReferred;
		this.message = message;
	}

	/**
	 * Getter method that returns the status of the game
	 * @return status It's the status of the game
	 */
	public GameStatus getGameStatus() {
		return status;
	}

	/**
	 * Getter method that returns the referred player
	 * @return playerReferred It's the referred player
	 */
	public String getPlayerReferred() {
		return playerReferred;
	}

	/**
	 * Getter method that returns the message
	 * @return message It's the message
	 */
	public String getMessage() {
		return message;
	}
	
	
	
}
