package it.polimi.ingsw.GC_26_readJson;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_personalBoard.PersonalBoardTile;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class BonusImplementation implements BonusInterface {

	
	public List<ResourcesOrPoints[]> getListOfResourcesOfPointsArray() {
		return listOfResourcesOfPointsArray;
	}

	private List<ResourcesOrPoints[]> listOfResourcesOfPointsArray = new ArrayList<ResourcesOrPoints[]>();

	@Override
	public List<ResourcesOrPoints[]> getListOfResourcesOrPointsArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourcesOrPoints> getResourcesOrPointsStarting() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonalBoardTile> get4RandomBonusTiles() {
		// TODO Auto-generated method stub
		return null;
	}

}
