package it.polimi.ingsw.GC_26_utilities;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;

public class PersonalBoardChangeNotification extends Message{
	private final GameStatus gameStatus;
	private final String playerName;
	private final CardDescriber card;
	private final String permanentEffect; //if we want to take note that a player has activated a permanent Effect
	

	private final String boardTileValues; //not null only at the beginning


	public PersonalBoardChangeNotification(GameStatus status, String playerName, CardDescriber card, String permanentEffect,
			String boardTileValues) {
		this.gameStatus=status;
		this.playerName = playerName;
		this.card = card;
		this.permanentEffect = permanentEffect;
		this.boardTileValues = boardTileValues;
	}


	public String getPlayerName() {
		return playerName;
	}


	public CardDescriber getCard() {
		return card;
	}


	public String getPermanentEffect() {
		return permanentEffect;
	}


	public String getBoardTileValues() {
		return boardTileValues;
	}

	
	
	
	
	
}
