package it.polimi.ingsw.gc_26.model.game.game_components.board.zones;

import java.util.*;

import it.polimi.ingsw.gc_26.model.game.game_components.board.positions.TowerPosition;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.gc_26.model.game.game_components.dices.Colour;
import it.polimi.ingsw.gc_26.model.game.game_components.family_members.*;
import it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.game.game_logic.GameParameters;
import it.polimi.ingsw.gc_26.model.player.Player;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * It's the class that represents the tower. There are 4 towers in the game, each with 4 floors. 
 * On every floor, there is an action space that allows the player to take the corresponding card and, 
 * if present, some bonuses.
 *
 */
public class Tower {
	private boolean isTowerFree=true;
	private List<Player> playersInTheTower = new ArrayList<>();
	private List<DevelopmentCard> cardsForThisRound = new ArrayList<>();
	private TowerPosition towerPositionFloor1;
	private TowerPosition towerPositionFloor2;
	private TowerPosition towerPositionFloor3;
	private TowerPosition towerPositionFloor4;
	
	/**
	 * Constructor: it creates the towers with the corresponding array of resources or points
	 * @param resourcesOrPoints It's the array of resources or points that have to be put in the floors of the towers 
	 */
	public Tower(ResourcesOrPoints[] resourcesOrPoints){
		createTowerPositions(resourcesOrPoints);
	}
	
	/**
	 * Method that creates the floors of the towers with the correct corresponding resources or points taken from the array
	 * of resources or points indicated in the parameter
	 * @param resourcesOrPoints It's the array of resources or points which contains the correct resources for every floor
	 */
	private void createTowerPositions(ResourcesOrPoints[] resourcesOrPoints){
		towerPositionFloor1 = new TowerPosition(1,resourcesOrPoints[0],getFloorValue(1));
		towerPositionFloor2 = new TowerPosition(2,resourcesOrPoints[1],getFloorValue(2));
		towerPositionFloor3 = new TowerPosition(3,resourcesOrPoints[2],getFloorValue(3));
		towerPositionFloor4 = new TowerPosition(4,resourcesOrPoints[3],getFloorValue(4));
	}

	/**
	 * Method that returns the position of the tower that corresponds to the floor contained in the parameter
	 * @param floor It's the floor to analyze to get the corresponding position of the tower
	 * @return the correct position of the tower that corresponds to the floor contained in the parameter
	 */
	public TowerPosition getPosition(int floor){
		switch(floor) {
		case 1:
			return towerPositionFloor1;
		case 2:
			return towerPositionFloor2;
		case 3:
			return towerPositionFloor3;
		case 4:
			return towerPositionFloor4;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Method that returns the minimum required value that the family member must have to be put in the corresponding 
	 * floor indicated in the parameter
	 * @param floor It's the number of floor to analyze
	 * @return the minimum required value that the family member must have to be put in the corresponding 
	 * floor indicated in the parameter
	 */
	public int getFloorValue(int floor){
		return GameParameters.getTowersFloorsValues(floor);
	}
	
	/**
	 * Method that sets the list of development cards to use in the current round
	 * @param cards the list of development cards to use in the current round
	 */
	public void setCardsForThisRound(List<DevelopmentCard> cards){
		cardsForThisRound=cards;
		setCardsInPositions();
	}
	
	/**
	 * Method that sets the cards in the corresponding towers with the correct order
	 */
	private void setCardsInPositions(){
		towerPositionFloor1.setCard(cardsForThisRound.get(0));
		towerPositionFloor2.setCard(cardsForThisRound.get(1));
		towerPositionFloor3.setCard(cardsForThisRound.get(2));
		towerPositionFloor4.setCard(cardsForThisRound.get(3));
	}
	
	/**
	 * Method called at the end of every round to remove all the cards and the family members present in the towers
	 * and to reset the towers as free
	 */
	public void clearCardsAndFamilyMembers(){ 
		isTowerFree=true;
		playersInTheTower.clear();
		towerPositionFloor1.clear();
		towerPositionFloor2.clear();
		towerPositionFloor3.clear();
		towerPositionFloor4.clear();
	    }
	

	/**
	 * Method that checks if the family member can be put in the tower
	 * @param familyMember It's the family member that the player wants to put in the tower
	 * @return true if it is possible; there are three possibilities 
	 * 1)the tower doesn't contain other coloured family members that belong to the player who wants to put another 
	 * coloured family member 
	 * 2) the player wants to put a neutral family member
	 * 3) the family member is null, which implies the action is a second action.
	 * 
	 * It returns false if it isn't possible (there is already a coloured family member of the player who wants to put another coloured
	 * family member in the same tower)
	 */
	
	
	public boolean canFamilyMemberGoToTheTower(FamilyMember familyMember){
		if(familyMember==null || familyMember.getColour()==Colour.NEUTRAL)
			return true;
		for(Player p: playersInTheTower){
			if(p.equals(familyMember.getPlayer())){
				return false;
			}
		}
			return true; 	
	}
	
	/**
	 * Method that sets a family member in the tower. Every time this is done, the player that has performed this action
	 * is added to the list of players who are occupying the tower except in two cases:
	 * 1) the family member is neutral;
	 * 2) the family member is null, which implies the action is a second action
	 * @param familyMember It's the family member that has to be put in the tower
	 */

	public void setPlayerInTheTower(FamilyMember familyMember){
		if(familyMember== null)
			return;
		if(familyMember.getColour()!=Colour.NEUTRAL){
			if(!canFamilyMemberGoToTheTower(familyMember))
				throw new IllegalArgumentException();
			else{ playersInTheTower.add(familyMember.getPlayer());
			      isTowerFree = false; }
		}
	}
	
	/**
	 * Method that checks if the tower is free or not
	 * @return true if the tower is free; false if it isn't
	 */
	public boolean isTheTowerFree(){
		return isTowerFree;
	}

	
	
	

	
	
}
