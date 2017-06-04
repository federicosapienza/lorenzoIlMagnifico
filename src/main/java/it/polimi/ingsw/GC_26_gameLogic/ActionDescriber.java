package it.polimi.ingsw.GC_26_gameLogic;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;

public class ActionDescriber extends Action{
	String  playerName;

	public ActionDescriber(BoardZone zone, int position, Colour familyMemberColour, int servantsUsed, String playerName) {
		super(zone, position, familyMemberColour, servantsUsed);
		this.playerName=playerName;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	

}
