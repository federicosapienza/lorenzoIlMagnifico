package it.polimi.ingsw.GC_26_utilities.familyMembers;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;

public class FamilyMembersSet {
	public void FamilyMembersSet(Player player) {
		FamilyMember orangeMember = new FamilyMember();
		FamilyMember whiteMember = new FamilyMember();
		FamilyMember blackMember = new FamilyMember();
		FamilyMember neutralMember = new FamilyMember();
		orangeMember.colour = Colour.ORANGE;
		whiteMember.colour = Colour.WHITE;
		blackMember.colour = Colour.BLACK;
		neutralMember.colour = Colour.NEUTRAL;
	}
}
