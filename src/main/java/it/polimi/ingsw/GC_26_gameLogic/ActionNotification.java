package it.polimi.ingsw.GC_26_gameLogic;


public class ActionNotification extends Action{
	private final String playerName;

	public ActionNotification(String playerName , Action action) {
		super(action.getZone(),action.getPosition(), action.getFamilyMemberColour(), action.getServantsUsed());
		this.playerName=playerName;
		}
	
	public String getPlayerName() {
		return playerName;
	}

}
