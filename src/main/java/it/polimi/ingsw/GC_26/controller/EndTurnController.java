package it.polimi.ingsw.GC_26.controller;

import it.polimi.ingsw.GC_26.messages.Info;
import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.model.game.gameLogic.GameStatus;
import it.polimi.ingsw.GC_26.model.handlers.actionHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.model.player.PlayerStatus;
import it.polimi.ingsw.GC_26.utilities.observer.Observer;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that represents the controller that checks everything is correct when the turn ends.
 *
 */
public class EndTurnController implements Observer<Boolean>{
	
	private Player player;
	private MainActionHandler handlers;
	
	/**
	 * Constructor: it creates a controller for the end of the turn of a player and the MainActionHandler to handle it
	 * @param player It's the player that is ending his turn
	 * @param handlers It's the MainActionHandler that handles the end of the turn
	 */
	public EndTurnController(Player player, MainActionHandler handlers) {
		this.player=player;
		this.handlers= handlers;
	}
	
	/**
	 * Method that updates the status of the player checking if he is active and if the timeout for the turn has occurred and finally 
	 * ends the turn passing at the next step of the game
	 */
	public void update(Boolean timeOutOccured){ 
		PlayerStatus previousStatus;
		synchronized (player) {
			previousStatus= player.getStatus();
			if(timeOutOccured && !player.isPlayerActive() ){
				player.setStatus(new Request(PlayerStatus.SUSPENDED, "end turn", null));
				handlers.getGameElements().notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+" is suspended"));
					}
			else  {
				player.setStatus(new Request(PlayerStatus.WAITINGHISTURN, "end turn", null));
			}
			
			//notifying the player is turn is ended if timeout occurred
			if(timeOutOccured &&player.isPlayerActive()){
				player.notifyObservers(new Request(PlayerStatus.WAITINGHISTURN, "Your turn was ended due to time out occurrence", null));
			}
			
			//setting the default parameters and calling the actions that were suspended if the turn ends where it should not
			if(previousStatus == PlayerStatus.TRADING)
				handlers.getTradeHandler().perform(player, 0);
				
			if(previousStatus == PlayerStatus.CHOOSINGPAYMENT)
				handlers.getTwoPaymentHandler().perform(player, 1);
			
			//scelta malus
			
			if(previousStatus == PlayerStatus.VATICANREPORTDECISION){
				handlers.getVaticanReportHandler().perform(player, 0);
				handlers.getGameElements().getGame().vaticanReportNext();
			}
			//ending his turn
			player.getWarehouse().resetCouncilPrivileges();
			player.endTurn();
			handlers.getGameElements().notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+ " has ended the turn" )); 
			handlers.getDiplomaticPrivilegesHandler().resetMemory();
			handlers.getHarvestAndProductionHandler();
			handlers.getGameElements().getGame().nextStep();
			return;	
		}
		
	
	}
}
