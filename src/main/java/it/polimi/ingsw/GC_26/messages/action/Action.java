package it.polimi.ingsw.GC_26.messages.action;



import java.io.Serializable;

import it.polimi.ingsw.GC_26.model.game.gameComponents.board.BoardZone;
import it.polimi.ingsw.GC_26.model.game.gameComponents.dices.Colour;


/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * It's the class that represents the action: every action implies the setting of a family member 
 * in a zone of the board and its value could be increased spending one or more servants.
 */
public class Action implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private final BoardZone zone; 
	private final int position;
	private final Colour familyMemberColour;  //can be null only in case of secondary action
	private final int servantsUsed;
	
	/**
	 * Constructor of an action: it sets the zone, the position, the colour of the family member and the number of the used
	 * servants to the ones contained in the parameters. 
	 * @param zone It's the zone of the board where the player wants to set his family member 
	 * @param position It specifies the position of the zone. In particular, it represents:
	 * the floor for towers 
	 * specifier of position for production, harvest, market. 
	 * @param familyMemberColour It's the colour of the family member
	 * @param servantsUsed It's the number of the servants used to increase the value of the action
	 */
	public Action(BoardZone zone, int position, Colour familyMemberColour, int servantsUsed) {
	
		this.zone=zone;
		this.position=position;
		this.familyMemberColour = familyMemberColour;
		this.servantsUsed= servantsUsed;
	} 
	
	/**
	 * Method that returns the colour of the family member that has been used for this action
	 * @return familyMemberColour It's the colour of the family member that has been used for this action
	 */
	public Colour getFamilyMemberColour() {
		return familyMemberColour;
	}
	
	/**
	 * Method that returns the number of the servants used to increase the value of this action
	 * @return servantsUsed It's the number of the servants used to increase the value of this action
	 */
	public int getServantsUsed() {
		return servantsUsed;
	}
	
	/**
	 * Method that returns the zone of the board involved in this action
	 * @return zone It's the zone of the board involved in this action
	 */
	public BoardZone getZone() {
		return zone;
	}
	
	/**
	 * Method that returns the position of the zone of the board if the action implies the use of a zone that has multiple positions
	 * @return position It's the position of the zone of the board
	 */
	public int getPosition(){
		return position;
	}
	
	/**
	 * Method that returns a textual representation of the action.
	 */
	@Override
	public String toString() {
		if(familyMemberColour!=null){
		return familyMemberColour+ " in "+ zone.getStringDescriber()+", position "+ position;
		}
		else return "goes to " + zone.getStringDescriber()+", position "+ position + " with a second action";
	}
}
