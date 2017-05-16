package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class TowerPosition {
	private int floor;
	private FamilyMember familyMemberInPosition;
	private ResourcesOrPoints resoucesOrPointsInPosition;
	private int valueOfPosition;
	private DevelopmentCard cardInPosition;
	
	public TowerPosition(int floor, ResourcesOrPoints resourcesOrPointsinPosition,int valueOfPosition){
		this.floor=floor;
		this.resoucesOrPointsInPosition=resourcesOrPointsinPosition;
		this.valueOfPosition=valueOfPosition;
	}
	public void setCard(DevelopmentCard cardInPosition){ 
		this.cardInPosition=cardInPosition;
	}
	
	public DevelopmentCard getCard(){
		return cardInPosition;
	}
	
	public void setFamilyMember(FamilyMember familyMember){
		this.familyMemberInPosition=familyMemberInPosition;
	}
	
	public FamilyMember GetFamilyMember(){
		return familyMemberInPosition;
	}
	public void clearAll(){
		familyMemberInPosition=null;
		cardInPosition=null;
	}
	@override
	public String toString(){
		return "Floor:" + this.floor + " Value of the position: " + this.valueOfPosition;
	// come faccio a ritornare l'oggetto resourcesOrPointsInPosition?
	}
    
	private payCoins(){
		//gli passo il player?
	}
}
