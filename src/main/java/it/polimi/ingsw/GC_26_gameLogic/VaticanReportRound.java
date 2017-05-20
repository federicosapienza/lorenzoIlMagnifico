package it.polimi.ingsw.GC_26_gameLogic;

public class VaticanReportRound {
	int points; 
	int period; 
	
	public VaticanReportRound(int period) {
		this.period= period;
		points= GameParameters.getFaithPointNeeded(period);
	}
	
	public void start(){
		//TODO
	}

}
