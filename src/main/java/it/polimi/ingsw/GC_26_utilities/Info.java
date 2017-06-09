package it.polimi.ingsw.GC_26_utilities;

import it.polimi.ingsw.GC_26_gameLogic.GameStatus;

public class Info extends Message{
	private final GameStatus status;
	private final String playerReferred; //can be null
	private final String message;
	
	
	public Info(GameStatus status, String playerReferred, String message) {
		this.status = status;
		this.playerReferred = playerReferred;
		this.message = message;
	}


	public GameStatus getGameStatus() {
		return status;
	}


	public String getPlayerReferred() {
		return playerReferred;
	}


	public String getMessage() {
		return message;
	}
	
	
	
}
