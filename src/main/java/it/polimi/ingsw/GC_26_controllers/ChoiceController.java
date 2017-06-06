package it.polimi.ingsw.GC_26_controllers;

import it.polimi.ingsw.GC_26_actionsHandlers.DiplomaticPrivilegesHandler;
import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_actionsHandlers.TradeHandler;
import it.polimi.ingsw.GC_26_actionsHandlers.VaticanReportHandler;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_serverView.Observer;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

//the controller called whenever the game asks the client to perform a specific choice
public class ChoiceController implements Observer<Integer>{
	Player player;
	MainActionHandler handlers;
	
	public ChoiceController(Player player, MainActionHandler handlers) {
		this.player=player;
		this.handlers= handlers;
	}
	
	public void update(Integer choice){ 
		PlayerStatus status; 
		synchronized (player) {
			status = player.getStatus();
		}
		if(status == PlayerStatus.TRADING)
			 tradeController(choice);
		if(status == PlayerStatus.CHOOSINGPAYMENT)
			twoPaymentsController(choice);
		
		if(status == PlayerStatus.VATICANREPORTDECISION)
			vaticanReportController(choice);
		
		if(status==PlayerStatus.ACTIONPERFORMED || status== PlayerStatus.SECONDPLAY 
				&& player.getWarehouse().areResourcesEnough(ResourcesOrPoints.newPoints(0, 0, 0, 1)))
			//player is trading diplomatic privileges
			diplomaticPrivilegesController( choice);

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
			player.backToPreviousStatus();
			//restarting the production
			handlers.getHarvestAndProductionHandler().notifyAll();
			
			}
		
		
		
		}
		catch(IllegalArgumentException e){
			e.printStackTrace();
			synchronized (player) {
				player.setStatus(PlayerStatus.WAITINGHISTURN); //ends the turn
				player.notifyObservers("action not valid");
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
						player.backToPreviousStatus();
						handlers.getFirstActionHandler().notifyAll();  //TODO controllare se funziona
					}
					if(previousStatus ==PlayerStatus.SECONDPLAY){
						player.backToPreviousStatus();
						handlers.getSecondActionHandler().notifyAll(); //TODO controllare se funziona
					}	
					}
			} catch(IllegalArgumentException e){
				e.printStackTrace();
				synchronized (player) {
					player.setStatus(PlayerStatus.WAITINGHISTURN); //ends his turn
					player.notifyObservers("action not valid");
				}
			}		
		}
		private void diplomaticPrivilegesController(int choice) {
			try{
				DiplomaticPrivilegesHandler handler= handlers.getDiplomaticPrivilegesHandler();
				boolean flag = handler.isPossible(player, choice);
				if (!flag)  // the player is notified by the handler
					return;
				handler.perform(player, choice);
		}
			catch(IllegalArgumentException e){
				e.printStackTrace();
				synchronized (player) {
					player.setStatus(PlayerStatus.WAITINGHISTURN); //ends his turn
					player.notifyObservers("action not valid");
				}
			}
			catch ( IllegalStateException e1 ) {
				e1.printStackTrace();
				synchronized (player) {
					player.setStatus(PlayerStatus.WAITINGHISTURN);
					player.notifyObservers("action not valid");
				}
				}	
				
			}
		private void vaticanReportController(int choice) {
			try{
				VaticanReportHandler handler= handlers.getVaticanReportHandler();
				handler.perform(player, choice);
				synchronized (player) {
					if(player.getStatus()==PlayerStatus.WAITINGHISTURN || player.getStatus()==PlayerStatus.SUSPENDED)// time out reached
						return;
					 //changing the state
					player.setStatus(PlayerStatus.WAITINGHISTURN);
					}
		}
			
			catch ( IllegalStateException e ) {
				e.printStackTrace();
				synchronized (player) {
					player.setStatus(PlayerStatus.WAITINGHISTURN);
					player.notifyObservers("action not valid");
					handlers.getVaticanReportHandler().perform(player, 0);
				}
				}	
			
		}	
}
