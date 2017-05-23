package it.polimi.ingsw.GC_26_utilities.familyMembers;
import it.polimi.ingsw.GC_26_utilities.dices.*;
import java.util.HashSet;
import java.util.Set;

import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;

public class FamilyMembers {
	private FamilyMember orangeMember;
	private FamilyMember whiteMember;
	private FamilyMember blackMember;
	private FamilyMember neutralMember;
	private Player player;
	
	
	public void FamilyMembersSet(Player player) {
		this.player= player;
		orangeMember = new FamilyMember(Colour.ORANGE, player);
		whiteMember = new FamilyMember(Colour.WHITE, player);
		blackMember = new FamilyMember(Colour.BLACK, player);
		neutralMember = new FamilyMember(Colour.NEUTRAL, player);
	}
	
	public Player getPlayer() {
		return player;
	}
	
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
	
	
	
	public void setValues(Dices dices){
		orangeMember.setValue(dices.readDice(Colour.ORANGE));
        blackMember.setValue(dices.readDice(Colour.BLACK));
        whiteMember.setValue(dices.readDice(Colour.WHITE));
        neutralMember.setValue(GameParameters.getDefaultNeutralValue());
	}
	
	public Set<FamilyMember> whatIsFree() {  //returns the set of the free familyMembers
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
	
	public void setFreeAll() {
		orangeMember.setFree();
		whiteMember.setFree();
		blackMember.setFree();
		neutralMember.setFree();
	}
	
	
	//penso che da qui in poi sia inutile. facciamo il get del singolo FamilyMember e e lavoriamo su di lui
	
	/*public boolean isFree(Colour colour) {
		boolean isFree;
		if (colour == Colour.BLACK) {
			isFree = blackMember.isFree();
		}
		else if (colour == Colour.WHITE) {
			isFree = whiteMember.isFree();
		}
		else if (colour == Colour.ORANGE) {
			isFree = orangeMember.isFree();
		}
		else if (colour == Colour.NEUTRAL) {
			isFree = neutralMember.isFree();
		}
		else {
			throw new IllegalArgumentException();
		}
		return isFree;
	}
	
	
	
	public int getValue(Colour colour){
		 int memberValue;
		if (colour == Colour.BLACK) {
			memberValue = blackMember.getValue();
		}
		else if (colour == Colour.WHITE) {
			memberValue = whiteMember.getValue();
		}
		else if (colour == Colour.ORANGE) {
			memberValue = orangeMember.getValue();
		}
		else if (colour == Colour.NEUTRAL) {
			memberValue = neutralMember.getValue();
		}
		else {
			throw new IllegalArgumentException();
		}
		return memberValue;
	}
	
	public void setUsed(Colour colour) {
		if (colour == Colour.ORANGE) {
			orangeMember.setUsed();
		}
		else if (colour == Colour.BLACK) {
			blackMember.setUsed();
		}
		else if (colour == Colour.WHITE) {
			whiteMember.setUsed();
		}
		else if (colour == Colour.NEUTRAL) {
			neutralMember.setUsed();
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	*/

}
