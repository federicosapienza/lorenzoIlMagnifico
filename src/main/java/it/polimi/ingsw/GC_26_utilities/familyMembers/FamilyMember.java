package it.polimi.ingsw.GC_26_utilities.familyMembers;
import it.polimi.ingsw.GC_26_utilities.dices.*;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
public class FamilyMember {
	private int value;
	private boolean free;
	private Colour colour;
	Dices dices;
	public FamilyMember(Colour colour) {
		this.colour = colour;
	}
	public boolean isFree() {
		if (this.free == true) {
			return true;
		}
		else{
			return false;
		}
	}
	
	public Colour getColour() {
		return colour;
	}
	
	public void setValue(int value) {
		this.value =value;
	}
	public int getValue() {
		return this.value;
	}
	public void setUsed() {
		this.free = false;
	}
	
	public void setFree() {
		this.free = true;
	}
}
