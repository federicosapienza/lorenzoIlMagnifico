package it.polimi.ingsw.GC_26_actions;



import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

/* position stands for:
 *  floor for towers, specifier for production , harvest, market. useless for council Palace.
 */

public class Action {
	private BoardZone zone; 
	private int position;
	private Colour familyMemberColour;
	private ResourcesOrPoints servantsUsed;
	
	public Action(BoardZone zone, int position, Colour familyMemberColour, ResourcesOrPoints  servantsUsed) {
		this.zone=zone;
		this.familyMemberColour = familyMemberColour;
		this.servantsUsed= servantsUsed;
	} 

	public Colour getFamilyMemberColour() {
		return familyMemberColour;
	}
	public ResourcesOrPoints getServantsUsed() {
		return servantsUsed;
	}
	public BoardZone getZone() {
		return zone;
	}
	
	public int getPosition(){
		return position;
	}
}
