package it.polimi.ingsw.GC_26_actionsHandlers;


import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;


/*Called when player is trading privileges in resources or points:
 * It records the type of privileges used to avoid the player to perform the same trade twice.
 * When it sees that privileges remained are 0 , it resets the memory (=the player has ended the trades with council palace).
 * 
 * the player send an int value that identifies the choice. If the integer is greater than the number of choices ,
 * 	 the first reward possible is given. (choices right value are set between 1 and 5 in the standard game.
 */
public class DiplomaticPrivilegesHandler {
	private  ResourcesOrPoints[] diplomaticPrivilegesTrades= GameParameters.getDiplomaticPrivilegesTrades();
	private boolean used[];

	public DiplomaticPrivilegesHandler() {
		used= new boolean[diplomaticPrivilegesTrades.length];
		resetMemory();
		
	}
	
	public boolean isPossible(Player player , int choice){  
		if(!player.getWarehouse().areResourcesEnough(ResourcesOrPoints.newPoints(0, 0, 0, 1))){
			throw new IllegalStateException();
		}
		
		if(choice >= diplomaticPrivilegesTrades.length){   // If the integer is greater than the number of choice, the first reward possible is given.
			choice=findNotUsed();
		}
			
		if(used[choice]){
			player.notifyObservers("Resources or points already chosen");
			return false;
		}
		else return true;
	}
	
	public void perform(Player player , int choice){  
		
		if(!player.getWarehouse().areResourcesEnough(ResourcesOrPoints.newPoints(0, 0, 0, 1)))
			throw new IllegalStateException();
			
		if(used[choice]){
			throw new IllegalArgumentException();
		}
		
		if(choice >= diplomaticPrivilegesTrades.length){   // If the integer is greater than the number of choice, the first reward possible is given.
			choice=findNotUsed();
		}
		player.getWarehouse().add(diplomaticPrivilegesTrades[choice]);
		player.getWarehouse().spendResources(ResourcesOrPoints.newPoints(0, 0, 0, 1));
		used[choice] = true;
		
		//If privileges remained are 0 , it resets the memory (=the player has ended the trades with council palace).
		// Memory is cleaned also if he already took all the trades.
		if(player.getWarehouse().getCouncilPrivileges()==0){
			resetMemory();
			return;
		}
		
		// Memory is cleaned also if he already took all the trades.
		if(!isSomethingNotUsed())
			resetMemory();
	}
	
	
	
	public void resetMemory(){
		for(int i=0; i< used.length; i++)
			used[i]=false;
	}	
	
	
	private int findNotUsed(){
		for(int i=0; i< used.length; i++){
			if(used[i]==false)
				return i;
		}
		resetMemory();
		return 0;
	}
	
	private boolean isSomethingNotUsed(){
		for(int i=0; i< used.length; i++){
			if(used[i]==false)
				return true;
		}
		return false;
		
	}
	
}
	
	

