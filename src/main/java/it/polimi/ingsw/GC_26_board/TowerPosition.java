package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class TowerPosition extends SinglePosition{
	private int floor;
	private ResourcesOrPoints resourcesOrPointsinPosition;
	private DevelopmentCard cardInPosition;
	
	public TowerPosition(int floor, ResourcesOrPoints resourcesOrPointsinPosition,int valueOfPosition){
		super(valueOfPosition); 
		this.floor=floor;
		this.resourcesOrPointsinPosition=resourcesOrPointsinPosition;
	}
	public void setCard(DevelopmentCard cardInPosition){ 
		this.cardInPosition=cardInPosition;
	}
	public int getFloor() {
		return floor;
	}
	
	public ResourcesOrPoints getResourcesOrPointsinPosition() {
		return resourcesOrPointsinPosition;
	}
	
	public DevelopmentCard getCard(){
		return cardInPosition;
	}
	@Override
	public void clear(){
		super.clear();
		setCard(null);
	}

	
}
