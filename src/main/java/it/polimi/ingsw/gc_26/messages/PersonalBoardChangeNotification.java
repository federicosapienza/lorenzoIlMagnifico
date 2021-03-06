package it.polimi.ingsw.gc_26.messages;

import it.polimi.ingsw.gc_26.messages.describers.CardDescriber;
import it.polimi.ingsw.gc_26.model.game.game_logic.GameStatus;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Class that represents the notification message to send and receive whenever a change on a personal board happens. 
 *
 */
public class PersonalBoardChangeNotification extends Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final GameStatus gameStatus;
	private final String playerName;
	private final CardDescriber card;
	

	private final String boardTileValues; //not null only at the beginning

	/**
	 * Constructor: it sets the status of the game, the player's name, the card, 
	 * the permanent effect and the values of the personal board tile to the ones expressed by the parameters.
	 * @param status It's the status of the game
	 * @param playerName It's the player's name
	 * @param card It's the card
	 * @param boardTileValues It represents the values of the personal bonus tile
	 */
	public PersonalBoardChangeNotification(GameStatus status, String playerName, CardDescriber card, 
			String boardTileValues) {
		if (status == null || playerName == null) {
			throw new NullPointerException("Status is null");
		}
		this.gameStatus=status;
		this.playerName = playerName;
		this.card = card;
		this.boardTileValues = boardTileValues;
	}

	/**
	 * Getter method that returns the status of the name
	 * @return the status of the current game
	 */
	public GameStatus getGameStatus() {
		return gameStatus;
	}
	/**
	 * Getter method that returns the player's name
	 * @return playerName It's the player's name
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Getter method that returns the card 
	 * @return card It's the card
	 */
	public CardDescriber getCard() {
		return card;
	}

	

	/**
	 * Getter method that returns the values of the personal bonus tile
	 * @return boardTileValues It represents the values of the personal bonus tile
	 */
	public String getBoardTileValues() {
		return boardTileValues;
	}

	
	
	
	
	
}
