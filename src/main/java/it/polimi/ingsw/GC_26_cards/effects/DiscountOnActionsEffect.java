package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;


//used by cards such as Dame or PicoDellaMirandola (to specify every tower action is valid for discount leave null zone.

public class DiscountOnActionsEffect implements Effect{
	BoardZone zone;
	ResourcesOrPoints discount;
	
	public DiscountOnActionsEffect(BoardZone zone, ResourcesOrPoints discount) {
		this.zone=zone;
		this.discount=discount;
	}

	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().addDiscount(zone, discount);
		
	}
	
	 @Override
	public String toString() {
		if(zone!= null){
			return "discount of "+discount+" on actions of type "+ zone;
		}
		else
			return 
					"discount of "+discount+" on actions on any tower";
	}
	

}
