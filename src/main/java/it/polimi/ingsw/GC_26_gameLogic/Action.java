package it.polimi.ingsw.GC_26_gameLogic;



import java.io.Serializable;
import java.util.PrimitiveIterator.OfDouble;

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
	private final BoardZone zone; 
	private final int position;
	private final Colour familyMemberColour;  //can be null only in case of secondary action
	private final int servantsUsed;
	
	public Action(BoardZone zone, int position, Colour familyMemberColour, int servantsUsed) {
		this.zone=zone;
		this.position=position;
		this.familyMemberColour = familyMemberColour;
		this.servantsUsed= servantsUsed;
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
		return familyMemberColour+ " in "+ zone.getStringDescriber()+", position "+ position;
	}
}
