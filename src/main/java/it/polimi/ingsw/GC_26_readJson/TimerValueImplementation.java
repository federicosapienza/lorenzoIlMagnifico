package it.polimi.ingsw.GC_26_readJson;

public class TimerValueImplementation implements TimerValuesInterface{

	int startingTimer;
	int turnTimer;
	int vaticanReportTimer;
	
	@Override
	public int getStartingTimer() {
		return startingTimer;
	}

	@Override
	public int getTurnTimer() {
		return turnTimer;
	}

	@Override
	public int getVaticanReportTimer() {
		return vaticanReportTimer;
	}

	public void setStartingTimer(int startingTimer) {
		this.startingTimer = startingTimer;
	}

	public void setTurnTimer(int turnTimer) {
		this.turnTimer = turnTimer;
	}

	public void setVaticanReportTimer(int vaticanReportTimer) {
		this.vaticanReportTimer = vaticanReportTimer;
	}

}
