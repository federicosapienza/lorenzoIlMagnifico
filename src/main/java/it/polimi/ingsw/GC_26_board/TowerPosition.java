package it.polimi.ingsw.GC_26_board;

public class TowerPosition {
	private int floor;
	private FamilyMember familyMemberInPosition;
	private ResoucesOrPoints resoucesOrPointsInPosition;
	private int valueOfPosition;
	private DevelopmentCard cardInPosition;
	
	public TowerPosition(int floor, ResoucesOrPoints resoucesOrPointsinPosition,int value){
		this.floor=floor;
		this.resoucesOrPointsInPosition=resoucesOrPointsinPosition;
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
	//string ToString
    
	private payCoins(){
		//gli passo il player?
	}
}
