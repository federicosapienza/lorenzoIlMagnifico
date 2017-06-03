package it.polimi.ingsw.GC_26_controllers;

import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

public class EndTurnController {
	
	Player player;
	MainActionHandler handlers;
	
	public EndTurnController(Player player, MainActionHandler handlers) {
		this.player=player;
		this.handlers= handlers;
	}
	
	public void update(){ //todo 
		//potrei mettere synronysed il controller per evitare problemi di 2 azioni in contemporane
		
		
		boolean connectionFailed=true; //inizializzazione provvisoria
		
		PlayerStatus status;
		synchronized (player) {
				 status= player.getStatus();
			
			if(!connectionFailed && player.isPlayerActive())
				player.setStatus(PlayerStatus.WAITINGHISTURN);
			
			else
					player.setStatus(PlayerStatus.SUSPENDED);
			//TODO mettere la notification
			
		}
		PlayerStatus previousStatus = player.getPreviousStatus();
		
		//setting the default parameters and calling the actions that wew suspended
		if(previousStatus == PlayerStatus.TRADING)
			handlers.getTradeHandler().perform(player, 0);
			
		if(previousStatus== PlayerStatus.CHOOSINGPAYMENT)
			handlers.getTwoPaymentHandler().perform(player, 1);
		
		//scelta malus
		
		if(previousStatus==PlayerStatus.VATICANREPORTDECISION)
			handlers.getVaticanReportHandler().perform(player, 0);
	
		
		//ending his turn
	player.getWarehouse().resetCouncilPriviledges();
	player.endTurn();
	handlers.getGameElements().getGame().getPeriod().getCurrentRound().endPlayerTurn();
		
		
	}	
		
	
	
}
