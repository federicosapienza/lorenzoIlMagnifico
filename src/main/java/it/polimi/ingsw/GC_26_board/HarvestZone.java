package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;

public class HarvestZone {
	private SingleHarvest singleHarvest;
	private MultipleHarvest multipleHarvest;
	int numberOfPlayers;
	
	public HarvestZone(int value1, int value2, int numberOfPlayers){
		this.numberOfPlayers=numberOfPlayers;
		singleHarvest=new SingleHarvest(value1);
		if(numberOfPlayers>=GameParameters.getNumPlayersForMultipleZones())
				multipleHarvest= new MultipleHarvest(value2);
	}
	
	public SingleHarvest getSingleHarvest() {
		return singleHarvest;
	}
	
	public MultipleHarvest getMultipleHarvest() {
		return multipleHarvest;
	}
	
	public void clear(){
		singleHarvest.clear();
		multipleHarvest.clear();
	} 
		//checks if there is one coloured family member for the player passed;
		//if member is neutral or member== null (it means second action) returns true)
	public boolean playerAlreadyHere(FamilyMember familyMember){
		if(familyMember == null || familyMember.getColour() == Colour.NEUTRAL)
			return true;
		return (singleHarvest.getFamilyMember() ==null || singleHarvest.getFamilyMember()==familyMember); 
	}

}
