package it.polimi.ingsw.GC_26.jsonReader;

import java.util.List;
import java.util.Map;

import it.polimi.ingsw.GC_26.model.game.gameComponents.personalBoard.PersonalBoardTile;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Interface for the bonus logic.
 *
 */
public interface BonusInterface {
	List<ResourcesOrPoints> getResourcesOrPointsStarting();
	List<ResourcesOrPoints[]> getListOfResourcesOfPointsArray();
	Map<Integer, Integer> getFaithTrack();
	List<PersonalBoardTile> get4RandomPersonalBoardTiles(String normalOrAdvanced);
	
	
}