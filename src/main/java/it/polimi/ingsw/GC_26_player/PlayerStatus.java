package it.polimi.ingsw.GC_26_player;



/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This enumeration represents the status of the player: 
 * in this way we can prevent the player to do something he cannot do at any time. 
 * Changes to status need to be synchronized.
 * Furthermore, this enumeration contains also the flags and any piece of information needed for second actions 
 * (the action created by cards)
 */

public enum PlayerStatus {
	WAITINGHISTURN,
	// VALIDATING, //needed to ensure that the player does not produce another action before validating or refusing a precedent one.
	// it makes synchronization much simpler in case of fast and near attempts to do the same kind of action.
	PLAYING,
	CHOOSINGPAYMENT,  // This status is needed because player could be asked to choose payment
	TRADING,
	TRADINGCOUNCILPRIVILEDGES,
	ACTIONPERFORMED, // the status reached when player can convert Diplomatic Privileges and use Leader cards
	SECONDPLAY,
	SUSPENDED , // set when a player has not performed an action within the default time interval
	VATICANREPORTDECISION;
	}	



