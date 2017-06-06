package it.polimi.ingsw.GC_26_board;


/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 *  This is used by class Action and actionHandlers to specify where the action will be set in the board. 
 *  It makes the code easier to read.
 * 
 */
public enum BoardZone {
	TERRITORYTOWER("territory tower"),
	BUILDINGTOWER("building tower"), 
	CHARACTERTOWER("character tower"),
	VENTURETOWER("venture tower"),
	HARVEST("harvest zone"),
	PRODUCTION("production zone"),
	MARKET("market zone"),
	COUNCILPALACE("Council Palace");
	
	private final String stringDescriber;
	
	BoardZone(String stringDescriber){
		this.stringDescriber = stringDescriber;
	}
	public String getStringDescriber() {
		return stringDescriber;
	}

}
