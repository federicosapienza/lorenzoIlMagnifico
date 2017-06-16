package it.polimi.ingsw.GC_26_controllers;

import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_server.Observer;
import it.polimi.ingsw.GC_26_utilities.Request;

public class ActionController implements Observer<Action>{  //TODO extends actionObserver etc
	Player player;
	MainActionHandler handlers;
	
	public ActionController(Player player, MainActionHandler handlers) {
		this.player=player;
		this.handlers= handlers;
	}
	
	public void update(Action action){ 
		PlayerStatus status;
	
		synchronized (player) {
			 status= player.getStatus();
		}
		
		if(status == PlayerStatus.PLAYING)
			 firstActionController(action);
		if(status== PlayerStatus.SECONDPLAY)
			secondActionController(action);
	}
	
	
	private void firstActionController(Action action){
		player.setPlayerActive();
		try {
			Boolean flag = handlers.getFirstActionHandler().isPossible(player, action);
			// if action not possible player is notified in IsPossible and linked methods
			if(!flag)
				return;
			
			handlers.getFirstActionHandler().perform(player, action);
			synchronized (player) {
				if(player.getStatus()==PlayerStatus.CHOOSINGPAYMENT)
					return;
			}
			//otherwise
	
			synchronized (player) {
				if(player.getStatus()==PlayerStatus.WAITINGHISTURN || player.getStatus()==PlayerStatus.SUSPENDED)// time out reached
					return;
				if(player.getStatus()==PlayerStatus.CHOOSINGPAYMENT)  //server waits for action
					return;
				if(player.getWarehouse().getCouncilPrivileges()>0){
					player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEDGES,GameParameters.getDiplomaticPrivilegesDescription(), null));
				}
				else if(player.isThereAsecondaryAction())
					player.setStatus(new Request(PlayerStatus.SECONDPLAY, "Perform your allowed second action" , null));
				else 
					player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, null , null));
			} 
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.PLAYING, null , null));
			}
		} catch (IllegalStateException e1 ) {
			e1.printStackTrace();
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.PLAYING, null , null));

			}
		}
	}
	
	private void secondActionController(Action action){
		try {
			Boolean flag = handlers.getSecondActionHandler().isPossible(player, action);
			// if action not possible player is notified in IsPossible and linked methods
			if(flag)
				return;

			handlers.getSecondActionHandler().perform(player, action);
			player.resetSecondAction();	 
			synchronized (player) {
				if(player.getStatus()==PlayerStatus.WAITINGHISTURN || player.getStatus()==PlayerStatus.SUSPENDED)// time out reached
					return;
				if(player.getWarehouse().getCouncilPrivileges()>0){
					player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEDGES,"you have" +player.getWarehouse().getCouncilPrivileges() +"diplomatic privileges left", null));
				}
				else{
					player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, null , null));
				}
			
			}

				handlers.getSecondActionHandler().perform(player,  action);
				player.resetSecondAction();
			 synchronized (player) {
				 if(player.getStatus()==PlayerStatus.WAITINGHISTURN || player.getStatus()==PlayerStatus.SUSPENDED)// time out reached
						return;
				 if(player.getWarehouse().getCouncilPrivileges()>0){
					 player.setStatus(new Request(PlayerStatus.TRADINGCOUNCILPRIVILEDGES,GameParameters.getDiplomaticPrivilegesDescription(), null));
					 }
				 else{
					 player.setStatus(new Request(PlayerStatus.ACTIONPERFORMED, null , null));
				 }

			 }

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			synchronized (player) {
				player.setStatus(new Request(PlayerStatus.SECONDPLAY, null , null));

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
	
