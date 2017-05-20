package it.polimi.ingsw.GC_26_utilities.familyMembers;
import it.polimi.ingsw.GC_26_utilities.dices.*;
import java.util.HashSet;
import java.util.Set;
import it.polimi.ingsw.GC_26_player.Player;

public class FamilyMembers {
	FamilyMember orangeMember;
	FamilyMember whiteMember;
	FamilyMember blackMember;
	FamilyMember neutralMember;
	private int memberValue; /*David: mi serve per getValue(Colour colour):
	per ogni if salvo il valore in memberValue e poi lo ritorno alla fine di tutti 
	gli if. Senza usare memberValue e scrivendo return per ogni if, dà errore */
	private boolean isFree;
	private Set<FamilyMember> freeMembers = new HashSet<FamilyMember>(); /* David: 
	non so se creare qui il set oppure dentro whatIsFree(), prima degli if */
	
	public void FamilyMembersSet(Player player) {
		orangeMember = new FamilyMember(Colour.ORANGE);
		whiteMember = new FamilyMember(Colour.WHITE);
		blackMember = new FamilyMember(Colour.BLACK);
		neutralMember = new FamilyMember(Colour.NEUTRAL);
	}
	public Set whatIsFree() {
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

	public boolean isFree(Colour colour) {
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
		else if (colour != Colour.BLACK && colour != Colour.ORANGE && colour != Colour.WHITE && colour != Colour.NEUTRAL) {
			System.out.println("Insert a valid colour");
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
	}
	
	public void setFreeAll() {
		orangeMember.setFree();
		whiteMember.setFree();
		blackMember.setFree();
		neutralMember.setFree();
	}

}
