package it.polimi.ingsw.GC_26.model.game.gameComponents.board.zones;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26.model.game.gameComponents.board.positions.MultipleProduction;
import it.polimi.ingsw.GC_26.model.game.gameComponents.board.positions.SingleProduction;
import it.polimi.ingsw.GC_26.model.game.gameComponents.dices.Colour;
import it.polimi.ingsw.GC_26.model.game.gameComponents.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26.model.game.gameLogic.GameParameters;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This class represent the Production zone of the board
 */
public class ProductionZone {
	private SingleProduction singleProduction;
	private MultipleProduction multipleProduction;
	private int positionsNumber;
	private List<Player> playersHere ;

	/**
	 * Constructor: it creates the correct version of the Production zone, according to the number of players contained in the
	 * parameters
	 * @param value1 It's the minimum value of the family members that the single Production zone requires for every player
	 * who wants to occupy the single Production zone
	 * @param value2 It's the minimum value of the action that the multiple Production zone requires to activate for every 
	 * player who wants to occupy the multiple Production zone
	 * @param numberOfPlayers It's the number of players that are playing the game
	 */
	public ProductionZone(int value1, int value2, int numberOfPlayers){
		singleProduction=new SingleProduction(value1);
		playersHere = new ArrayList<>();
		if(numberOfPlayers>=GameParameters.getNumPlayersForMultipleZones()){
				multipleProduction= new MultipleProduction(value2);
				positionsNumber=2;
		}
		else positionsNumber=1;
	}
	
	/**
	 * Method that checks and returns the number of Production zones: 1 if the multiple Production zone has not been 
	 * activated, 2 if it's active
	 * @return the number of Production zones
	 */
	public int getPositionsActivated() {
		return positionsNumber;
	}
	
	/**
	 * Method that returns the status of the single Production zone
	 * @return the status of the single Production zone
	 */
	public SingleProduction getSingleProduction() {
		return singleProduction;
	}
	
	/**
	 * Method that returns the status of the multiple Production zone
	 * @return the status of the multiple Production zone
	 */
	public MultipleProduction getMultipleProduction() {
		return multipleProduction;
	}
	
	/**
	 * Method that adds the family member of the corresponding player in the Production zone
	 * @param familyMember It's the family member that the player wants to put in the Production zone
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
	 * Method used to clear all the family members present in the Production zones and to reset the list of players who 
	 * are occupying these zones
	 */
	public void clear(){
		singleProduction.clear();
		if(multipleProduction!=null)
			multipleProduction.clear();
		playersHere.clear();
	} 

	/**
	 * Method that checks if a player has already occupied the Production zone with a coloured family member
	 * @param familyMember It's the family member of the player that has to be checked
	 * @return true if the player has already occupied the Production zone with a coloured family member before;
	 * false if he hasn't occupied it with a coloured family member, or if the colour of the family member is neutral or if
	 * the family member is null
	 */
	public boolean playerAlreadyHere(FamilyMember familyMember){
		if(familyMember == null || familyMember.getColour() == Colour.NEUTRAL)
			return false;
		return (playersHere.contains(familyMember.getPlayer())); 
	}

}


