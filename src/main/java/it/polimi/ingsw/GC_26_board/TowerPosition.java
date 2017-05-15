package it.polimi.ingsw.GC_26_board;

public class TowerPosition {
	private int floor;
	private FamilyMember familyMemberInPosition;
	private ResoucesOrPoints resoucesOrPointsInPosition;
	private int valueOfPosition;
	private DevelopmentCard cardInPosition;
	
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
	
	public void setFamilyMember(FamilyMember familyMember){
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
    
	private payCoins(){
		//gli passo il player?
	}
}
