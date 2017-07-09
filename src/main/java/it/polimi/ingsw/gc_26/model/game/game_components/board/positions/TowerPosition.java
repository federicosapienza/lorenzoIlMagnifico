package it.polimi.ingsw.gc_26.model.game.game_components.board.positions;

import it.polimi.ingsw.gc_26.model.game.game_components.board.positions.common.SinglePosition;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.gc_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This is the class containing all the logic about the positions of towers.
 */
public class TowerPosition extends SinglePosition{
	private int floor;
	private ResourcesOrPoints resourcesOrPointsinPosition;
	private DevelopmentCard cardInPosition;
	
	/**
	 * Constructor: it creates a position for a tower, with the values indicated in the parameters.
	 * @param floor the floor of the tower
	 * @param resourcesOrPointsinPosition the resources or points that the player can obtain, putting a family member
	 * in this position
	 * @param valueOfPosition the value corresponding to this position
	 */
	public TowerPosition(int floor, ResourcesOrPoints resourcesOrPointsinPosition,int valueOfPosition){
		super(valueOfPosition); 
		if (floor < 1 || floor > 4) {
			throw new IllegalArgumentException();
		}
		this.floor=floor;
		this.resourcesOrPointsinPosition=resourcesOrPointsinPosition;
	}
	
	/**
	 * Setter method that puts the card expressed by the parameter in this position of the tower
	 * @param cardInPosition the card that has to be put in this position of the tower
	 */
	public void setCard(DevelopmentCard cardInPosition){ 
		this.cardInPosition=cardInPosition;
	}
	
	/**
	 * Getter method that returns the floor of the position of the position of the tower
	 * @return the floor of the position of the position of the tower
	 */
	public int getFloor() {
		return floor;
	}
	
	/**
	 * Getter method that returns the resources or points that a player can obtain putting a family member in this position
	 * @return the resources or points that a player can obtain putting a family member in this position
	 */
	public ResourcesOrPoints getResourcesOrPointsinPosition() {
		return resourcesOrPointsinPosition;
	}
	
	/**
	 * Getter method that returns the card present in this position
	 * @return the card present in this position
	 */
	public DevelopmentCard getCard(){
		return cardInPosition;
	}
	
	/**
	 * Method used at the end of every period to clear all the cards present in every tower
	 */
	@Override
	public void clear(){
		super.clear();
		setCard(null);
	}

	
}
