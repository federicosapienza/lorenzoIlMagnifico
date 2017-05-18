package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public abstract class SinglePosition{
		private final int valueOfPosition;
		private FamilyMember familyMemberInPosition;
		private boolean isPositionFree=true;
		
		public SinglePosition(int valueOfPosition){
			this.valueOfPosition=valueOfPosition;
		}
		
		public int getValueOfPosition(){
			return valueOfPosition;
		}
		
		public void setFamilyMember(FamilyMember familyMemberInPosition){
			this.familyMemberInPosition=familyMemberInPosition;
			isPositionFree=false;
		}
		
		public FamilyMember GetFamilyMember(){
			return familyMemberInPosition;
		}
		
		public boolean IsPositionOccupied(){
			return isPositionFree;
		}
		
		public abstract void clear();

	}

