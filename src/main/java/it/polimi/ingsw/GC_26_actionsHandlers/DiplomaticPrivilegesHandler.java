package it.polimi.ingsw.GC_26_actionsHandlers;

import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;


/*Called when player is trading privileges in resources or points:
 * It records the type of privileges used to avoid the player to perform the same trade twice.
 * When it sees that privileges remained are 0 , it resets the memory (=the player is ended the trades)
 */
public class DiplomaticPrivilegesHandler {
	private GameElements gameElements;
	private  ResourcesOrPoints[] diplomaticPrivilegesTrades= GameParameters.getDiplomaticPrivilegesTrades();
	private boolean used[];

	public DiplomaticPrivilegesHandler(GameElements gameElements) {
		this.gameElements =gameElements;
		
	}
	
	
	public boolean isPossible(Player player , int choice){  // could throw runtime exception: ClassCastException
		
			return true;}
	

}
