package it.polimi.ingsw.GC_26_player;



public enum PlayerStatus {
	WAITINGHISTURN,
	ACTIONVALIDATING, //needed to ensure that the player do not produce an other action before validating or refusing a precedent one.
	PLAYING,
	CHOOSINGPAYMENT,  // This status is needed because player could be asked to choose payment 
	ACTIONPERFORMED, // the status  reached when player can convert Diplomatic Priviledges and use Leader cards
	SUSPENDED ; // reached when a player has not performed an action 
	
	
}


