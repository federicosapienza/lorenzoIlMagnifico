package it.polimi.ingsw.GC_26_actionsHandlers;


import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

// synchronization is ensured by Controllers which calls method of this function, only if the player is allowed
public class FirstActionHandler extends ActionHandler{
	
	
	public FirstActionHandler(GameElements gameElements){
		super(gameElements);
	}
	
	@Override
	public void startAction(Player player, Action action) {
		player.setPlayerActive();
		//cambiare già nel controller lo stato del player
		
		try {
			Boolean flag = isPossible(player, action);
			// if action not possible player is notified in IsPossible and linked methods
			if(!flag)
				perform(player,  action);
			if(player.getStatus().isThereAsecondaryAction())
				synchronized (player.getStatus()) {
					player.setStatus(PlayerStatus.SECONDPLAY);
				}
			else synchronized (player.getStatus()) {
				player.setStatus(PlayerStatus.ACTIONPERFORMED);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			player.setStatus(PlayerStatus.PLAYING);
			player.notifyObservers("action not valid");
			
		}
		catch ( IllegalStateException e1 ) {
			e1.printStackTrace();
			player.setStatus(PlayerStatus.PLAYING);
			player.notifyObservers("action not valid");
		}
	}	  
		//if(flag)
			//pay()	

	
	private boolean isPossible(Player player, Action action ) throws IllegalArgumentException{
		//spostare sopra
		//if(action.getPlayer().getStatus() != PlayerStatus.PLAYING)  //TODO la spostiamo in controller
			//TODO notificare:forse va lanciata un' eccezione
		//	return false;
		
		//mettere try catch: spiegare che progrmmazione molto difensiva
		
		player.setTemporaryWarehouse();  // prepares the action
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
				new IllegalArgumentException();
				throw new IllegalArgumentException();
			}
		}
				
 
		
		
	// also used in secondary action.
	 
	 
/////////////////////////////////////////////////////////////////////
	 private void perform(Player player, Action action) {
			try{
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
	 
		} catch (IllegalArgumentException e) {
			player.notifyObservers("Action not valid. retry");  // defensive catch.
			player.setStatus(PlayerStatus.WAITINGHISTURN);
			e.printStackTrace();
		}
	 }
}
	
	 