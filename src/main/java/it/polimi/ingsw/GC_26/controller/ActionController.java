package it.polimi.ingsw.GC_26.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.messages.action.Action;
import it.polimi.ingsw.GC_26.model.actionHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26.model.game.gameLogic.GameParameters;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.model.player.PlayerStatus;
import it.polimi.ingsw.GC_26.utilities.exceptions.IllegalActionException;
import it.polimi.ingsw.GC_26.utilities.exceptions.NotEnoughResourcesExceptions;
import it.polimi.ingsw.GC_26.utilities.observer.Observer;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the controller for every action
 *
 */
public class ActionController implements Observer<Action>{  
	private Player player;
	private MainActionHandler handlers;
	private static final Logger LOG = Logger.getLogger(ActionController.class.getName());
	private static final String errorMessage= "Illegal action was going to be performed ";
	private static final String errorRequest = "not valid action";

	/**
	 * Constructor: it creates an ActionController for the player and the MainActionHandler contained in the parameters
	 * @param player the player to control
	 * @param handlers the handlers to control
	 */
	public ActionController(Player player, MainActionHandler handlers) {
		if(player==null || handlers==null)
			throw new NullPointerException("player or handlers are null"); 
		this.player=player;
		this.handlers= handlers;
	}
	
	/**
	 * Method that analyze the action contained in the parameter and checks if it is everything ok according with 
	 * the characteristics of the player
	 */
	public void update(Action action){ 
		if (action == null) {
			throw new NullPointerException("action is null");
		}
		
		PlayerStatus status;
	
		synchronized (player) {
			 status= player.getStatus();
		}
		
		if(status == PlayerStatus.PLAYING)
			 firstActionController(action);
		if(status== PlayerStatus.SECONDPLAY)
			secondActionController(action);
	}
	
	/**
	 * Method that controls if the action contained in the parameter is ok as a first action
	 * @param action It's the action that has to be checked as a first action
	 */
	private void firstActionController(Action action){
		if(action==null)
			throw new NullPointerException(); 
		player.setPlayerActive();
		try {
			Boolean flag = handlers.getFirstActionHandler().isPossible(player, action);
			// if action not possible player is notified in IsPossible and linked methods
			if(!flag)
				return;
			
			handlers.getFirstActionHandler().perform(player, action);
			
	
			synchronized (player) {
				if(player.getStatus()==PlayerStatus.WAITINGHISTURN || player.getStatus()==PlayerStatus.SUSPENDED)// time out reached
					return;
				if(player.getStatus()==PlayerStatus.CHOOSINGPAYMENT || player.getStatus()==PlayerStatus.TRADING)  //server waits for action
					return;
				if(player.getWarehouse().getCouncilPrivileges()>0){
					player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEGES,GameParameters.getDiplomaticPrivilegesDescription()+ " ("
							+player.getWarehouse().getCouncilPrivileges()+ " left)", null));
					return;
				}
				if(player.isThereAsecondaryAction()){
					player.setStatus(new Request(PlayerStatus.SECONDPLAY, "Perform your allowed second action" , null));
					return;
				}
				else 
					player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, null , null));
			} 
		} catch (NotEnoughResourcesExceptions e) {
			LOG.log( Level.FINE, "not enough resources ", e);	

			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, "not valid action: resources ended under zero. ending turn" , null));
			}
		} catch (IllegalStateException e1 ) {
			LOG.log( Level.FINE, "Illegal action was going to be performed ", e1);	
			synchronized (player) {
				LOG.log( Level.FINE, errorMessage, e1);	
				player.setStatus(new Request(PlayerStatus.PLAYING, errorRequest , null));
			}
		}
		catch (IllegalActionException e2 ) {
			LOG.log( Level.FINE, errorMessage, e2);	

			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.PLAYING, errorRequest , null));
		}
	}
	}
	
	/**
	 * Method that controls the correctness of the action contained in the parameter as a secondary action
	 * @param action It's the action that has to be checked as a secondary action
	 */
	private void secondActionController(Action action){
		if (action == null) {
			throw new NullPointerException("action is null");
		}
		try {
			Boolean flag = handlers.getSecondActionHandler().isPossible(player, action);
			// if action not possible player is notified in IsPossible and linked methods
			if(!flag)
				return;

			handlers.getSecondActionHandler().perform(player, action);
			player.resetSecondAction();	 
			synchronized (player) {
				if(player.getStatus()==PlayerStatus.CHOOSINGPAYMENT || player.getStatus()==PlayerStatus.TRADING)  //server waits for action
					return;
				if(player.getStatus()==PlayerStatus.WAITINGHISTURN || player.getStatus()==PlayerStatus.SUSPENDED)// time out reached
					return;
				if(player.getWarehouse().getCouncilPrivileges()>0){
					player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEGES,GameParameters.getDiplomaticPrivilegesDescription()+ " ("
							+player.getWarehouse().getCouncilPrivileges()+ " left)", null));
					return;
				}
				else{
					player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, null , null));
				}	
			}
			} catch (NotEnoughResourcesExceptions e) {
				LOG.log( Level.FINE, "not enough resources ", e);	
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, "not valid action: resources ended under zero. ending turn" , null));
			}
		} 
		catch (IllegalActionException e1 ) {
			LOG.log( Level.FINE, errorMessage, e1);	
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.PLAYING, errorRequest , null));
			}
		}
				
		catch (IllegalStateException e2 ) {
			LOG.log( Level.FINE, errorMessage, e2);	
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.SECONDPLAY, null , null));

			}
		}
	}

	
}	
	
