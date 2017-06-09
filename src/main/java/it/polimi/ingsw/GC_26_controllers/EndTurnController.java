package it.polimi.ingsw.GC_26_controllers;

import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.Info;
import it.polimi.ingsw.GC_26_utilities.Message;
import it.polimi.ingsw.GC_26_utilities.Request;

public class EndTurnController {
	
	Player player;
	MainActionHandler handlers;
	
	public EndTurnController(Player player, MainActionHandler handlers) {
		this.player=player;
		this.handlers= handlers;
	}
	
	public void update(boolean timeOutOccured){ //todo 
		//potrei mettere synronysed il controller per evitare problemi di 2 azioni in contemporane
		
		
		
		PlayerStatus status;
		synchronized (player) {
				 status= player.getStatus();
			
			if(!timeOutOccured && player.isPlayerActive())
				player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, "end turn", null));

			
			else{
				player.setStatus(new Request(PlayerStatus.SUSPENDED, "end turn", null));
				handlers.getGameElements().notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+" is suspended"));
			
					}
		}
		PlayerStatus previousStatus = player.getPreviousStatus();
		
		//setting the default parameters and calling the actions that were suspended if the turn ends where it should not
		if(previousStatus == PlayerStatus.TRADING)
			handlers.getTradeHandler().perform(player, 0);
			
		if(previousStatus== PlayerStatus.CHOOSINGPAYMENT)
			handlers.getTwoPaymentHandler().perform(player, 1);
		
		//scelta malus
		
		if(previousStatus==PlayerStatus.VATICANREPORTDECISION){
			handlers.getVaticanReportHandler().perform(player, 0);
			handlers.getGameElements().getGame().vaticanReportNext();
		}
			
	
		
		//ending his turn
	player.getWarehouse().resetCouncilPriviledges();
	player.endTurn();
	handlers.getGameElements().notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+ " has ended the turn" )); 
	handlers.getLeaderCardHandler().endTurn();
	handlers.getDiplomaticPrivilegesHandler().resetMemory();
	handlers.getGameElements().getGame().nextStep();
		
	}	
		
	
	
}
