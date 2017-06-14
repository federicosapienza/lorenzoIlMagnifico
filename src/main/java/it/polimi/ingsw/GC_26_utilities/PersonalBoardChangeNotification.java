package it.polimi.ingsw.GC_26_utilities;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Class that represents the notification message to create whenever a change on a personal board happens. 
 *
 */
public class PersonalBoardChangeNotification extends Message{
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
		this.gameStatus=status;
		this.playerName = playerName;
		this.card = card;
		this.boardTileValues = boardTileValues;
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
