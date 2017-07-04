package it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects;

import it.polimi.ingsw.GC_26.model.game.action.Action;
import it.polimi.ingsw.GC_26.model.game.gameComponents.board.BoardZone;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the effects that have a second action. If this effect is associated with another one 
 * (for example taking resources), the other one will be applied first.
 *
 */


public class SetSecondAction implements Effect{
	private final int value;
	
	/**
	 * When creating the action, BoardZone is set to zone and other values to null; 
	 * if the second action is taking card from everywhere (for example Abbess) even this parameter is set to null.
	 */
	//
	private final Action action; 
	private final ResourcesOrPoints secondActionDiscount;  //leave null if does not exists
	
	/**
	 * Constructor: it creates the effect of the second action, with the value and the discount expressed in the parameters
	 * and with an action with 0 servants used and in position 0, in the zone expressed in the parameter
	 * @param zone It's the board zone involved in the action
	 * @param value It's the value of the second action
	 * @param discount It's the discount of resources or points for the second action
	 */
	public SetSecondAction(BoardZone zone, int value, ResourcesOrPoints discount) {
		this.value= value;
		this.action= new Action(zone, 0,null,0);
		this.secondActionDiscount=discount;
	}
	
	/**
	 * Method that returns the value of the effect of the second action
	 * @return the value of the effect of the second action
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Method called to apply the effect to the player who is involved in the effect: it activates the flag for the secondary 
	 * action, sets the correct type, the value and the discount of the secondary action  
	 */
	@Override
	public  synchronized void doEffect(Player player, boolean immediate) {
		player.setSecondaryAction();
		player.setTypeOfSecondaryAction(action);
		player.setSecondactionValue(value);
		player.setDiscountOnSecondAction(secondActionDiscount);
	}
	

	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		if(action.getZone()!=null){
		if(secondActionDiscount==null)
			return " Second action of type " +action.getZone() +" of value " +value;
		else{
			return " Second action of type " +action.getZone() +" of value " +value+ " with discount "+ secondActionDiscount;
		}
	}
		else return " Second action: pick a card in any tower " +", with action value " +value;
	}

}
