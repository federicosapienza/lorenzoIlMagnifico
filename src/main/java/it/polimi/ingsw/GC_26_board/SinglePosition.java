package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;

public abstract class SinglePosition{
		private final int valueOfPosition;
		private FamilyMember familyMemberInPosition;
		private boolean positionFree=true;
		
		public SinglePosition(int valueOfPosition){
			this.valueOfPosition=valueOfPosition;
		}
		
		public int getValueOfPosition(){
			return valueOfPosition;
		}
		
		public void setFamilyMember(FamilyMember familyMemberInPosition){
			this.familyMemberInPosition=familyMemberInPosition;
			positionFree=false;
		}
		
		public FamilyMember getFamilyMember(){
			return familyMemberInPosition;
		}
		
		public boolean IsPositionOccupied(){
			return positionFree;
		}
		
		public abstract void clear();

	}

