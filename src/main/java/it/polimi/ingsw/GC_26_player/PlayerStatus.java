package it.polimi.ingsw.GC_26_player;



/*Represents the status of the player: this way we can prevent the player to do something he can not do at any time. 
 * changes to status need to be synchronized.
 * 
 * Furthermore , here are set the flags and any piece of information needed for second actions (the action created by cards)
 */

public enum PlayerStatus {
	WAITINGHISTURN,
	// VALIDATING, //needed to ensure that the player do not produce an other action before validating or refusing a precedent one.
	// it makes synchronization much simpler in case of fast and near attempts to do the same kind of action.
	PLAYING,
	CHOOSINGPAYMENT,  // This status is needed because player could be asked to choose payment
	TRADING,
	ACTIONPERFORMED, // the status  reached when player can convert Diplomatic Priviledges and use Leader cards
	SECONDPLAY,
	SUSPENDED , // reached when a player has not performed an action 
	VATICANREPORTDECISION;
	}	



