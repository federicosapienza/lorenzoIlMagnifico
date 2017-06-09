package it.polimi.ingsw.GC_26_controllers;


import it.polimi.ingsw.GC_26_actionsHandlers.DiplomaticPrivilegesHandler;
import it.polimi.ingsw.GC_26_actionsHandlers.LeaderCardHandler;
import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_actionsHandlers.TradeHandler;
import it.polimi.ingsw.GC_26_actionsHandlers.VaticanReportHandler;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_serverView.Observer;
import it.polimi.ingsw.GC_26_utilities.Request;

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
		
		if(status==PlayerStatus.TRADINGCOUNCILPRIVILEDGES )
			//player is trading diplomatic privileges
			diplomaticPrivilegesController( choice);
		if(status==PlayerStatus.ACTIONPERFORMED)
			//player Is asking to use Leader Card
			leaderCardsController(choice);

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
				player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, null , null));
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
					 player.backToPreviousStatus();
					player.setStatus(new Request(player.getStatus(), "action not valid" , null));

				}
			}		
		}
		private void diplomaticPrivilegesController(int choice) {
			try{
				DiplomaticPrivilegesHandler handler= handlers.getDiplomaticPrivilegesHandler();
				boolean flag = handler.isPossible(player, choice--);  //because player will send a value >=1, but we want it starting from zero
				if (!flag)  // the player is notified by the handler
					return;
				handler.perform(player, choice--);
				synchronized (player) {
					if(player.getStatus()==PlayerStatus.WAITINGHISTURN || player.getStatus()==PlayerStatus.SUSPENDED)// time out reached
						return;
					 //going back to previous state of the game, if time not expired and restarting the action that was interrputed
					if(player.getWarehouse().getCouncilPrivileges()>0)
						return;
					if(player.isThereAsecondaryAction())
						player.setStatus(new Request(PlayerStatus.SECONDPLAY, null , null));
					else player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, null , null));;
				}
				}
			catch(IllegalArgumentException e){
				e.printStackTrace();
				synchronized (player) {
					player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, "action not valid" , null));

					
				}
			}
			catch ( IllegalStateException e1 ) {
				e1.printStackTrace();
				synchronized (player) {
					player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, "action not valid" , null));

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
					player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, "action not valid" , null));
					

					}
				handlers.getGameElements().getGame().vaticanReportNext(); // automatically ends the turn
		}
			
			catch ( IllegalStateException e ) {
				e.printStackTrace();
				synchronized (player) {
					player.setStatus(new Request(PlayerStatus.VATICANREPORTDECISION, "action not valid" , null));
					handlers.getVaticanReportHandler().perform(player, 0);
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
				if(player.getWarehouse().getCouncilPrivileges()>0)
					player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEDGES,"you have" +player.getWarehouse().getCouncilPrivileges() +"diplomatic privileges left", null));
				//altrimenti lo stato resta lo stesso
				}
		}
		catch( IllegalStateException e){
			player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEDGES,"you have" +player.getWarehouse().getCouncilPrivileges() +"diplomatic privileges left", null));
		}
		}
}
