package it.polimi.ingsw.GC_26_board;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;

public abstract class SinglePosition{
		private final int valueOfPosition;
		private List<FamilyMember> familyMemberInPosition;
		private boolean positionFree=true;
		
		public SinglePosition(int valueOfPosition){
			this.valueOfPosition=valueOfPosition;
			 familyMemberInPosition =new ArrayList<>();
		}
		
		public int getValueOfPosition(){
			return valueOfPosition;
		}
		
		public void setFamilyMember(FamilyMember familyMember){
			familyMemberInPosition.add(familyMember);
			positionFree=false;
		}
		
		
		public boolean IsPositionOccupied(){
			return positionFree;
		}
		
		public void clear(){
			positionFree=true;
			familyMemberInPosition.clear();
		}

	}

