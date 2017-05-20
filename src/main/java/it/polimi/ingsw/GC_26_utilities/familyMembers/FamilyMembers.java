package it.polimi.ingsw.GC_26_utilities.familyMembers;
import it.polimi.ingsw.GC_26_utilities.dices.*;
import java.util.Set;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.dices.Dices;
import it.polimi.ingsw.GC_26_utilities.dices.Dice;


public class FamilyMembers {
	FamilyMember orangeMember;
	FamilyMember whiteMember;
	FamilyMember blackMember;
	FamilyMember neutralMember;
	public void FamilyMembersSet(Player player) {
		orangeMember = new FamilyMember(Colour.ORANGE);
		whiteMember = new FamilyMember(Colour.WHITE);
		blackMember = new FamilyMember(Colour.BLACK);
		neutralMember = new FamilyMember(Colour.NEUTRAL);
	}
	public Set whatIsFree() {
		//TODO
	}

	public boolean isFree(Colour colour) {
		boolean isFree = false;
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
		else if (colour != Colour.BLACK && colour != Colour.ORANGE && colour != Colour.WHITE && colour != Colour.NEUTRAL) {
			System.out.println("Insert a valid colour");
		}
		return isFree;
	}
	
	public void setValues(){
		orangeMember.getValue();
        blackMember.getValue();
        whiteMember.getValue();
        neutralMember.getValue();
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
	}
	
	public void setFreeAll() {
		orangeMember.setFree();
		whiteMember.setFree();
		blackMember.setFree();
		neutralMember.setFree();
	}

}
