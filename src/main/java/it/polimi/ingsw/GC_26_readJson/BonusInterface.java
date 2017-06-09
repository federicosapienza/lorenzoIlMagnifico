package it.polimi.ingsw.GC_26_readJson;

import java.util.List;
import java.util.Map;

import it.polimi.ingsw.GC_26_personalBoard.PersonalBoardTile;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public interface BonusInterface {
	List<ResourcesOrPoints> getResourcesOrPointsStarting();
	List<ResourcesOrPoints[]> getListOfResourcesOfPointsArray();
	Map<Integer, Integer> getFaithTrack();
	List<PersonalBoardTile> get4RandomBonusTiles();
	
	
}