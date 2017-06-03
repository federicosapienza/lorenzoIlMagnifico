package it.polimi.ingsw.GC_26_gameLogic;

import java.util.Iterator;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;


public class Round {
	private int period;
	private int round;
	private GameElements gameElements;
	private int turnsNumber = GameParameters.getTurnsForRound(); // how many family member per player

	public Round(int period, int round, GameElements gameElements) {
		this.period= period;
		this.round= round;
		this.gameElements= gameElements;
	}

	public int getPeriod() {
		return period;
	}
	public int getRound() {
		return round;
	}
	
	public void start() {
		//throw the dices!
		gameElements.getDices().throwDices();
		
		
		// set the values of family members: it's valid for all the round
		for (Iterator<Player> iter = gameElements.getPlayers().iterator(); iter.hasNext(); ) {
		    Player p = iter.next();
		    p.getFamilyMembers().setValues(gameElements.getDices());
		}
		
		//sort the list of the players changing the order in which they will perform their turn. (in the first turn , order is not changed
		try {
			gameElements.getRankings().changeNextRoundOrder(gameElements.getPlayers());
		} catch (RuntimeException e) {
			e.printStackTrace();  // error generated when player list is corrupted! the list will not be sorted during the game!
		}
		
		// creates a valid turn for every player
		for(int i =0; i<turnsNumber; i++){
			for (Iterator<Player> iter = gameElements.getPlayers().iterator(); iter.hasNext(); ) {
			    Player player = iter.next();
			    startPlayerTurn(player);
			    
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
		
		//ends the round! : clears everything on the board
		gameElements.getBoard().endRound();
		
	}
	
	private synchronized void startPlayerTurn(Player player){
		synchronized(player){
			if(player.getStatus() == PlayerStatus.SUSPENDED){  
		    	//if player is suspended, misses the turn
		    	//TODO notificare i giocatori che il player salta il turno
		    	gameElements.notifyObservers(player.getName() + "misses his turn!");
		    	return;
			}
			if(player.getStatus() == PlayerStatus.WAITINGHISTURN){
			    player.setStatus(PlayerStatus.PLAYING);
			    //TODO notifico al player che è il suo turno
			    player.notifyObservers("It's your turn");
		
			}	
		}
}
	
	
	public synchronized void endPlayerTurn(){
		notifyAll();
	}
	
}
