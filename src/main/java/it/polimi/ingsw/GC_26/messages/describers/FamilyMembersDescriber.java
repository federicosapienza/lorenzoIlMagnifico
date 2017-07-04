package it.polimi.ingsw.GC_26.messages.describers;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.GC_26.model.game.gameComponents.dices.Colour;
import it.polimi.ingsw.GC_26.model.game.gameComponents.familyMembers.FamilyMembers;

public class FamilyMembersDescriber implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String player;
	private final Set<Colour> free;
	private final Map<Colour, Integer> status;

	public FamilyMembersDescriber(FamilyMembers familyMembers) {
		player=familyMembers.getPlayerName();
		free= familyMembers.whatIsFree();
		status=familyMembers.getStatus();
	}
	
	
	public String getPlayerName(){
		return player;
	}
	public Set<Colour> whatIsFree(){
		return free;
	}
	public Map<Colour, Integer> getStatus(){
		return status;
	}
}
