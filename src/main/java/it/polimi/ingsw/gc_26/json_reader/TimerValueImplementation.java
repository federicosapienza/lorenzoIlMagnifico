package it.polimi.ingsw.gc_26.json_reader;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Class that implements the logic around the timers needed in the game.
 *
 */
public class TimerValueImplementation implements TimerValuesInterface{
	/**
	 * It's the timer that starts when a game has been created and the number of players that have joined the game becomes 2.
	 * If the number of players is < 4 until the end of the timer, the game begins with the current number of players (2 or 3)
	 */
	int startingTimer;
	
	/**
	 * It's the timer that starts whenever the status of a player passes from WAITINGHISTURN to PLAYING.
	 * If the player doesn't make any action in this interval of time, he will be suspended (his status will become SUSPENDED)
	 */
	int turnTimer;
	
	/**
	 * It's the timer that starts whenever a player has enough Faith Points and so can decide whether to support the
	 * Church or not. If he doesn't decide until the end of the timer, he'll automatically get the excommunication.
	 */
	int vaticanReportTimer;
	
	/**
	 * Getter method that returns the timer for the beginning of the game
	 */
	@Override
	public int getStartingTimer() {
		return startingTimer;
	}
	
	/**
	 * Getter method that returns the timer for every turn of the game
	 */
	@Override
	public int getTurnTimer() {
		return turnTimer;
	}
	
	/**
	 * Getter method that returns the timer for the Vatican Report phase of the game
	 */
	@Override
	public int getVaticanReportTimer() {
		return vaticanReportTimer;
	}

	/**
	 * Setter method that sets the timer for the beginning of the game to the one contained in the parameter
	 * @param startingTimer is the timer for the beginning of the game
	 */
	public void setStartingTimer(int startingTimer) {
		this.startingTimer = startingTimer;
	}

	/**
	 * Setter method that sets the timer for every turn of the game to the one contained in the parameter
	 * @param turnTimer is the timer for every turn of the game
	 */
	public void setTurnTimer(int turnTimer) {
		this.turnTimer = turnTimer;
	}

	/**
	 * Setter method that sets the timer for the Vatican Report phase of the game to the one contained in the parameter
	 * @param vaticanReportTimer is the timer for the Vatican Report phase of the game
	 */
	public void setVaticanReportTimer(int vaticanReportTimer) {
		this.vaticanReportTimer = vaticanReportTimer;
	}

}
