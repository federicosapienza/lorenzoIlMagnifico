package it.polimi.ingsw.GC_26.controller;


import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.model.game.gameLogic.GameParameters;
import it.polimi.ingsw.GC_26.model.handlers.actionHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26.model.handlers.choiceHandlers.DiplomaticPrivilegesHandler;
import it.polimi.ingsw.GC_26.model.handlers.choiceHandlers.LeaderCardHandler;
import it.polimi.ingsw.GC_26.model.handlers.choiceHandlers.TradeHandler;
import it.polimi.ingsw.GC_26.model.handlers.choiceHandlers.VaticanReportHandler;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.model.player.PlayerStatus;
import it.polimi.ingsw.GC_26.utilities.exceptions.IllegalActionException;
import it.polimi.ingsw.GC_26.utilities.exceptions.NotEnoughResourcesExceptions;
import it.polimi.ingsw.GC_26.utilities.observer.Observer;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that represents the controller called whenever the game asks the client to perform a specific choice
 *
 */

public class ChoiceController implements Observer<Integer>{
	private Player player;
	private MainActionHandler handlers;
	private static final Logger LOG = Logger.getLogger(ActionController.class.getName());
	private static final String ERRORMESSAGE= "Illegal action was going to be performed ";
	private static final String ERRORRESOURCES =  "not enough resources ";
	private static final String ERRORMESSAGERESOURCES= "Not enough resources: ending the turn";
	private static final String ACTIONNOTVALID=  "action not valid";
	
	/**
	 * Constructor: it creates a controller for the choice selected by the player and the MainActionHandler to handle it
	 * @param player It's the player that selects the choice
	 * @param handlers It's the MainActionHandler that handles the choice
	 */
	public ChoiceController(Player player, MainActionHandler handlers) {
		this.player=player;
		this.handlers= handlers;
	}
	
	/**
	 * Method that updates the status of the player and performs the action that corresponds to the choice contained in the parameter
	 */
	public void update(Integer choice){ 
		PlayerStatus status; 
		synchronized (player) {
			status = player.getStatus();
		}
		if(status==PlayerStatus.SUSPENDED){
			restartingPlayer();
			return;
			}
		if(status == PlayerStatus.TRADING)
			 tradeController(choice);
		if(status == PlayerStatus.CHOOSINGPAYMENT)
			twoPaymentsController(choice);
		
		if(status == PlayerStatus.VATICANREPORTDECISION){
			vaticanReportController(choice);
			return;
		}
		
		if(status==PlayerStatus.TRADINGCOUNCILPRIVILEGES )
			//player is trading diplomatic privileges
			diplomaticPrivilegesController( choice);
		if(status==PlayerStatus.ACTIONPERFORMED)
			//player Is asking to use Leader Card
			leaderCardsController(choice);

	}

	/**
	 * Method that resets a suspended player to a player who is waiting his turn
	 */

	private void restartingPlayer() {
		player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, "Entering in the game again ", null));
		handlers.getGameElements().getGame().addPlayerNoMoreSuspended(player);
	}

	/**
	 * Method called when the player is trading. It checks if the trade chosen by the player is possible, and if it is, calls the handler that performs it 
	 * @param choice It's the choice for the trade selected by the player
	 */
	private void tradeController(int choice){
		try{
		TradeHandler handler= handlers.getTradeHandler();
		boolean flag = handler.isPossible(player, choice);
		if (!flag)  // the player is notified by the handler
			return;
		handler.perform(player, choice);
		synchronized (player) {
			if(player.getStatus()==PlayerStatus.WAITINGHISTURN || player.getStatus()==PlayerStatus.SUSPENDED)// time out reached
				return;
			 //going back to previous state of the game, if time not expired and restarting the action that was interrupted
			player.backToPreviousStatusWithoutNotifying();
			}
			//restarting the production
			handlers.getHarvestAndProductionHandler().continuePerforming(player);
			
			//going back from production: we must end his turn
			synchronized (player) {
				//going back to previous state of the game, if time not expired and restarting the action that was interrputed
				if(player.getStatus()==PlayerStatus.WAITINGHISTURN || player.getStatus()==PlayerStatus.SUSPENDED ||
						player.getStatus()==PlayerStatus.TRADING)// time out reached
					return;
				else player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, null , null));
			
			}
		}
		catch(IllegalActionException e){
			LOG.log( Level.FINE, "Illegal action was going to be performed ", e);
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.TRADING, null , null));
				//ends the turn
			}
		}
		catch(NotEnoughResourcesExceptions e){
			LOG.log( Level.FINE, ERRORRESOURCES, e);
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, ERRORMESSAGERESOURCES , null));
				//ends the turn
			}		
		}
	}
	
	/**
	 * Method called when the player is choosing the payment. It checks if the choice selected by the player is possible and 
	 * handles correctly all the possible cases generated by the choice of the player and his status 
	 * @param choice It's the choice selected by the player
	 */
	private void twoPaymentsController(int choice) {
		try {
			handlers.getTwoPaymentHandler().perform(player, choice);
			synchronized (player) {
				if(player.getStatus()==PlayerStatus.WAITINGHISTURN || player.getStatus()==PlayerStatus.SUSPENDED)// time out reached
					return;
				//going back to previous state of the game, if time not expired and restarting the action that was interrupted
				PlayerStatus previousStatus =player.getPreviousStatus(); 
				if(previousStatus ==PlayerStatus.PLAYING){
					if(player.getWarehouse().getCouncilPrivileges()>0){
						player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEGES,GameParameters.getDiplomaticPrivilegesDescription()+ 
								" ("+player.getWarehouse().getCouncilPrivileges()+ " left)", null));
						return;
					}
					else if(player.isThereAsecondaryAction()){
						player.setStatus(new Request(PlayerStatus.SECONDPLAY, null , null));
						return;
					}
					else 
						player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, null , null));
				}	
			}
		} catch(IllegalActionException e){
			LOG.log(Level.FINE, ERRORMESSAGE, e);
			synchronized (player) {
				player.backToPreviousStatusWithoutNotifying();
				player.setStatus(new Request(player.getStatus(), ACTIONNOTVALID , null));
			}
		}
		catch(NotEnoughResourcesExceptions e){
			LOG.log( Level.FINE, ERRORRESOURCES, e);
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, "Not enough resources: ending the turn" , null));
				//ends the turn
			}		
		}
			
			
	}
	
	/**
	 * Method called when the player is trading the Council Privileges. It checks if the choice selected by the player is possible and 
	 * handles correctly all the possible cases generated by the choice of the player and his status 
	 * @param choice
	 */
	private void diplomaticPrivilegesController(int choice) {
		try{
			DiplomaticPrivilegesHandler handler= handlers.getDiplomaticPrivilegesHandler();
			int temp=choice-1;
			boolean flag = handler.isPossible(player, temp);  //because player will send a value >=1, but we want it starting from zero
			if (!flag)  // the player is notified by the handler
				return;
			handler.perform(player, temp);
			synchronized (player) {
				if(player.getStatus()==PlayerStatus.WAITINGHISTURN || player.getStatus()==PlayerStatus.SUSPENDED)// time out reached
					return;
				 //going back to previous state of the game, if time not expired and restarting the action that was interrputed
				if(player.getWarehouse().getCouncilPrivileges()>0){
					player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEGES,GameParameters.getDiplomaticPrivilegesDescription()+ " ("
							+player.getWarehouse().getCouncilPrivileges()+ " left)", null));
					return;
				}
				if(player.isThereAsecondaryAction()){
					player.setStatus(new Request(PlayerStatus.SECONDPLAY, null , null));
					return;}
				else player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, null , null));
			}
			}
		catch(IllegalActionException e){
			LOG.log( Level.FINE, ERRORMESSAGE, e);
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, ACTIONNOTVALID , null));

				
			}
		}
		catch(NotEnoughResourcesExceptions e1){
			LOG.log( Level.FINE, ERRORRESOURCES, e1);
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, ERRORMESSAGERESOURCES , null));
				//ends the turn
			}		
		}		
	}
	
	/**
	 * Method called when the player is in the Vatican Report phase. Depending on the choice the player can decide whether to support 
	 * the Church or not: if he doesn't support it, he is excommunicated and is affected by the negative effects of the corresponding
	 * Excommunication Tile of the current period;
	 * if he decides to support the Church, he must pay all the Faith Points of the current period and he avoids the Excommunication, 
	 * getting also the bonus Victory Points that correspond to the amount of the Faith Points which he has just paid.  
	 * @param choice It's the choice of the player that indicates if he wants to support the Church or not: 0 if he doesn't want to
	 * support it, any other value to support it 
	 */
	//it ' synchronized because client and timertask may try to perform this action at the same time
	private synchronized void vaticanReportController(int choice) {
		//ensuring timer and client do no not both perform this action 
		if(player.getStatus()==PlayerStatus.WAITINGHISTURN)
			return;
		try{
			player.setPlayerActive();
			VaticanReportHandler handler= handlers.getVaticanReportHandler();
			handler.perform(player, choice);
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, null , null));
			}

			handlers.getGameElements().getGame().vaticanReportNext(); // automatically ends the turn
	}
		
		catch ( IllegalActionException e ) {
			LOG.log( Level.FINE, ERRORMESSAGE, e);
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, ACTIONNOTVALID , null));
				handlers.getVaticanReportHandler().perform(player, 0); //not loop because if 0 status is not checked
			}
			player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, null, null));
			handlers.getGameElements().getGame().vaticanReportNext(); // automatically ends the turn
			}	
		
	}
	
	/**
	 * Method called when the player has performed an action and wants to use a Leader Card that he owns. 
	 * It checks if the choice selected by the player is possible and handles correctly all the possible cases generated by the choice 
	 * of the player and his status
	 * @param choice It's the choice selected by the player
	 */
	private void leaderCardsController(Integer choice) {
		
	try{
		LeaderCardHandler handler= handlers.getLeaderCardHandler(); 
		boolean flag = handler.isPossible(player, choice); //because player will send a value >=1, but we want it starting from zero
		if (!flag)  // the player is notified by the handler
			return;
		handler.perform(player, choice);
		synchronized (player) {
			if(player.getStatus()==PlayerStatus.WAITINGHISTURN || player.getStatus()==PlayerStatus.SUSPENDED)// time out reached
				return;
			 //going back to previous state of the game, if time not expired and restarting the action that was interrupted
			if(player.getWarehouse().getCouncilPrivileges()>0){
				player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEGES,GameParameters.getDiplomaticPrivilegesDescription()+ " ("
						+player.getWarehouse().getCouncilPrivileges()+ " left)", null));
				return;
			}
			//otherwise the status does not change
			player.notifyObservers(new Request(player.getStatus(),null  , null));

			}
	}
	catch( IllegalActionException e){
		LOG.log( Level.FINE, ERRORMESSAGE, e);
		player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED,ACTIONNOTVALID, null));
	}
	}
}