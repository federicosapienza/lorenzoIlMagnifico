package it.polimi.ingsw.GC_26.model.game.game_components.cards.effects;

import it.polimi.ingsw.GC_26.model.player.Player;
/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the effect that changes the value of a family member.
 * negative values are used to decrease the value, positive ones to increase it
 * 
 */


public class ChangeFamilyMembersValue implements Effect{
	

	private final int colouredMemberChange;
	private final int neutralMemberChange;

	/**
	 * Constructor: it creates the effect with the changes to apply onto the coloured and neutral family members, as expressed in the parameters
	 * @param colouredMemberChange It's the value to add to the actual value of the coloured family members
	 * @param neutralMemberChange It's the value to add to the actual value of the neutral family member
	 */
	public ChangeFamilyMembersValue(int colouredMemberChange, int neutralMemberChange) {
		this.colouredMemberChange = colouredMemberChange;
		this.neutralMemberChange= neutralMemberChange;
	}
	
	/**
	 * Method called to apply the effect to the player who is involved in the effect
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setColouredMembersChange(colouredMemberChange);
		player.getPermanentModifiers().setNeutralMembersChange(neutralMemberChange);
		player.getFamilyMembers().changeValues();
		
	}
	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		if(neutralMemberChange==0)
		return " All coloured Family Members of the player receive a " +colouredMemberChange+ "  change of their value";
		else return " Neutral Family Members of the player receive a " +neutralMemberChange+ "  change of their value";
	}
	
	
}
