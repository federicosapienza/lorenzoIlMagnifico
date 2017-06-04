package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

/*Note: if this effect is present in association with an other (i.e. taking resources) , write the other as first.
 * 
 */

public class SetSecondAction implements Effect{
	private final int value;
	private final Action action;  //in creation: sets BoardType to type and null to other values; 
	//If the second action is take card from everywhere (i.e Abbess) leave null even this parameter.
	private final ResourcesOrPoints secondActionDiscount;  //leave null if does not exists
	
	public SetSecondAction(Action action, int value, ResourcesOrPoints discount) {
		this.value= value;
		this.action=action;
		this.secondActionDiscount=discount;
	}
	
	public int getValue() {
		return value;
	}

	@Override
	public  synchronized void doEffect(Player player, boolean immediate) {
		player.setSecondaryAction();
		player.setTypeOfSecondaryAction(action);
		player.setSecondactionValue(value);
		player.setDiscountOnSecondAction(secondActionDiscount);
	}
	

	
	@Override
	public String toString() {
		if(action.getZone()!=null){
		if(secondActionDiscount==null)
			return "Second action of type" +action.getZone() +" of value " +value;
		else{
			return "Second action of type" +action.getZone() +" of value " +value+ " with discount "+ secondActionDiscount;
		}
	}
		else return "Second action: pick a card in any tower " +", with action  value " +value;
	}

}
