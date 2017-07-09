package it.polimi.ingsw.gc_26.json_reader;

import java.util.List;
import java.util.Map;

import it.polimi.ingsw.gc_26.model.game.game_components.personal_board.PersonalBoardTile;
import it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points.ResourcesOrPoints;

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