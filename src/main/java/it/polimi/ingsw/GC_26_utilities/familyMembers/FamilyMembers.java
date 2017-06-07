package it.polimi.ingsw.GC_26_utilities.familyMembers;
import it.polimi.ingsw.GC_26_utilities.dices.*;
import java.util.HashSet;
import java.util.Set;

import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that represents the set of family members that every player owns.
 * Every set of family members includes:
 * an orange family member, a white family member, a black family member and a neutral family member.
 * The values of the coloured family members correspond with the value of the dices of the same colour.
 * 
 */
public class FamilyMembers {
	
	//it's the orange family member.
	private FamilyMember orangeMember;
	
	//it's the white family member.
	private FamilyMember whiteMember;
	
	//it's the black family member.
	private FamilyMember blackMember;
	
	//it's the neutral family member.
	private FamilyMember neutralMember;
	
	//it's the player that owns these family members.
	private Player player;
	
	/**
	 * Constructor that creates the set of family members of a player.
	 * @param player It's the player that owns these family members.
	 */
	public FamilyMembers(Player player) {
		this.player= player;
		orangeMember = new FamilyMember(Colour.ORANGE, player);
		whiteMember = new FamilyMember(Colour.WHITE, player);
		blackMember = new FamilyMember(Colour.BLACK, player);
		neutralMember = new FamilyMember(Colour.NEUTRAL, player);
	}
	
	/**
	 * Method that returns the player who owns these family members.
	 * @return player It's the player who owns these family members.
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Method that returns the family member for the desired colour.
	 * @param colour It's the desired colour of the family member to get.
	 * @return blackMember if the desired colour is black.
	 * @return whiteMember if the desired colour is white.
	 * @return orangeMember if the desired colour is orange.
	 * @return neutralMember if the desired colour is neutral.
	 */
	public FamilyMember getfamilyMember(Colour colour){
		if (colour == Colour.BLACK) {
			return blackMember;
		}
		else if (colour == Colour.WHITE) {
			return whiteMember;
		}
		else if (colour == Colour.ORANGE) {
			return orangeMember;
		}
		else if (colour == Colour.NEUTRAL) {
			return neutralMember;
		}
		else 
			throw  new IllegalArgumentException();
		
	}
	
	/**
	 * Method that sets the value for every family member to the correct value of the corresponding dice.
	 * If the player is under the effect of a malus, 
	 * his family members will have a value subtracted of an amount equal to malus.
	 * @param dices It's the set of dices that assigns the corresponding value for every family member. 
	 */
	public void setValues(Dices dices){
		//reduce of permanent effect
		int colouredChange = player.getPermanentModifiers().getColouredMemberChange();
		int neutralChange = player.getPermanentModifiers().getneutralMemberChange();
		
		orangeMember.setValue(dices.readDice(Colour.ORANGE)+colouredChange);
        blackMember.setValue(dices.readDice(Colour.BLACK)+colouredChange);
        whiteMember.setValue(dices.readDice(Colour.WHITE)+colouredChange);
        neutralMember.setValue(GameParameters.getDefaultNeutralValue()+neutralChange);
	}
	
	/**
	 * Method that returns the set of the family members that are actually free.
	 * @return freeMembers It's the set of the family members that are actually free.
	 */
	public Set<FamilyMember> whatIsFree() {  
		Set<FamilyMember> freeMembers = new HashSet<FamilyMember>();
		if (blackMember.isFree() == true) {
			freeMembers.add(blackMember);
		}
		if (orangeMember.isFree() == true) {
			freeMembers.add(orangeMember);
		}
		if (whiteMember.isFree() == true) {
			freeMembers.add(whiteMember);
		}
		if (neutralMember.isFree() == true) {
			freeMembers.add(neutralMember);
		}
		return freeMembers;
	}
	
	/**
	 * Method that sets all the family members to free, 
	 * i.e. the attributes "free" of the family members will be set to true.  
	 */
	public void setFreeAll() {
		orangeMember.setFree();
		whiteMember.setFree();
		blackMember.setFree();
		neutralMember.setFree();
	}
	
	
}
