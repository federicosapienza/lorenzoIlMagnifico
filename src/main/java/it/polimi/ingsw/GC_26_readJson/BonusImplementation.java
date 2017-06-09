package it.polimi.ingsw.GC_26_readJson;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class BonusImplementation implements BonusInterface {

	
	public List<ResourcesOrPoints[]> getListOfResourcesOfPointsArray() {
		return listOfResourcesOfPointsArray;
	}

	private List<ResourcesOrPoints[]> listOfResourcesOfPointsArray = new ArrayList<ResourcesOrPoints[]>();

	@Override
	public List<ResourcesOrPoints[]> getResourcesOrPointsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourcesOrPoints> resourcesOrPointsStarting() {
		// TODO Auto-generated method stub
		return null;
	}

}
