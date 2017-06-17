package it.polimi.ingsw.GC_26_board;


import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Class that represents the Council Palace of the board
 * 
 *
 */
public class CouncilPalace extends MultiplePosition {
	
	private ResourcesOrPoints ResourcesOrPointsInPosition;
	
	/**
	 * Constructor: it creates a Council Palace that gives to every player who occupies this zone the corresponding
	 * resources or points (by rules 1 coin and 1 Council Privilege) and keeps a record of the value of the position to 
	 * determine the next round order
	 * @param resourcesOrPoints the resources or points that every player who occupies this zone obtains
	 * @param valueOfPosition It's the value of the position in the Council Palace: this determines the next round order
	 */
	public CouncilPalace(ResourcesOrPoints resourcesOrPoints,int valueOfPosition){
		super(valueOfPosition);
		this.ResourcesOrPointsInPosition=resourcesOrPoints;
	}
	
	/**
	 * Method used to add the resources or points that the player has obtained from the Council Palace to his warehouse
	 * @param player It's the player that has obtained the resources or points from the Council Palace
	 */
	public void addBonusResources(Player player){
		player.getWarehouse().add(ResourcesOrPointsInPosition);
	}
	
	/**
	 * Method that returns the resources or points given by the Council Palace
	 * @return the resources or points given by the Council Palace
	 */
	public ResourcesOrPoints getResourcesOrPointsInPosition() {
		return ResourcesOrPointsInPosition;
	}
	/*public String PrintList(){
		//TODO
	}*/
	
	/*public ArrayList getRanking(){
		//TODO
	}*/
}
