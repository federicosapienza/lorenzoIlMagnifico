package it.polimi.ingsw.GC_26.model.game.gameComponents.board.positions.common;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26.model.game.gameComponents.familyMembers.FamilyMember;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Abstract class that represents the single position zones, that characterize some zones of the board, such as the Single
 * Harvest zone, the Single Production zone or the action spaces of the Market 
 *
 */
public abstract class SinglePosition{
		private final int valueOfPosition;
		private List<FamilyMember> familyMemberInPosition;
		private boolean positionFree=true;
		
		/**
		 * Constructor: it creates a free single position zone with the minimum required value that the family members of the player 
		 * must have to be put in this zone. 
		 * @param valueOfPosition the minimum required value that the family members of the player 
		 * must have to be put in this zone
		 */
		public SinglePosition(int valueOfPosition){
			this.valueOfPosition=valueOfPosition;
			 familyMemberInPosition =new ArrayList<>();
			 positionFree=true;
		}
		
		/**
		 * Method that returns the minimum required value that the family members must have to occupy this single position zone
		 * @return the minimum required value that the family members must have to occupy this single position zone
		 */
		public int getValueOfPosition(){
			return valueOfPosition;
		}
		
		/**
		 * Method that sets a family member in the single position zone and updates the flag of the position, setting it 
		 * as occupied and adding the family member put in this single position zone in the list of family members 
		 * in the zone
		 * @param familyMemberInPosition It's the family member that has to be set in the single position zone
		 */
		public void setFamilyMember(FamilyMember familyMember){
			familyMemberInPosition.add(familyMember);
			positionFree=false;
		}
		
		/**
		 * Method that checks if the single position zone is free or not 
		 * @return true if the single position zone is free; false if it isn't
		 */
		public boolean IsPositionFree(){
			return positionFree;
		}
		
		/**
		 * Method used at the end of every round, to remove all the family members contained in the list of family members 
		 * that are occupying the single position zone and to reset the positions as free
		 */
		public void clear(){
			positionFree=true;
			familyMemberInPosition.clear();
		}

	}

