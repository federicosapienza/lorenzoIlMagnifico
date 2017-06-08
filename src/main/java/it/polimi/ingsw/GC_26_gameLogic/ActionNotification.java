package it.polimi.ingsw.GC_26_gameLogic;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;

public class ActionNotification extends Action{
	private final String playerName;

	public ActionNotification(String playerName , BoardZone zone, int position, Colour familyMemberColour, int servantsUsed) {
		super(zone, position, familyMemberColour, servantsUsed);
		this.playerName=playerName;
		}
	
	public String getPlayerName() {
		return playerName;
	}

}
