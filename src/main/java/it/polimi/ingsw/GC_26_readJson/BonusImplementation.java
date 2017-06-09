package it.polimi.ingsw.GC_26_readJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.GC_26_personalBoard.PersonalBoardTile;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class BonusImplementation implements BonusInterface {



	private List<ResourcesOrPoints[]> listOfResourcesOfPointsArray = new ArrayList<ResourcesOrPoints[]>();
	private List<ResourcesOrPoints> resourcesOrPointsStarting = new ArrayList<ResourcesOrPoints>(); 
	private Map<Integer,Integer> faithTrack = new HashMap<>();
	
	@Override
	public List<ResourcesOrPoints[]> getListOfResourcesOfPointsArray() {
		return listOfResourcesOfPointsArray;
	}
	
	@Override
	public List<ResourcesOrPoints> getResourcesOrPointsStarting() {
		return resourcesOrPointsStarting;
	}

	

	

	@Override

	public Map<Integer, Integer> getFaithTrack() {
		return faithTrack;
	}

	@Override
	public List<PersonalBoardTile> get4RandomBonusTiles() {
		// TODO Auto-generated method stub
		return null;
	}


}
