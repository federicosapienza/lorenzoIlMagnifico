package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class TowerPosition extends SinglePosition{
	private int floor;
	private ResourcesOrPoints resourcesOrPointsinPosition;
	private DevelopmentCard cardInPosition;
	
	public TowerPosition(int floor, ResourcesOrPoints resourcesOrPointsinPosition,int valueOfPosition){
		super(valueOfPosition); //dovrebbe utilizzare il costruttore base.
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
	
	public void clear(){
		setFamilyMember(null);
		setCard(null);
	}

	/*@Override
	public String toString(){
		return "Floor:" + floor + super.ToString() ;
	}*/
    
	
}
