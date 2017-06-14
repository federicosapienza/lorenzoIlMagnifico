package it.polimi.ingsw.GC_26_readJson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import it.polimi.ingsw.GC_26_personalBoard.PersonalBoardTile;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

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



	private List<ResourcesOrPoints[]> listOfResourcesOfPointsArray = new ArrayList<ResourcesOrPoints[]>();
	private List<ResourcesOrPoints> resourcesOrPointsStarting = new ArrayList<ResourcesOrPoints>();
	private List<PersonalBoardTile> personalBoardTilesNormal = new ArrayList<PersonalBoardTile>();
	private List<PersonalBoardTile> personalBoardTilesAdvanced = new ArrayList<PersonalBoardTile>();
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
	 * Getter method that returns the faithTrack as a map.
	 */
	@Override

	public Map<Integer, Integer> getFaithTrack() {
		return faithTrack;
	}
	
	/**
	 * Getter method that returns the list of personal bonus tiles
	 */
	@Override
	public List<PersonalBoardTile> get4RandomPersonalBoardTiles(String normalOrAdvanced) {
		if(normalOrAdvanced.equals("normal")){
			Collections.shuffle(personalBoardTilesNormal);
			return personalBoardTilesNormal;
		}
		if(normalOrAdvanced.equals("advanced")){
			Collections.shuffle(personalBoardTilesAdvanced);
			return personalBoardTilesAdvanced;
		}
		return null;
	}

	public List<PersonalBoardTile> getPersonalBoardTiles(String nomrmalOrAdvanced){
		if(nomrmalOrAdvanced.equals("normal")){
			return personalBoardTilesNormal;
		}
		if(nomrmalOrAdvanced.equals("advanced")){
			return personalBoardTilesAdvanced;
		}
		return null;
	}

}
