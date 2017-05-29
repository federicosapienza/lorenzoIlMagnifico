package it.polimi.ingsw.GC_26_actionsHandlers;



import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;

/* position stands for:
 *  floor for towers, specifier for production , harvest, market. useless for council Palace.
 */

public class Action {
	private BoardZone zone; 
	private int position;
	private Colour familyMemberColour;  //can be null only in case of secondary action
	private int servantsUsed;
	
	public Action(BoardZone zone, int position, Colour familyMemberColour, int servantsUsed) {
		this.zone=zone;
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
}
