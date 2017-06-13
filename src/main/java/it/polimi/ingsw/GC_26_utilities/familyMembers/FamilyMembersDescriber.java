package it.polimi.ingsw.GC_26_utilities.familyMembers;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;

public class FamilyMembersDescriber implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String player;
	private final Set<FamilyMember> free;
	private final Map<Colour, Integer> status;

	public FamilyMembersDescriber(FamilyMembers familyMembers) {
		player=familyMembers.getPlayerName();
		free= familyMembers.whatIsFree();
		status=familyMembers.getStatus();
	}
	
	
	public String getPlayerName(){
		return player;
	}
	public Set<FamilyMember> whatIsFree(){
		return free;
	}
	public Map<Colour, Integer> getStatus(){
		return status;
	}
}
