package it.polimi.ingsw.GC_26_actionsHandlers;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class SecondActionHandler extends ActionHandler{

	public SecondActionHandler(GameElements gameElements) {
		super(gameElements);
	}

	@Override
	public boolean isPossible(Player player, Action action) {
		//enough servants?
		if(!player.getTestWarehouse().areResourcesEnough(ResourcesOrPoints.newResources(0,action.getServantsUsed(),0,0))){
			player.notifyObservers("Not enough servants");
			return false;}
		player.getTestWarehouse().spendResources(ResourcesOrPoints.newResources(0,action.getServantsUsed(),0,0));
		
		//gets the info about second action from PlayerStatus and checks is valid
		Action secondAction = player.getTypeOfSecondaryAction();
		if(secondAction.getZone()!=action.getZone()){
			player.notifyObservers("second action not corresponds to the allowed one");
			return false;
		}
			// calls the right checker	
		if(action.getZone()==BoardZone.BUILDINGTOWER  || action.getZone() == BoardZone.CHARACTERTOWER || 
					action.getZone()==BoardZone.TERRITORYTOWER || action.getZone()==BoardZone.VENTURETOWER)
				return towerIsPossible(player, null , action);
		//not present in standard game. anyway implemented for flexibility:
		if(action.getZone()==BoardZone.MARKET)
			return marketIsPossible(player, null, action);
		//not present in standard game. anyway implemented for flexibility:
		if(action.getZone()==BoardZone.COUNCILPALACE)
			return councilPalaceIsPossible(player, null, action);
		if(action.getZone()==BoardZone.HARVEST ||action.getZone()==BoardZone.PRODUCTION)
				return true;
		else throw new IllegalArgumentException();
		
	
	}
	@Override
	public void perform(Player player, Action action) {
		//spends the servants
		player.getWarehouse().spendResources(ResourcesOrPoints.newResources(0,action.getServantsUsed(),0,0));
		//calling the right performer
		if(action.getZone()==BoardZone.BUILDINGTOWER  || action.getZone() == BoardZone.CHARACTERTOWER || 
				action.getZone()==BoardZone.TERRITORYTOWER || action.getZone()==BoardZone.VENTURETOWER)
			towerPerform(player, null, action);
		
		
		//adding the servants to the value and calling Harvest
		if(action.getZone()== BoardZone.HARVEST){
			HarvestAndProductionHandler handler =new HarvestAndProductionHandler(super.getGameElements());
			handler.startHarvest(player, player.getSecondactionValue()+action.getServantsUsed());
		}
		if(action.getZone()== BoardZone.PRODUCTION){
			HarvestAndProductionHandler handler =new HarvestAndProductionHandler(super.getGameElements());
			handler.startProduction(player, player.getSecondactionValue()+action.getServantsUsed());
		}		
		//they do not exists in standard game but they were put for flexibility
		if(action.getZone()==BoardZone.MARKET)
			marketPerform(player, null, action);
		if(action.getZone()== BoardZone.COUNCILPALACE)
			councilPalacePerform(player, null, action);
		
		else throw new IllegalArgumentException();
	}
	
	
}
