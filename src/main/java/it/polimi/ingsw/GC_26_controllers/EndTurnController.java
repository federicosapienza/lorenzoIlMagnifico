package it.polimi.ingsw.GC_26_controllers;

import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_server.Observer;
import it.polimi.ingsw.GC_26_utilities.Info;
import it.polimi.ingsw.GC_26_utilities.Request;

public class EndTurnController implements Observer<Boolean>{
	
	Player player;
	MainActionHandler handlers;
	
	public EndTurnController(Player player, MainActionHandler handlers) {
		this.player=player;
		this.handlers= handlers;
	}
	
	public void update(Boolean timeOutOccured){ 
		PlayerStatus previousStatus;
		synchronized (player) {
			previousStatus= player.getStatus();
			
			if(!timeOutOccured && player.isPlayerActive())
				player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, "end turn", null));

			
			else{
				player.setStatus(new Request(PlayerStatus.SUSPENDED, "end turn", null));
				handlers.getGameElements().notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+" is suspended"));
			
					}
		}
		
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
	handlers.getHarvestAndProductionHandler();
	handlers.getGameElements().getGame().nextStep();
	return;	
	}	
		
	
	
}
