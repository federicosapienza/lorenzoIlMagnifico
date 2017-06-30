package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the effects applied by cards like Dame and Pico Della Mirandola that reduce the cost of cards.
 * If the discount is applied to every tower, zone has to be specified as null. Values of discount must be > 0.
 *
 */


public class ReceiveDiscountOnActionsEffect implements Effect{
	BoardZone zone;
	ResourcesOrPoints discount;
	
	/**
	 * Constructor: it creates the correct discount in the correct board zone expressed in the parameters 
	 * @param zone It's the board zone where the discount has to be applied
	 * @param discount It's the discount to apply in the board zone
	 */
	public ReceiveDiscountOnActionsEffect(BoardZone zone, ResourcesOrPoints discount) {
		this.zone=zone;
		this.discount=discount;
	}

	/**
	 * Method called to apply the effect to the player who is involved in the effect: it adds the discount in the zone expressed
	 * in the constructor
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().addDiscount(zone, discount);
		
	}
	
	/**
	 * Method that explains the effect as a string message
	 */
	 @Override
	public String toString() {
		if(zone!= null){
			return " Discount of "+discount+" on actions of type "+ zone;
		}
		else
			return 
					" Discount of "+discount+" on actions on any tower";
	}
	

}
