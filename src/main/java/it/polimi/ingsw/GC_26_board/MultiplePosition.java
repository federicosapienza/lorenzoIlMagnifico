package it.polimi.ingsw.GC_26_board;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;

public class MultiplePosition {
	private final int valueOfPosition;
	private List<FamilyMember> familyMemberInPosition = new ArrayList<>();
	private boolean positionFree=true;
	private int counter; //to count number of FM in position
	
	public MultiplePosition(int valueOfPosition){
		this.valueOfPosition=valueOfPosition;
	}
	
	public int getValueOfPosition(){
		return valueOfPosition;
	}
	
	public void setFamilyMember(FamilyMember familyMemberInPosition){
		this.familyMemberInPosition.add(familyMemberInPosition);
		positionFree=false;
		setCounter(getCounter() + 1);
	}
	
	//controllo superflue con number e counter
	public FamilyMember GetFamilyMember(int number){
		if(number<=counter){
			return familyMemberInPosition.get(number);
		}
		return null;
	}
	
	public boolean getIsPositionFree(){
		return positionFree;
	}
	
	public void setIsPositionFree(boolean isPositionFree){
		this.positionFree=isPositionFree;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public void clear(){
		familyMemberInPosition.clear();
		setIsPositionFree(true);
		}


}
