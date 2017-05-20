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
		else {throw new IllegalArgumentException();
		}
		return isFree;
	}
	
	public void setValues(Dices dices){
		orangeMember.setValue(dices.readDice(Colour.ORANGE));
        blackMember.setValue(dices.readDice(Colour.BLACK));
        whiteMember.setValue(dices.readDice(Colour.WHITE));
        neutralMember.setValue(0);
	}
	
	public int getValue(Colour colour){
		if (colour == Colour.BLACK) {
			return blackMember.getValue();
		}
		else if (colour == Colour.WHITE) {
			return whiteMember.getValue();
		}
		else if (colour == Colour.ORANGE) {
			return orangeMember.getValue();
		}
		else if (colour == Colour.NEUTRAL) {
			return neutralMember.getValue();
		}
		else if (colour != Colour.BLACK && colour != Colour.ORANGE && colour != Colour.WHITE && colour != Colour.NEUTRAL) {
			System.out.println("Insert a valid colour");
		}
		else throw new IllegalArgumentException();
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
