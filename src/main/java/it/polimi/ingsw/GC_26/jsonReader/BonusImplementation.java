package it.polimi.ingsw.GC_26.jsonReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
 * Class that implements the bonus logic.
 *
 */
public class BonusImplementation implements BonusInterface {



	private List<ResourcesOrPoints[]> listOfResourcesOfPointsArray = new ArrayList<>();
	private List<ResourcesOrPoints> resourcesOrPointsStarting = new ArrayList<>();
	private List<PersonalBoardTile> personalBoardTilesNormal = new ArrayList<>();
	private List<PersonalBoardTile> personalBoardTilesAdvanced = new ArrayList<>();
	private Map<Integer,Integer> faithTrack = new HashMap<>();
	
	/**
	 * Getter method that returns the list of resources or points 
	 */
	@Override
	public List<ResourcesOrPoints[]> getListOfResourcesOfPointsArray() {
		return listOfResourcesOfPointsArray;
	}
	
	/**
	 * Getter method that returns the list of starting resources or points 
	 */
	@Override
	public List<ResourcesOrPoints> getResourcesOrPointsStarting() {
		return resourcesOrPointsStarting;
	}

	/**
	 * Getter method that returns the current status of the faithTrack as a map.
	 */
	@Override

	public Map<Integer, Integer> getFaithTrack() {
		return faithTrack;
	}
	
	/**
	 * Getter method that returns the list of personal bonus tiles, after a random shuffle: normal if the game has been playing with basic rules,
	 * advanced if the games has been playing with advanced rules.
	 */
	@Override
	public List<PersonalBoardTile> get4RandomPersonalBoardTiles(String normalOrAdvanced) {
		if("normal".equals(normalOrAdvanced)){
			Collections.shuffle(personalBoardTilesNormal);
			return personalBoardTilesNormal;
		}
		if("advanced".equals(normalOrAdvanced)){
			Collections.shuffle(personalBoardTilesAdvanced);
			return personalBoardTilesAdvanced;
		}
		return Collections.emptyList();
	}

	/**
	 * Getter method that returns the list of personal bonus tiles: normal if the game has been playing with basic rules,
	 * advanced if the games has been playing with advanced rules.
	 */
	public List<PersonalBoardTile> getPersonalBoardTiles(String normalOrAdvanced){
		if("normal".equals(normalOrAdvanced)){
			return personalBoardTilesNormal;
		}
		if("advanced".equals(normalOrAdvanced)){
			return personalBoardTilesAdvanced;
		}
		else {
			return Collections.emptyList();
		}
		
	}

}
