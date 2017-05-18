package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class TowerPosition {
	private int floor;
	private FamilyMember familyMemberInPosition;
	private ResourcesOrPoints resourcesOrPointsinPosition; //non capisco il warning che mi da
	private int valueOfPosition;
	private DevelopmentCard cardInPosition;
	
	public TowerPosition(int floor, ResourcesOrPoints resourcesOrPointsinPosition,int valueOfPosition){
		this.floor=floor;
		this.resourcesOrPointsinPosition=resourcesOrPointsinPosition;
		this.valueOfPosition=valueOfPosition;
	}
	public void setCard(DevelopmentCard cardInPosition){ 
		this.cardInPosition=cardInPosition;
	}
	
	public DevelopmentCard getCard(){
		return cardInPosition;
	}
	
	public void setFamilyMember(FamilyMember familyMemberInPosition){
		this.familyMemberInPosition=familyMemberInPosition;
	}
	
	public FamilyMember GetFamilyMember(){
		return familyMemberInPosition;
	}
	
	public void clear(){
		familyMemberInPosition=null;
		cardInPosition=null;
	}

	@Override
	public String toString(){
		return "Floor:" + floor + " Value of the position: " + valueOfPosition + " Bonus resources: " + resourcesOrPointsinPosition.toString();
	}
    
	private void payCoins(){
		//gli passo il player?
	}
}
