package it.polimi.ingsw.GC_26.model.actionHandlers;


import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.messages.action.Action;
import it.polimi.ingsw.GC_26.messages.action.ActionNotification;
import it.polimi.ingsw.GC_26.model.game.gameComponents.board.BoardZone;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.game.gameLogic.GameElements;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.utilities.exceptions.IllegalActionException;

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
			return false;
		}
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
		else throw new IllegalActionException();
		
	
	}
	@Override
	public void perform(Player player, Action action) {
		//spends the servants
		player.getWarehouse().spendResources(ResourcesOrPoints.newResources(0,action.getServantsUsed(),0,0));
		//calling the right performer
		if(action.getZone()==BoardZone.BUILDINGTOWER  || action.getZone() == BoardZone.CHARACTERTOWER || 
				action.getZone()==BoardZone.TERRITORYTOWER || action.getZone()==BoardZone.VENTURETOWER){
			super.towerPerform(player, null, action);
		}
		
		//adding the servants to the value and calling Harvest
		if(action.getZone()== BoardZone.HARVEST){
			HarvestAndProductionHandler handler = super.getHarvestAndProductionHandler();
			int actionValue= action.getServantsUsed()  +player.getPermanentModifiers().getActionModifier(BoardZone.HARVEST); //permanent Effect
				if(actionValue<1)  //checking if action value is valid
					throw new IllegalArgumentException();
		
			harvestAndProductionHandler.startHarvest(player, actionValue);	
			handler.startHarvest(player, player.getSecondactionValue()+action.getServantsUsed());
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
			super.councilPalacePerform(player, null);
			
		
		
		
		//notify the players of the action
		getGameElements().notifyObservers(new ActionNotification(player.getName(), action));

	}
	
	
}
