package it.polimi.ingsw.GC_26_actionsHandlers;




import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_player.Player;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the abstract class that represents the handler for every action.
 * It contains references to ActionCheckerHandler and ActionPerformerHandler that have the methods used both
 * by firstActionHandler and SecondActionHandler
 *
 */


public abstract class ActionHandler {
	private GameElements gameElements;
	protected HarvestAndProductionHandler harvestAndProductionHandler;
	private ActionCheckerHandler checkerHandler;
	private ActionPerformerHandler performerHandler;
	
	/**
	 * Constructor: it creates an action handler with the same game elements and harvest and production handler expressed
	 * in the parameters and 2 new handlers: the first to check to possibility to do an action, 
	 * the second to allow a player to do the action if it is possible. Both are created with the same parameters as above. 
	 * @param gameElements the game elements of the game
	 * @param harvestAndProductionHandler the handler for harvest and production 
	 */
	public ActionHandler(GameElements gameElements, HarvestAndProductionHandler harvestAndProductionHandler){
		this.gameElements = gameElements;
		this.harvestAndProductionHandler = harvestAndProductionHandler;
		checkerHandler = new ActionCheckerHandler(gameElements, harvestAndProductionHandler);
		performerHandler = new ActionPerformerHandler(gameElements, harvestAndProductionHandler);
		
	}
	
	/**
	 * Getter method that returns the game elements
	 * @return the game elements
	 */
	public GameElements getGameElements() {
		return gameElements;
	}
	
	/**
	 * Getter method that returns the handler for harvest and production
	 * @return the handler for harvest and production
	 */
	public HarvestAndProductionHandler getHarvestAndProductionHandler() {
		return harvestAndProductionHandler;
	}
	
	/**
	 * Method that verifies if a player can perform an action or not
	 * @param player It's the player who wants to perform the action
	 * @param action It's the action of the player that has to be verified
	 * @return true if the player has the right to perform the action; false if he cannot do the action
	 */
	public abstract boolean isPossible(Player player, Action action);
	
	/**
	 * Method used to allow a player to perform an action
	 * @param player It's the player who wants to perform an action
	 * @param action It's the action that the player wants to perform
	 */
	public abstract void perform(Player player, Action action);
	
	/**
	 * Getter method that returns the handler that checks the possibility of an action
	 * @return the handler that checks the possibility of an action
	 */
	public ActionCheckerHandler getCheckerHandler() {
		return checkerHandler;
	}
	
	/**
	 * Getter method that returns the handler that allows a player to perform an action
	 * @return the handler that allows a player to perform an action
	 */
	
	public ActionPerformerHandler getPerformerHandler() {
		return performerHandler;
	}
	
	
	
	
	
}
