package it.polimi.ingsw.GC_26_actionsHandlers;


import java.util.List;

import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;


/**
 * Class called when player is trading Council privileges in resources or points:
 * It records the type of privileges used to avoid the player to perform the same trade twice.
 * When the remained privileges are 0, it resets the memory (i.e. the player has ended the trades with council palace).
 * 
 * The player sends an int value that identifies the choice. If the Integer is greater than the number of choices,
 * 	the first reward possible is given. (The values for the choices are set between 1 and 5 in the standard game)
 */
public class DiplomaticPrivilegesHandler{
	private  ResourcesOrPoints[] diplomaticPrivilegesTrades= GameParameters.getDiplomaticPrivilegesTrades();
	private boolean used[]= new boolean[GameParameters.getDiplomaticPrivilegesTrades().length];

	/**
	 * Constructor: it creates an array of boolean initialized to false
	 */
	public DiplomaticPrivilegesHandler() {
		used= new boolean[diplomaticPrivilegesTrades.length];
		resetMemory();
		
	}
	
	/**
	 * Method that checks if the choice of the player is possible according to the rules
	 * @param player It's the player who is choosing 
	 * @param choice It's the choice of the player
	 * @return true if the resources or points chosen by the player hadn't been already taken before and the value of
	 * the choice is between 1 and 5 and he has enough resources in his warehouse;
	 * false if the value of the choice is not between 1 and 5 or if the player makes the same choice twice.
	 * 
	 */
	public boolean isPossible(Player player, int choice){  
		if(!player.getWarehouse().areResourcesEnough(ResourcesOrPoints.newPoints(0, 0, 0, 1))){
			throw new IllegalStateException();
		}
		
		if(choice >= diplomaticPrivilegesTrades.length){   
			player.notifyObservers(new Request(player.getStatus(),"Insert a valid choice", null));
			return false;
		}
			
		if(used[choice]){
			player.notifyObservers(new Request(player.getStatus(),"Resources or points already chosen", null));
			return false;
		}
		else return true;
	}
	
	/**
	 * Method that performs the trade connected to the Council Privilege 
	 * @param player It's the player who performs the trade and obtains the resources or points
	 * @param choice It's the value of the choice that determines the trade 
	 */
	public void perform(Player player, int choice){  
		
		if(!player.getWarehouse().areResourcesEnough(ResourcesOrPoints.newPoints(0, 0, 0, 1)))
			throw new IllegalStateException();
			
		if(used[choice]){
			throw new IllegalArgumentException();
		}
		
		// If the integer is greater than the maximum number of possible choice, the first possible reward is automatically given.
		if(choice >= diplomaticPrivilegesTrades.length){   
			choice=findNotUsed();
		}
		
		//the choice of the player is recorded 
		player.getWarehouse().add(diplomaticPrivilegesTrades[choice]);
		player.getWarehouse().spendResources(ResourcesOrPoints.newPoints(0, 0, 0, 1));
		used[choice] = true;
		
		//If the remained privileges are 0, the memory is resetted (i.e. the player has ended the trades with council palace).
		
		if(player.getWarehouse().getCouncilPrivileges()==0){
			resetMemory();
			return;
		}
		
		// Memory is cleaned also if all the trades are taken
		if(!isSomethingNotUsed())
			resetMemory();
	}
	
	
	/**
	 * Method used to reset the array that represents the memory of the used choices for trades:
	 * all the elements of the array "used" are set to false
	 */
	public void resetMemory(){
		for(int i=0; i< used.length; i++)
			used[i]=false;
	}	
	
	/**
	 * Method called when the player' choice has a value that is greater than the maximum (by default it's 5):
	 * it finds the first choice that hasn't been selected yet and sets it as the choice of the player
	 * @return the value of the first choice that hasn't been selected yet; if all the choices had been selected, it returns 0
	 */
	private int findNotUsed(){
		for(int i=0; i< used.length; i++){
			if(used[i]==false)
				return i;
		}
		resetMemory();
		return 0;
	}
	
	/**
	 * Method that checks if there is at least one choice of the Council Privileges that has not been selected yet.
	 * @return true if the check is positive (there is at least one choice of the Council Privileges that has not been 
	 * selected yet); false if the check is negative.
	 */
	private boolean isSomethingNotUsed(){
		for(int i=0; i< used.length; i++){
			if(used[i]==false)
				return true;
		}
		return false;
		
	}
	//Need these for testing
	public boolean[] getUsedArray(){
		return used;
	}
	
	public void setUsedToTrue(int position){
		used[position] = true;
	}
	
}
	
	

