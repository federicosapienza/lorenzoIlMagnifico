package it.polimi.ingsw.GC_26_actionHandlers;

import java.util.Observer;

import it.polimi.ingsw.GC_26_gameLogic.GameElements;

public abstract class ActionHandler implements Observer{
	private GameElements gameElements;
	public void setGameElements(GameElements gameElements) {
		this.gameElements= gameElements;
	}
	
	public GameElements getGameElements() {
		return gameElements;
	}

}
