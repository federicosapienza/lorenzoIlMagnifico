package it.polimi.ingsw.GC_26_board;

public class TowerPosition {
	int floor;
	FamilyMember familyMemberInPosition;
	ResoucesOrPoints resoucesOrPointsInPosition;
	int valueInPosition;
	DevelopmentCard cardInPosition;
	
	public TowerPosition(int floor, ResoucesOrPoints resoucesOrPointsinPosition,int value){
		this.floor=floor;
		this.resoucesOrPoints=resourcesOrPoints;
		this.value=valueInPosition;
	}
	public void setCard(DevelopmentCard cardInPosition){ 
		this.cardInPosition=cardInPosition;
	}
	
	public DevelopmentCard getCard(){
		return cardInPosition;
	}
	
	public SetFamilyMember(FamilyMember familymember){
		this.familyMember=familyMember;
	}
	
	public FamilyMember GetFamilyMember(){
		return familyMemberInPosition;
	}
	// absTRACT in singleposition, qua implementato.
	public void clearAll(){
		setCard(null);
		setFamilyMember(null);
	}
	//string ToString
}
