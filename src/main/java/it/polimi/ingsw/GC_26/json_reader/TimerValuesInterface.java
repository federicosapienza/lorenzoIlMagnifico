package it.polimi.ingsw.GC_26.json_reader;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * Interface for the various timers that are needed during the game
 *
 */
public interface TimerValuesInterface {
	int getStartingTimer();
	int getTurnTimer();
	int getVaticanReportTimer();
}
