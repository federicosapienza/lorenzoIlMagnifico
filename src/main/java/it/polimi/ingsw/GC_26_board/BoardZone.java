package it.polimi.ingsw.GC_26_board;


/* Used by class Action e actionHandlers to specify where in the board the action will be set. It makes the code more easily readable.
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
