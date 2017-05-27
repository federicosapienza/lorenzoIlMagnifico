package it.polimi.ingsw.GC_26_player;

import it.polimi.ingsw.GC_26_actionsHandlers.Action;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

/*Represents the status of the player: this way we can prevent the player to do something he can not do at any time. 
 * changes to status need to be synchronized.
 * 
 * Furthermore , here are set the flags and any piece of information needed for second actions (the action created by cards)
 */

public enum PlayerStatus {
	WAITINGHISTURN,
	VALIDATING, //needed to ensure that the player do not produce an other action before validating or refusing a precedent one.
	// it makes synchronization much simpler in case of fast and near attempts to do the same kind of action.
	PLAYING,
	CHOOSINGPAYMENT,  // This status is needed because player could be asked to choose payment 
	ACTIONPERFORMED, // the status  reached when player can convert Diplomatic Priviledges and use Leader cards
	SECONDPLAY,
	SUSPENDED ; // reached when a player has not performed an action 
	
	private boolean secondaryAction=false;
	private ResourcesOrPoints discountOnSecondAction;
	private Action typeOfSecondaryAction;
	
	public boolean isThereAsecondaryAction(){
		return secondaryAction;
	}
	public void setSecondaryAction(){
		secondaryAction = true;
	}
	public void setDiscountOnSecondAction(ResourcesOrPoints discountOnSecondAction) {
		this.discountOnSecondAction = discountOnSecondAction;
	}
	public ResourcesOrPoints getDiscountOnSecondAction() {
		return discountOnSecondAction;
	}
	public void setTypeOfSecondaryAction(Action typeOfSecondaryAction) {
		this.typeOfSecondaryAction = typeOfSecondaryAction;
	}
	public Action getTypeOfSecondaryAction() {
		return typeOfSecondaryAction;
	}
	
	public void resetSecondAction(){
		secondaryAction=false;
		discountOnSecondAction=null;
		typeOfSecondaryAction=null;
	}
}


