package it.polimi.ingsw.GC_26_controllers;


import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.GC_26_actionsHandlers.DiplomaticPrivilegesHandler;
import it.polimi.ingsw.GC_26_actionsHandlers.LeaderCardHandler;
import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_actionsHandlers.TradeHandler;
import it.polimi.ingsw.GC_26_actionsHandlers.VaticanReportHandler;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_server.Observer;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities_exceptions.IllegalActionException;
import it.polimi.ingsw.GC_26_utilities_exceptions.NotEnoughResourcesExceptions;

//the controller called whenever the game asks the client to perform a specific choice
public class ChoiceController implements Observer<Integer>{
	private Player player;
	private MainActionHandler handlers;
	private static final Logger LOG = Logger.getLogger(ActionController.class.getName());
	private static final String errorMessage= "Illegal action was going to be performed ";
	private static final String errorResources =  "not enough resources ";
	private static final String errorMessageResources= "Not enough resources: ending the turn";
	private static final String actionNotValid=  "action not valid";
	
	public ChoiceController(Player player, MainActionHandler handlers) {
		this.player=player;
		this.handlers= handlers;
	}
	
	public void update(Integer choice){ 
		PlayerStatus status; 
		synchronized (player) {
			status = player.getStatus();
		}
		if(status==PlayerStatus.SUSPENDED){
			restartingPlayer();
			return;}
		if(status == PlayerStatus.TRADING)
			 tradeController(choice);
		if(status == PlayerStatus.CHOOSINGPAYMENT)
			twoPaymentsController(choice);
		
		if(status == PlayerStatus.VATICANREPORTDECISION){
			vaticanReportController(choice);
			return;}
		
		if(status==PlayerStatus.TRADINGCOUNCILPRIVILEDGES )
			//player is trading diplomatic privileges
			diplomaticPrivilegesController( choice);
		if(status==PlayerStatus.ACTIONPERFORMED)
			//player Is asking to use Leader Card
			leaderCardsController(choice);

	}



	private void restartingPlayer() {
		player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, null, null));
		handlers.getGameElements().getGame().addPlayerNoMoreSuspended(player);
	}

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
				if(player.getStatus()==PlayerStatus.WAITINGHISTURN || player.getStatus()==PlayerStatus.SUSPENDED)// time out reached
					return;
				 //going back to previous state of the game, if time not expired and restarting the action that was interrputed
				if(player.getStatus()==PlayerStatus.TRADING)
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
			LOG.log( Level.FINE, errorResources, e);
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, errorMessageResources , null));
				//ends the turn
			}		
		}
	}
		
		private void twoPaymentsController(int choice) {
			try {
				handlers.getTwoPaymentHandler().perform(player, choice);
				
				synchronized (player) {
					if(player.getStatus()==PlayerStatus.WAITINGHISTURN || player.getStatus()==PlayerStatus.SUSPENDED)// time out reached
						return;
					 //going back to previous state of the game, if time not expired and restarting the action that was interrputed
					PlayerStatus previousStatus =player.getPreviousStatus(); 
					if(previousStatus ==PlayerStatus.PLAYING){
						if(player.getWarehouse().getCouncilPrivileges()>0){
							player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEDGES,GameParameters.getDiplomaticPrivilegesDescription()+ " ("
									+player.getWarehouse().getCouncilPrivileges()+ " left)", null));
							return;
						}
						else if(player.isThereAsecondaryAction()){
							player.setStatus(new Request(PlayerStatus.SECONDPLAY, null , null));
							return;}
						else 
							player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, null , null));
					}	
					}
			} catch(IllegalActionException e){
				LOG.log( Level.FINE, errorMessage, e);
				synchronized (player) {
					 player.backToPreviousStatusWithoutNotifying();
					player.setStatus(new Request(player.getStatus(), actionNotValid , null));

				}
			}
			catch(NotEnoughResourcesExceptions e){
				LOG.log( Level.FINE, errorResources, e);
				synchronized (player) {
					player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, "Not enough resources: ending the turn" , null));
					//ends the turn
				}		
			}
			
			
		}
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
						player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEDGES,GameParameters.getDiplomaticPrivilegesDescription()+ " ("
								+player.getWarehouse().getCouncilPrivileges()+ " left)", null));
						return;
					}
					if(player.isThereAsecondaryAction()){
						player.setStatus(new Request(PlayerStatus.SECONDPLAY, null , null));
						return;}
					else player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, null , null));;
				}
				}
			catch(IllegalActionException e){
				LOG.log( Level.FINE, errorMessage, e);
				synchronized (player) {
					player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, actionNotValid , null));

					
				}
			}
			catch(NotEnoughResourcesExceptions e1){
				LOG.log( Level.FINE, errorResources, e1);
				synchronized (player) {
					player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, errorMessageResources , null));
					//ends the turn
				}		
			}		
		}
		private void vaticanReportController(int choice) {
			try{
				player.setPlayerActive();
				VaticanReportHandler handler= handlers.getVaticanReportHandler();
				handler.perform(player, choice);
				
				handlers.getGameElements().getGame().vaticanReportNext(); // automatically ends the turn
		}
			
			catch ( IllegalActionException e ) {
				LOG.log( Level.FINE, errorMessage, e);
				synchronized (player) {
					player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, actionNotValid , null));
					handlers.getVaticanReportHandler().perform(player, 0); //not loop because if 0 status is not checked
				}
				handlers.getGameElements().getGame().vaticanReportNext(); // automatically ends the turn
				}	
			
		}
		
		
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
					player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEDGES,GameParameters.getDiplomaticPrivilegesDescription()+ " ("
							+player.getWarehouse().getCouncilPrivileges()+ " left)", null));
					return;
				}
				//otherwise the status does not change
				}
		}
		catch( IllegalActionException e){
			LOG.log( Level.FINE, errorMessage, e);
			player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED,actionNotValid, null));
		}
		}
}