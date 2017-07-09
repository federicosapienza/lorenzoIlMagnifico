package it.polimi.ingsw.gc_26.messages.describers;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.gc_26.model.game.game_components.dices.Colour;
import it.polimi.ingsw.gc_26.model.game.game_components.family_members.FamilyMembers;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the describer of the Family Members
 *
 */
public class FamilyMembersDescriber implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private final String player;
	private final Set<Colour> free;
	private final Map<Colour, Integer> status;

	/**
	 * Constructor: it creates a new FamilyMembersDescriber for the Family Members contained in the parameter
	 * @param familyMembers the Family Members to describe
	 */
	public FamilyMembersDescriber(FamilyMembers familyMembers) {
		player=familyMembers.getPlayerName();
		free= familyMembers.whatIsFree();
		status=familyMembers.getStatus();
	}
	
	/**
	 * Method that returns the name of the player who owns the Family Members
	 * @return
	 */
	public String getPlayerName(){
		return player;
	}
	
	/**
	 * Method that returns the Set of colours of the free Family Members 
	 * @return the Set of colours of the free Family Members 
	 */
	public Set<Colour> whatIsFree(){
		return free;
	}
	
	/**
	 * Method that returns the Map which keeps the record of status of each Family Member
	 * @return the Map which keeps the record of status of each Family Member
	 */
	public Map<Colour, Integer> getStatus(){
		return status;
	}
	
	/**
	 * Method that returns the number of family members used
	 */
	public int size(){
		return status.size();
	}
}
