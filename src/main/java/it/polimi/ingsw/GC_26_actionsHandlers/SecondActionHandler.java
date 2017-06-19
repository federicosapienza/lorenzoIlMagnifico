package it.polimi.ingsw.GC_26_actionsHandlers;


import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.ActionNotification;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class SecondActionHandler extends ActionHandler{

	public SecondActionHandler(GameElements gameElements , HarvestAndProductionHandler handler) {
		super(gameElements,handler);
	}

	@Override
	public boolean isPossible(Player player, Action action) {
		//BoardZone correspondence is checked here, position correspondence in ActionCheckerHandler 
		
		//enough servants?
		if(!player.getTestWarehouse().areResourcesEnough(ResourcesOrPoints.newResources(0,action.getServantsUsed(),0,0))){
			player.notifyObservers(new Request(player.getStatus(),"Not enough servants", null));
			return false;}
		player.getTestWarehouse().spendResources(ResourcesOrPoints.newResources(0,action.getServantsUsed(),0,0));
		
		//gets the info about second action from PlayerStatus and checks is valid
		Action secondAction = player.getTypeOfSecondaryAction();
		if(secondAction.getZone()==null){ //==means take card from everywhere (i.e Badess effect): look at SetSecondActionEffect
			//anyway we must check is a tower Action. 
			if(! (action.getZone()==BoardZone.BUILDINGTOWER  || action.getZone() == BoardZone.CHARACTERTOWER || 
			action.getZone()==BoardZone.TERRITORYTOWER || action.getZone()==BoardZone.VENTURETOWER)){
				player.notifyObservers(new Request(player.getStatus(),"second action not corresponds to the allowed one", null));
				return false;
			}
				
		}
		else if(secondAction.getZone()!=action.getZone()){
			player.notifyObservers(new Request(player.getStatus(),"second action not corresponds to the allowed one", null));
			return false;
		}
		
		
			// calls the right checker	
		if(action.getZone()==BoardZone.BUILDINGTOWER  || action.getZone() == BoardZone.CHARACTERTOWER || 
					action.getZone()==BoardZone.TERRITORYTOWER || action.getZone()==BoardZone.VENTURETOWER)
				return super.towerIsPossible(player, null , action);
		//not present in standard game. anyway implemented for flexibility:
		if(action.getZone()==BoardZone.MARKET)
			return super.marketIsPossible(player, null, action);
		//not present in standard game. anyway implemented for flexibility:
		if(action.getZone()==BoardZone.COUNCILPALACE)
			return super.councilPalaceIsPossible(player, null, action);
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
				action.getZone()==BoardZone.TERRITORYTOWER || action.getZone()==BoardZone.VENTURETOWER){
			super.towerPerform(player, null, action);
			return;
		}
		
		//adding the servants to the value and calling Harvest
		if(action.getZone()== BoardZone.HARVEST){
			HarvestAndProductionHandler handler = super.getHarvestAndProductionHandler();
			int actionValue= action.getServantsUsed()  +player.getPermanentModifiers().getActionModifier(BoardZone.HARVEST); //permanent Effect
				if(actionValue<1)  //checking if action value is valid
					throw new IllegalArgumentException();
		
			harvestAndProductionHandler.startHarvest(player, actionValue);	
			handler.startHarvest(player, player.getSecondactionValue()+action.getServantsUsed());
			return;
		}
		if(action.getZone()== BoardZone.PRODUCTION){
			HarvestAndProductionHandler handler = super.getHarvestAndProductionHandler();
			int actionValue= action.getServantsUsed() +player.getPermanentModifiers().getActionModifier(BoardZone.PRODUCTION); //permanent Effect
				if(actionValue<1) //checking if action value is valid
					throw new IllegalArgumentException();
			harvestAndProductionHandler.startProduction(player, actionValue);	
			handler.startProduction(player, player.getSecondactionValue()+action.getServantsUsed());
		}
				
		//they do not exists in standard game but they were put for flexibility
		if(action.getZone()==BoardZone.MARKET)
			super.marketPerform(player, null, action);
			
		if(action.getZone()== BoardZone.COUNCILPALACE)
			super.councilPalacePerform(player, null, action);
			
		
		
		
		//notify the players of the action
		getGameElements().notifyObservers(new ActionNotification(player.getName(), action));

	}
	
	
}
