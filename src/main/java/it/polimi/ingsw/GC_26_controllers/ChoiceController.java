package it.polimi.ingsw.GC_26_controllers;

import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_actionsHandlers.TradeHandler;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

//the controller called whenever the game asks the client to perform a specific choice
public class ChoiceController {//TODO extends actionObserver etc
	Player player;
	MainActionHandler handlers;
	
	public ChoiceController(Player player, MainActionHandler handlers) {
		this.player=player;
		this.handlers= handlers;
	}
	
	void update(){ //todo
		int choice=0;
		PlayerStatus status; 
		synchronized (player) {
			status = player.getStatus();
		}
		if(status == PlayerStatus.TRADING)
			 tradeController(choice);
		if(status == PlayerStatus.CHOOSINGPAYMENT)
			TwoPaymentsController(choice);
		
		
		
		if(status==PlayerStatus.ACTIONPERFORMED || status== PlayerStatus.SECONDPLAY)  //player is trading diplomatic privileges
			diplomaticPrivilegesController( choice);

	}

	private void diplomaticPrivilegesController(int choice) {
		// TODO Auto-generated method stub
		
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
			handlers.getHarvestAndProductionHandler().notifyAll();
			}
		
		//restarting the production
		
		}
		catch(IllegalArgumentException e){
			e.printStackTrace();
			synchronized (player) {
				player.setStatus(PlayerStatus.WAITINGHISTURN); //ends the turn
				player.notifyObservers("action not valid");
			}
		}
	}
		
		private void TwoPaymentsController(int choice) {
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
}
