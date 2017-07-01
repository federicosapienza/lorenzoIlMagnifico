package it.polimi.ingsw.GC_26_controllers;

import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_server.Observer;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities_exceptions.IllegalActionException;
import it.polimi.ingsw.GC_26_utilities_exceptions.NotEnoughResourcesExceptions;

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
	Player player;
	MainActionHandler handlers;
	
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
					player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEDGES,GameParameters.getDiplomaticPrivilegesDescription()+ " ("
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
			
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, "not valid action: resources ended under zero. ending turn" , null));
			}
		} catch (IllegalStateException e1 ) {
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.PLAYING, "not valid action" , null));
			}
		}
		catch (IllegalActionException e1 ) {
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.PLAYING, "not valid action" , null));
		}
	}
	}
	
	/**
	 * Method that controls if the action contained in the parameter is ok as a secondary action
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
					player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEDGES,GameParameters.getDiplomaticPrivilegesDescription()+ " ("
							+player.getWarehouse().getCouncilPrivileges()+ " left)", null));
					return;
				}
				else{
					player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, null , null));
				}	
			}
			} catch (NotEnoughResourcesExceptions e) {
			
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, "not valid action: resources ended under zero. ending turn" , null));
			}
		} 
		catch (IllegalActionException e1 ) {
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.PLAYING, "not valid action" , null));
			}
		}
				
		catch (IllegalStateException e1 ) {
			e1.printStackTrace();
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.SECONDPLAY, null , null));

			}
		}
	}

	
}	
	
