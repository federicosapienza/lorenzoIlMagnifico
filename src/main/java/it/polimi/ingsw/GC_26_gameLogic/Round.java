package it.polimi.ingsw.GC_26_gameLogic;

import java.util.Iterator;

import javax.net.ssl.SSLEngineResult.Status;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.dices.Dice;
import it.polimi.ingsw.GC_26_utilities.dices.Dices;

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
		
		
		// set the values of family members: it ' s valid for all the round
		for (Iterator<Player> iter = gameElements.getPlayers().iterator(); iter.hasNext(); ) {
		    Player p = iter.next();
		    p.getFamilyMembers().setValues(gameElements.getDices());
		}
		
		
		// creates a valid turn for every player
		for(int i =0; i<turnsNumber; i++){
			for (Iterator<Player> iter = gameElements.getPlayers().iterator(); iter.hasNext(); ) {
			    Player player = iter.next();
			    player.getFamilyMembers().setValues(gameElements.getDices());
			    if(player.getStatus()== PlayerStatus.SUSPENDED)  
			    	//if player is suspended it misses the turn
			    	//TODO notificare i giocatori che il player salta il turno
			    	gameElements.notifyObservers(player.getName() + "misses his turn!");  // look at gameElements
			    else if(player.getStatus()== PlayerStatus.WAITINGHISTURN){
			    player.setStatus(PlayerStatus.PLAYING);
			    //TODO notifico al player che è il suo turno
			    player.notifyObservers("your turn "+ player.getName());
			    //TODO è ok?
			    while(player.getStatus()!= PlayerStatus.WAITINGHISTURN || player.getStatus()!= PlayerStatus.SUSPENDED){
			    	wait();
			    //TODO mettere un notifyall!!!
			    }
			    player.endRound();
			    }
			}
		}
		//ends the round! : clears everything on the board
		gameElements.getBoard().endRound();
		
	}
	
}
