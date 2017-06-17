package it.polimi.ingsw.GC_26_board;


/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This enumeration is needed whenever there's an action that involves a board zone (for example, it is used by classes
 * Action and actionHandlers to specify where the action will be set in the board. 
 * It makes the code easier to read.
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
	
	/**
	 * Constructor: the board zone is associated to the string describer expressed in the parameter
	 * @param stringDescriber It's the string describer of the board zone 
	 */
	BoardZone(String stringDescriber){
		this.stringDescriber = stringDescriber;
	}
	
	/**
	 * Method that returns the string describer that corresponds to the board zone
	 * @return the string describer that corresponds to the board zone
	 */
	public String getStringDescriber() {
		return stringDescriber;
	}

}
