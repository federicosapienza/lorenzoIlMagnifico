package it.polimi.ingsw.GC_26.model.actionHandlers;


import it.polimi.ingsw.GC_26.messages.action.Action;
import it.polimi.ingsw.GC_26.messages.action.ActionNotification;
import it.polimi.ingsw.GC_26.model.game.gameComponents.board.BoardZone;
import it.polimi.ingsw.GC_26.model.game.gameComponents.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.game.gameLogic.GameElements;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.utilities.exceptions.IllegalActionException;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * Extending class of action handler that represents the handler for the first action.
 * Synchronization is ensured by Controllers which call methods of this class, only if the player is allowed
 */

public class FirstActionHandler extends ActionHandler{
	
	/**
	 * Constructor: it creates a handler for the first action that involves the game elements and the handler for Harvest
	 * and Production, expressed by the parameters
	 * @param gameElements the game elements involved in the first action
	 * @param handler It's the handler for Harvest and Production
	 */
	public FirstActionHandler(GameElements gameElements, HarvestAndProductionHandler handler){
		super(gameElements, handler);
	}
	
	/*@Override
	public void startAction(Player player, Action action) {
		
	}  */	  

	/**
	 * Method that checks if the action that the players wants to perform can be done according to the rules
	 * @param player It's the player who wants to perform the action
	 * @param action It's the action that the player wants to perform
	 */
	public boolean isPossible(Player player, Action action) throws IllegalArgumentException{
		
		
		player.setTemporaryWarehouse();  // prepares the action
		
		if(!super.getCheckerHandler().areServantsEnough(player, action))
			return false;
		player.getTestWarehouse().spendResources(ResourcesOrPoints.newResources(0,action.getServantsUsed(),0,0));
		
		//takes the family member used and checks is free
		FamilyMember familyMemberUsed = player.getFamilyMembers().getfamilyMember(action. getFamilyMemberColour());
		
		if(!super.getCheckerHandler().isFamilyMemberFree(familyMemberUsed, player))
			return false;
		
		//calling the right checker
		else if(action.getZone()==BoardZone.BUILDINGTOWER  || action.getZone() == BoardZone.CHARACTERTOWER || 
					action.getZone()==BoardZone.TERRITORYTOWER || action.getZone()==BoardZone.VENTURETOWER){
				return super.towerIsPossible(player, familyMemberUsed, action);
		}
		else if(action.getZone()==BoardZone.MARKET){
				return super.marketIsPossible(player, familyMemberUsed, action);
		}
		else if(action.getZone()==BoardZone.COUNCILPALACE){
				return super.councilPalaceIsPossible(player, familyMemberUsed, action);
		}
		else if(action.getZone()==BoardZone.HARVEST){
				return super.harvestIsPossible(player, familyMemberUsed, action);
		}
		else if(action.getZone()==BoardZone.PRODUCTION){
				return super.productionIsPossible(player, familyMemberUsed, action);
			
		}
		else {
				throw new IllegalActionException();
		}
			
	}
				

	/**
	 * Method called to perform the Action chosen by the player contained in the parameters
	 */
	@Override
	 public void perform(Player player, Action action) {
			//spends the servants
			player.getWarehouse().spendResources(ResourcesOrPoints.newResources(0,action.getServantsUsed(),0,0));
			//takes the family member and sets it used
			FamilyMember familyMemberUsed = player.getFamilyMembers().getfamilyMember(action.getFamilyMemberColour()); 
			familyMemberUsed.setUsed();
		
		//calling the right performer
			if(action.getZone()==BoardZone.BUILDINGTOWER  || action.getZone() == BoardZone.CHARACTERTOWER || 
					action.getZone()==BoardZone.TERRITORYTOWER || action.getZone()==BoardZone.VENTURETOWER)
				super.towerPerform(player, familyMemberUsed, action);			
			if(action.getZone()==BoardZone.MARKET)
				super.marketPerform(player, familyMemberUsed, action);
			if(action.getZone()== BoardZone.COUNCILPALACE)
				super.councilPalacePerform(player, familyMemberUsed);
			if(action.getZone()==BoardZone.HARVEST)
				super.harvestPerform(player, familyMemberUsed, action);
			if(action.getZone()==BoardZone.PRODUCTION)
				super.productionPerform(player, familyMemberUsed, action);
			
			//notify the players
			getGameElements().notifyObservers(new ActionNotification(player.getName(), action));
		
	 }
}
	
	 