package it.polimi.ingsw.GC_26_actionsHandlers;


import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

// synchronization is ensured by Controllers which calls method of this function, only if the player is allowed
public class FirstActionHandler extends ActionHandler{
	
	
	public FirstActionHandler(GameElements gameElements, HarvestAndProductionHandler handler){
		super(gameElements, handler);
	}
	
	/*@Override
	public void startAction(Player player, Action action) {
		
	}  */	  

	
	public boolean isPossible(Player player, Action action ) throws IllegalArgumentException{
		
		//spiegare che programmazione molto difensiva
		
		player.setTemporaryWarehouse();  // prepares the action
		
		//enough servants?
		if(!player.getTestWarehouse().areResourcesEnough(ResourcesOrPoints.newResources(0,action.getServantsUsed(),0,0))){
			player.notifyObservers("Not enough servants");
			return false;}
		player.getTestWarehouse().spendResources(ResourcesOrPoints.newResources(0,action.getServantsUsed(),0,0));
		
		//takes the family member used and checks is free
		FamilyMember familyMemberUsed = player.getFamilyMembers().getfamilyMember(action. getFamilyMemberColour());
		if(!familyMemberUsed.isFree()){
			player.notifyObservers("Family member not free");
			return false;
		}
		
		//calling the right checker
			if(action.getZone()==BoardZone.BUILDINGTOWER  || action.getZone() == BoardZone.CHARACTERTOWER || 
					action.getZone()==BoardZone.TERRITORYTOWER || action.getZone()==BoardZone.VENTURETOWER){
				return towerIsPossible(player, familyMemberUsed, action);
			}
			if(action.getZone()==BoardZone.MARKET){
					return marketIsPossible(player, familyMemberUsed, action);
			}
			if(action.getZone()==BoardZone.COUNCILPALACE){
				return councilPalaceIsPossible(player, familyMemberUsed, action);
			}
			if(action.getZone()==BoardZone.HARVEST){
				return harvestIsPossible(player, familyMemberUsed, action);
			}
			if(action.getZone()==BoardZone.PRODUCTION){
				
			 return productionIsPossible(player, familyMemberUsed, action);
			}
			else {
				throw new IllegalArgumentException();
			}
		}
				
 
		
		
	// also used in secondary action.
	 
	 
/////////////////////////////////////////////////////////////////////
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
				towerPerform(player, familyMemberUsed, action);			
			if(action.getZone()==BoardZone.MARKET)
				marketPerform(player, familyMemberUsed, action);
			if(action.getZone()== BoardZone.COUNCILPALACE)
				councilPalacePerform(player, familyMemberUsed, action);
			if(action.getZone()==BoardZone.HARVEST)
				harvestPerform(player, familyMemberUsed, action);
			if(action.getZone()==BoardZone.PRODUCTION)
				productionPerform(player, familyMemberUsed, action);
		
	 }
}
	
	 