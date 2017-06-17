package it.polimi.ingsw.GC_26_board;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the multiple position zones characterizing the Harvest, Production and Council Palace zones
 *
 */
public class MultiplePosition {
	private final int valueOfPosition;
	private List<FamilyMember> familyMemberInPosition = new ArrayList<>();
	private boolean positionFree=true;
	
	//Counter is necessary to count the number of family members in the multiple position
	private int counter; 
	private final int actionMalus= GameParameters.getMultiplePositionMalus();
	
	/**
	 * Constructor: it creates a multiple position zone with the minimum required value of the family members (or 
	 * of the action that has to be activated) to occupy it
	 * @param valueOfPosition the minimum required value to occupy the multiple position zone
	 */
	public MultiplePosition(int valueOfPosition){
		this.valueOfPosition=valueOfPosition;
	}
	
	/**
	 * Method that returns the minimum required value to occupy this multiple position zone
	 * @return the minimum required value to occupy this multiple position zone
	 */
	public int getValueOfPosition(){
		return valueOfPosition;
	}
	
	/**
	 * Method that sets a family member in the multiple position zone and updates the counter increasing it by 1 and setting
	 * the position as occupied and adding the family member put in this multiple position zone in the list of family
	 * members in the zone
	 * @param familyMemberInPosition It's the family member that has to be set in the multiple position zone
	 */
	public void setFamilyMember(FamilyMember familyMemberInPosition){
		this.familyMemberInPosition.add(familyMemberInPosition);
		positionFree=false;
		setCounter(getCounter() + 1);
	}
	
	/**
	 * Method that returns the family member in the position expressed in the parameter
	 * @param number It's the number of position from which the corresponding family member has to be got
	 * @return the family member in the corresponding number of position
	 */
	public FamilyMember GetFamilyMember(int number){
		if(number<=counter){
			return familyMemberInPosition.get(number);
		}
		return null;
	}
	
	/**
	 * Method that checks if the multiple position zone is free or not 
	 * @return true if the multiple position zone is free; false if it isn't
	 */
	public boolean isPositionFree(){
		return positionFree;
	}
	
	/**
	 * Method that sets the boolean positionFree as indicated in the parameter
	 * @param isPositionFree It's value that positionFree will assume after calling this method
	 */
	public void setIsPositionFree(boolean isPositionFree){
		this.positionFree=isPositionFree;
	}

	/**
	 * Method that returns the counter of the family members that are occupying this multiple position zone
	 * @return the counter of the family members
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Method that sets the counter of the family members to the one contained in the parameter
	 * @param counter It's the counter of the family members that this multiple position zone will assume after calling
	 * this method
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	/**
	 * Method that returns the malus that is applied to the value of the action when putting a family member in this 
	 * multiple position zone
	 * @return the malus applied to the value of the action when putting a family member in this multiple position zone
	 */
	public int getMultipleActionMalus() {
		return actionMalus;
	}
	
	/**
	 * Method used at the end of every round, to remove all the family members contained in the list of family members 
	 * that are occupying the multiple position zone and to reset the positions as free
	 */
	public void clear(){
		familyMemberInPosition.clear();
		setIsPositionFree(true);
	}
	
	


}
