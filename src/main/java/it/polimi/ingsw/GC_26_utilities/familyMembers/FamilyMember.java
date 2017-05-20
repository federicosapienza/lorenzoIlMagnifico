package it.polimi.ingsw.GC_26_utilities.familyMembers;
import it.polimi.ingsw.GC_26_utilities.dices.*;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
public class FamilyMember {
	private int value;
	private boolean free;
	private Colour colour;
	Dices dices = new Dices();
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
	
	public void setValue(Colour colour) {
		if (colour == Colour.NEUTRAL) {
			this.value = 0;
		}
		else {
			this.value = dices.readDice(colour); 
		}
		
	}
	public int getValue() {
		this.setValue(colour);
		return this.value;
	}
	public void setUsed() {
		this.free = false;
	}
}
