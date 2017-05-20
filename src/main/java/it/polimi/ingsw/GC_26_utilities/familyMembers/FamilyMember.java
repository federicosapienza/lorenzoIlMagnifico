package it.polimi.ingsw.GC_26_utilities.familyMembers;

import java.util.Map;

import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.dices.DicesSet;

public class FamilyMember {
	private int value;
	private boolean free;
	private final Colour colour;
	public Map<Colour, Integer> valueTable;
	
	public FamilyMember(Colour colour) {
		this.colour= colour;
	}
	public boolean isFree() {
		//Secondo me isFree non deve essere un metodo di FamilyMember!
		return false;
	}
	public int getValue() {
		if(colour==Colour.NEUTRAL)
			value = 0;
		else{
			DicesSet valueMap = new DicesSet();
			valueTable = valueMap.throwDices();
			value = valueTable.get(colour);
		}
		return value;
	}
}
