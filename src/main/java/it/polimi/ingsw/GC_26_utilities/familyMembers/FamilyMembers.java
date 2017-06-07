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
	
	
	public FamilyMembers(Player player) {
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
		//reduce of permanent effect
		int colouredChange = player.getPermanentModifiers().getColouredMemberChange();
		int neutralChange = player.getPermanentModifiers().getneutralMemberChange();
		
		orangeMember.setValue(dices.readDice(Colour.ORANGE)+colouredChange);
        blackMember.setValue(dices.readDice(Colour.BLACK)+colouredChange);
        whiteMember.setValue(dices.readDice(Colour.WHITE)+colouredChange);
        neutralMember.setValue(GameParameters.getDefaultNeutralValue()+neutralChange);
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
	
	
}
