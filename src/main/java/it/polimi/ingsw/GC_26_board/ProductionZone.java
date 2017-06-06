package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;

public class ProductionZone {
	private SingleProduction singleProduction;
	private MultipleProduction multipleProduction;
	private int positionsNumber;
	
	public ProductionZone(int value1, int value2, int numberOfPlayers){
		singleProduction=new SingleProduction(value1);
		if(numberOfPlayers>=GameParameters.getNumPlayersForMultipleZones()){
				multipleProduction= new MultipleProduction(value2);
				positionsNumber=2;
		}
		else positionsNumber=1;
	}
	
	public int getPositionsActivated() {
		return positionsNumber;
	}
	public SingleProduction getSingleProduction() {
		return singleProduction;
	}
	
	public MultipleProduction getMultipleProduction() {
		return multipleProduction;
	}
	
	public void clear(){
		singleProduction.clear();
		multipleProduction.clear();
	} 
		//checks if there is one coloured family member for the player passed;
		//if member is neutral or member== null (it means second action) returns true)
	public boolean playerAlreadyHere(FamilyMember familyMember){
		if(familyMember == null || familyMember.getColour() == Colour.NEUTRAL)
			return true;
		return (singleProduction.getFamilyMember() ==null || singleProduction.getFamilyMember()==familyMember); 
	}

}


