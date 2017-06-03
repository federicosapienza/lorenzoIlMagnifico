package it.polimi.ingsw.GC_26_gameLogic;


import java.util.Iterator;

import it.polimi.ingsw.GC_26_board.Board;
import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_cards.Cards;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

public class Period {
	private int periodNumber;
	private Cards cards;
	private GameElements gameElements;
	private int roundNumber; 
	private int numOfRounds =  GameParameters.getRoundsforPeriod();
	private Round round;
	private ExcommunicationTile excommunicationTile;
	
	public Period(int period, Cards cards, GameElements gameElements, ExcommunicationTile excommunicationTile) {
		this.periodNumber=period;
		this.cards= cards;
		this.gameElements = gameElements;
		this.roundNumber=1;
		this.excommunicationTile= excommunicationTile;
		
	}
	
	public int getPeriodNumber() {
		return periodNumber;
	}
	public Round getCurrentRound() {
		return round;
	}
	
	public int getRoundNumber(){
		return roundNumber;
	}
	public ExcommunicationTile getExcommunicationTile() {
		return excommunicationTile;
	}
	
	public void start(){
		initialisePeriod();
		while(roundNumber<=numOfRounds){
			round = new Round( periodNumber, roundNumber, gameElements);
			round.start();
			roundNumber++;
			
		}
		vaticanReport();
	}

	
	private void vaticanReport() {
		for (Iterator<Player> iter = gameElements.getPlayers().iterator(); iter.hasNext(); ) {
		    Player player = iter.next();
		    startReportTurn(player);
		    //TODO è ok?
		    synchronized (this) {
		    while(player.getStatus()!= PlayerStatus.WAITINGHISTURN && player.getStatus()!= PlayerStatus.SUSPENDED){
		    	try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
		    }
		    
		}
		
	}
	
	private synchronized void startReportTurn(Player player){
		synchronized(player){
			//if the player has not enough faith points , excommunication tile effect is automatically activated.
    		//Otherwise the player will be asked to choose.
			  if(player.getWarehouse().getFaithPoints()< GameParameters.getFaithPointNeeded(periodNumber)){
			    	excommunicationTile.runEffect(player);
			    	//notificare i giocatori
			    	return;
			  }
			
			if(player.getStatus() == PlayerStatus.SUSPENDED){  
		    	//if player is suspended, the effect is runned.
		    	//TODO notificare i giocatori che il player salta il turno
		    	gameElements.notifyObservers(player.getName() + "misses his turn!");
		    	excommunicationTile.runEffect(player);
		    	return;
			}
		  player.setStatus(PlayerStatus.VATICANREPORTDECISION);
		   //TODO notifico al player che è il suo turno
		   player.notifyObservers("It's your turn");

			}	
		
}
	
	
	

	private void initialisePeriod(){
		gameElements.getBoard().getTower(BoardZone.BUILDINGTOWER).setCardsForThisPeriod(cards.getDevelopmentCards(periodNumber, DevelopmentCardTypes.BUILDINGCARD));
		gameElements.getBoard().getTower(BoardZone.CHARACTERTOWER).setCardsForThisPeriod(cards.getDevelopmentCards(periodNumber, DevelopmentCardTypes.CHARACTERCARD));
		gameElements.getBoard().getTower(BoardZone.TERRITORYTOWER).setCardsForThisPeriod(cards.getDevelopmentCards(periodNumber, DevelopmentCardTypes.TERRITORYCARD));
		gameElements.getBoard().getTower(BoardZone.VENTURETOWER).setCardsForThisPeriod(cards.getDevelopmentCards(periodNumber, DevelopmentCardTypes.VENTURECARD));
	}
	
	
	
	
}
