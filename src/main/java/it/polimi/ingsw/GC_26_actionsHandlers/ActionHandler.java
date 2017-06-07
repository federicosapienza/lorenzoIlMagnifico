package it.polimi.ingsw.GC_26_actionsHandlers;




import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_player.Player;


//contains references to ActionsCheckerHandler and ActionsPerformerHandler that have the methods used both by firstActionHandler and SecondActionHandler
public abstract class ActionHandler {
	private GameElements gameElements;
	protected HarvestAndProductionHandler harvestAndProductionHandler;
	private ActionCheckerHandler checkerHandler;
	private ActionPerformerHandler performerHandler;
	
	public ActionHandler(GameElements gameElements, HarvestAndProductionHandler harvestAndProductionHandler){
		this.gameElements =gameElements;
		this.harvestAndProductionHandler=harvestAndProductionHandler;
		checkerHandler= new ActionCheckerHandler(gameElements, harvestAndProductionHandler);
		performerHandler = new ActionPerformerHandler(gameElements, harvestAndProductionHandler);
		
	}
	public GameElements getGameElements() {
		return gameElements;
	}
	
	public HarvestAndProductionHandler getHarvestAndProductionHandler() {
		return harvestAndProductionHandler;
	}
	
	public abstract boolean isPossible(Player player, Action action);
	public abstract void perform(Player player, Action action);
	
	public ActionCheckerHandler getCheckerHandler() {
		return checkerHandler;
	}
	
	public ActionPerformerHandler getPerformerHandler() {
		return performerHandler;
	}
	
	
	
	
	
}
