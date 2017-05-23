package it.polimi.ingsw.GC_26_gameLogic;

import it.polimi.ingsw.GC_26_player.Player;

public class VaticanReportRound {
	int points; 
	int period; 
	
	public VaticanReportRound(int period) {
		this.period= period;
		points= GameParameters.getFaithPointNeeded(period);
	}
	
	public void start(Player p){
		//TODO
	}

}
