package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class MarketPosition extends SinglePosition {
	int number;
	private ResourcesOrPoints resourcesOrPointsinPosition;
	
	public MarketPosition(int number,ResourcesOrPoints resourcesOrPointsinPosition,int valueOfPosition){
		super(valueOfPosition);
		this.number=number;
		this.resourcesOrPointsinPosition=resourcesOrPointsinPosition;
	}

	@Override
	public void clear() {
		setFamilyMember(null);
	}
}
