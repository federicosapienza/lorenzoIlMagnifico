package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;
/*Represent a change in value of a family member;
 * use negative value for decrement, positive for increment
 * 
 */


public class ChangeFamilyMembersValue implements Effect{
	

	private final int colouredMemberChange;
	private final int neutralMemberChange;

	public ChangeFamilyMembersValue(int colouredMemberChange, int neutralMemberChange) {
		this.colouredMemberChange = colouredMemberChange;
		this.neutralMemberChange= neutralMemberChange;
	}
	
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setColouredMembersChange(colouredMemberChange);
		player.getPermanentModifiers().setColouredMembersChange(neutralMemberChange);
		player.getFamilyMembers().changeValues();
		
	}
	
	@Override
	public String toString() {
		if(neutralMemberChange==0)
		return "All coloured Family Members of the player receive a" +colouredMemberChange+ "  increment/reduction of their value";
		else return "Neutral Family Members of the player receive a" +neutralMemberChange+ "  increment/reduction of their value";
	}
	
	
}
