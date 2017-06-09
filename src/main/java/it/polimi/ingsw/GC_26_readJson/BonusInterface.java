package it.polimi.ingsw.GC_26_readJson;

import java.util.List;

import it.polimi.ingsw.GC_26_personalBoard.PersonalBoardTile;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public interface BonusInterface {
	List<ResourcesOrPoints> getResourcesOrPointsStarting();
	List<ResourcesOrPoints[]> getListOfResourcesOrPointsArray();

	List<PersonalBoardTile> get4RandomBonusTiles();
	
	
}
