package it.polimi.ingsw.GC_26_gameLogic;



import java.io.Serializable;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;

/* position stands for:
 *  floor for towers, specifier of poisiton for production , harvest, market. Useless for council Palace.
 */

public class Action implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String playerName;
	private final BoardZone zone; 
	private final int position;
	private final Colour familyMemberColour;  //can be null only in case of secondary action
	private final int servantsUsed;
	
	public Action(String playerName, BoardZone zone, int position, Colour familyMemberColour, int servantsUsed) {
		this.playerName= playerName;
		this.zone=zone;
		this.position=position;
		this.familyMemberColour = familyMemberColour;
		this.servantsUsed= servantsUsed;
	} 
	
	public String getPlayerName() {
		return playerName;
	}

	public Colour getFamilyMemberColour() {
		return familyMemberColour;
	}
	public int getServantsUsed() {
		return servantsUsed;
	}
	public BoardZone getZone() {
		return zone;
	}
	
	public int getPosition(){
		return position;
	}
	
	@Override
	public String toString() {
		return familyMemberColour + " of "+ playerName +" in "+ zone.getStringDescriber()+", position "+ position;
	}
}
