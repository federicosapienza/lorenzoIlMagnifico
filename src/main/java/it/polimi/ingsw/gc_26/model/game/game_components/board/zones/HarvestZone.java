package it.polimi.ingsw.gc_26.model.game.game_components.board.zones;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_26.model.game.game_components.board.positions.MultipleHarvest;
import it.polimi.ingsw.gc_26.model.game.game_components.board.positions.SingleHarvest;
import it.polimi.ingsw.gc_26.model.game.game_components.dices.Colour;
import it.polimi.ingsw.gc_26.model.game.game_components.family_members.FamilyMember;
import it.polimi.ingsw.gc_26.model.game.game_logic.GameParameters;
import it.polimi.ingsw.gc_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the Harvest zone of the board
 * 
 *
 */
public class HarvestZone {
	private SingleHarvest singleHarvest;
	private MultipleHarvest multipleHarvest;
	
	/**
	 * It indicates how many spaces the Harvest zone has: 1 if the number of players is equal to 2, 2 if the number of players
	 * is greater or equal to 3
	 */
	private int positionsNumber;
	
	//It's the list of players that have put at least a family member in the Harvest zone
	private List<Player> playersHere;

	/**
	 * Constructor: it creates the correct version of the Harvest zone, according to the number of players contained in the
	 * parameters
	 * @param value1 It's the minimum value of the family members that the single Harvest zone requires for every player
	 * who wants to occupy the single Harvest zone
	 * @param value2 It's the minimum value of the action that the multiple Harvest zone requires to activate for every 
	 * player who wants to occupy the multiple Harvest zone
	 * @param numberOfPlayers It's the number of players that are playing the game
	 */
	public HarvestZone(int value1, int value2, int numberOfPlayers){
		singleHarvest=new SingleHarvest(value1);
		playersHere = new ArrayList<>();

		//if the number of players is greater or equal to 3, the multiple Harvest zone is activated
		if(numberOfPlayers>=GameParameters.getNumPlayersForMultipleZones()){
				multipleHarvest= new MultipleHarvest(value2);
				positionsNumber=2;
		}
		else positionsNumber=1;
		
	}
	
	/**
	 * Method that checks and returns the number of Harvest zones: 1 if the multiple Harvest zone has not been activated, 2 if it's active
	 * @return the number of Harvest zones
	 */
	public int getPositionsActivated() {
		return positionsNumber;
	}
	
	/**
	 * Method that returns the status of the single Harvest zone
	 * @return the status of the single Harvest zone
	 */
	public SingleHarvest getSingleHarvest() {
		return singleHarvest;
	}
	
	/**
	 * Method that returns the status of the multiple Harvest zone
	 * @return the status of the multiple Harvest zone
	 */
	public MultipleHarvest getMultipleHarvest() {
		return multipleHarvest;
	}
	
	/**
	 * Method that adds the family member of the corresponding player in the Harvest zone
	 * @param familyMember It's the family member that the player wants to put in the Harvest zone
	 */
	public void addPlayerHere(FamilyMember familyMember){
		if(familyMember== null)
			return;
		if(playerAlreadyHere(familyMember) && familyMember.getColour()!=Colour.NEUTRAL)
			throw new IllegalArgumentException();
		if(!playerAlreadyHere(familyMember) && familyMember.getColour()!=Colour.NEUTRAL)
			playersHere.add(familyMember.getPlayer());
		
		
	}
	
	/**
	 * Method used to clear all the family members present in the Harvest zones and to reset the list of players who are
	 * occupying these zones
	 */
	public void clear(){
		singleHarvest.clear();
		if(multipleHarvest!=null)
			multipleHarvest.clear();
		playersHere.clear();
	} 
	
	/**
	 * Method that checks if a player has already occupied the Harvest zone with a coloured family member
	 * @param familyMember It's the family member of the player that has to be checked
	 * @return true if the player has already occupied the Harvest zone with a coloured family member before;
	 * false if he hasn't occupied it with a coloured family member, or if the colour of the family member is neutral or if
	 * the family member is null
	 */
	
	public boolean playerAlreadyHere(FamilyMember familyMember){
		if(familyMember == null || familyMember.getColour() == Colour.NEUTRAL)
			return false;
		return (playersHere.contains(familyMember.getPlayer())); 
	}
}
