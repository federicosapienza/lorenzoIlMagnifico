package it.polimi.ingsw.gc_26.messages.describers;

import java.io.Serializable;

import it.polimi.ingsw.gc_26.model.game.game_components.board.BoardZone;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This class describes the position of a board zone
 */
public class PositionDescriber implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private final BoardZone zone;
	private final int position;
	private final String resourcesOrPointsOnPosition;
	private final int value;
	
	/**
	 * Constructor: it creates a describer based on the values contained in the parameters
	 * @param zone It's the board zone 
	 * @param position It's the position of the board zone
	 * @param value It's the minimum required value to occupy the zone
	 * @param resourcesOrPointsOnPosition It's the description of the resources or points in the position
	 */
	public PositionDescriber(BoardZone zone, int position, int value, String resourcesOrPointsOnPosition) {
		this.value = value;
		this.zone = zone;
		this.position = position;
		this.resourcesOrPointsOnPosition = resourcesOrPointsOnPosition;
	}
	
	/**
	 * Method that returns the position of the describer
	 * @return the position of the describer
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * Method that returns the description of the resources or points present in the position, as a string
	 * @return the description of the resources or points present in the position
	 */
	public String getResourcesOrPointsOnPosition() {
		return resourcesOrPointsOnPosition;
	}
	
	/**
	 * Method that returns the board zone of the describer
	 * @return the board zone of the describer
	 */
	public BoardZone getZone() {
		return zone;
	}
	
	/**
	 * Method that returns the minimum required value to occupy the zone
	 * @return the minimum required value to occupy the zone
	 */
	public int getValue() {
		return value;
	}
}
