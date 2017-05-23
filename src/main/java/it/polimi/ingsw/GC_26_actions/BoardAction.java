package it.polimi.ingsw.GC_26_actions;


import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

/* Zone stands for:
 * 1- production Tower
 * 2.
 * 3.
 * 4.
 * 5.market
 * 6.harvest
 * 7.production
 * 8.Council palace
 * 
 * position:
 * is floor for towers, specifier for production , harvest, market. useless for council Palace.
 */

public class BoardAction {
	private int zone; 
	private int position;
	private Colour familyMemberColour;
	private ResourcesOrPoints servantsUsed;
	
	
	//la view all inizio del gioco dovrà avere un riferimento a player , un' identità di se stesso, se no non ne usciamo piu!
	public BoardAction(Player player, int zone, int position, Colour familyMemberColour, ResourcesOrPoints  servantsUsed) {
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
	public int getZone() {
		return zone;
	}
	
	public int getPosition(){
		return position;
	}
}
