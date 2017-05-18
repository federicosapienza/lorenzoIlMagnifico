package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class TowerPosition extends SinglePosition{
	private int floor;
	private ResourcesOrPoints resourcesOrPointsinPosition; //non capisco il warning che mi da
	private DevelopmentCard cardInPosition;
	
	public TowerPosition(int floor, ResourcesOrPoints resourcesOrPointsinPosition,int valueOfPosition){
		super(valueOfPosition); //dovrebbe utilizzare il costruttore base.
		this.floor=floor;
		this.resourcesOrPointsinPosition=resourcesOrPointsinPosition;
	}
	public void setCard(DevelopmentCard cardInPosition){ 
		this.cardInPosition=cardInPosition;
	}
	
	public DevelopmentCard getCard(){
		return cardInPosition;
	}
	
	public void clear(){
		setFamilyMember(null);
		setCard(null);
	}

	@Override
	/*public String toString(){
		return "Floor:" + floor + " Value of the position: " + valueOfPosition + " Bonus resources: " + resourcesOrPointsinPosition.toString();
	}*/
    
	private void payCoins(){
		//gli passo il player?
	}
}
