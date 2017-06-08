package it.polimi.ingsw.GC_26_utilities.familyMembers;

import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;

public interface FamilyMembersDescriber {

	public Player getPlayer();
	public Set<FamilyMember> whatIsFree();
	public Map<Colour, Integer> getStatus();
}
