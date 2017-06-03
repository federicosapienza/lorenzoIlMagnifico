package it.polimi.ingsw.GC_26_gameLogic;


public class TimerInfo {
	private int turnTime;
	private int vaticanReportChoiceTime;
	
	
	
	
	
	
	public TimerInfo(int times[]) {
		turnTime= times[0];
		vaticanReportChoiceTime = times[1];
	}
	public int getTurnTime() {
		return turnTime;
	}
	public int vaticanReportChoiceTime(){
		return vaticanReportChoiceTime;
	}

}
