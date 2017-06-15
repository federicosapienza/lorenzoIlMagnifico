package it.polimi.ingsw.GC_26_gameLogic;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the notifications made for every action performed by the players
 *
 */
public class ActionNotification extends Action{
	private final String playerName;

	/**
	 * Constructor: it creates a notification of the action performed by the player expressed in the parameters
	 * @param playerName It's the name of the player who has performed the action
	 * @param action It's the action performed by the player 
	 */
	public ActionNotification(String playerName , Action action) {
		super(action.getZone(),action.getPosition(), action.getFamilyMemberColour(), action.getServantsUsed());
		this.playerName=playerName;
		}
	
	/**
	 * Getter method that returns the name of the player who has performed the action
	 * @return the name of the player who has performed the action
	 */
	public String getPlayerName() {
		return playerName;
	}

}
