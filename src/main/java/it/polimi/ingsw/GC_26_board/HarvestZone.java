package it.polimi.ingsw.GC_26_board;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;

public class HarvestZone {
	private SingleHarvest singleHarvest;
	private MultipleHarvest multipleHarvest;
	private int positionsNumber;
	private List<Player> playersHere ;

	
	public HarvestZone(int value1, int value2, int numberOfPlayers){
		singleHarvest=new SingleHarvest(value1);
		playersHere = new ArrayList<>();

		if(numberOfPlayers>=GameParameters.getNumPlayersForMultipleZones()){
				multipleHarvest= new MultipleHarvest(value2);
				positionsNumber=2;
		}
		else positionsNumber=1;
		
	}
	
	public int getPositionsActivated() {
		return positionsNumber;
	}
	
	public SingleHarvest getSingleHarvest() {
		return singleHarvest;
	}
	
	public MultipleHarvest getMultipleHarvest() {
		return multipleHarvest;
	}
	
	public void addPlayerHere(FamilyMember familyMember){
		if(familyMember== null)
			return;
		if(playerAlreadyHere(familyMember))
			throw new IllegalArgumentException();
		if( familyMember.getColour()!=Colour.NEUTRAL)
			playersHere.add(familyMember.getPlayer());
		
		
	}
	
	
	public void clear(){
		singleHarvest.clear();
		if(multipleHarvest!=null)
			multipleHarvest.clear();
		playersHere.clear();
	} 
		//checks if there is one coloured family member for the player passed;
		//if member is neutral or member== null (it means second action) returns true)
	public boolean playerAlreadyHere(FamilyMember familyMember){
		if(familyMember == null || familyMember.getColour() == Colour.NEUTRAL)
			return true;
		return (playersHere.contains(familyMember.getPlayer())); 
	}
}
