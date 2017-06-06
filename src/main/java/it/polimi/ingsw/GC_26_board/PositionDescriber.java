package it.polimi.ingsw.GC_26_board;

import java.io.Serializable;

public class PositionDescriber implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private final BoardZone zone;
	private final int position;
	private final String resourcesOrPointsOnPosition;
	private final int value;
	
	public PositionDescriber(BoardZone zone, int position, int value, String resourcesOrPointsOnPosition) {
		this.value =value;
		this.zone = zone;
		this.position = position;
		this.resourcesOrPointsOnPosition = resourcesOrPointsOnPosition;
	}
	
	
	public int getPosition() {
		return position;
	}
	
	public String getResourcesOrPointsOnPosition() {
		return resourcesOrPointsOnPosition;
	}
	public BoardZone getZone() {
		return zone;
	}
	public int getValue() {
		return value;
	}
}
