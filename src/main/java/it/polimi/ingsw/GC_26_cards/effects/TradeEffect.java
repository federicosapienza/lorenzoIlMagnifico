package it.polimi.ingsw.GC_26_cards.effects;


import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;


//Trade effect needs dialog with user: there is often choice and playyer can also decide not to trade!
/*Creation rules: 
 * if a resource to be given can be traded in two different ways , this resouce must be inserted both in give1 and give2
 * if  there is a choice in giving and only one possible outcome, the outcome must be inserted both in receive1 that receive2. 
 * If there is only one income and only one outcome leave give2 and receive2 null; 
 */
public class TradeEffect implements Effect{
	private ResourcesOrPoints give1;  
	private ResourcesOrPoints give2;
	private ResourcesOrPoints receive1;
	private ResourcesOrPoints receive2;
	
	public TradeEffect(ResourcesOrPoints give1, ResourcesOrPoints give2, ResourcesOrPoints receive1, ResourcesOrPoints receive2) {
		this.give1= give1;
		this.give2=give2;
		this.receive1= receive1;
		this.receive2= receive2;
	}
	
	private TradeEffect(TradeEffect other){ //for deep cloning
		give1= other.getGive1().copy();
		give2= other.getGive2().copy();
		receive1= other.getReceive1().copy();
		receive2= other.getReceive2().copy();

	}
	
	public ResourcesOrPoints getGive1() {
		return give1;
	}
	public ResourcesOrPoints getGive2() {
		return give2;
	}
	
	public ResourcesOrPoints getReceive1() {
		return receive1;
	}
	public ResourcesOrPoints getReceive2() {
		return receive2;
	}
	
	@Override
	public void doEffect(Player player, boolean immediate) {
		// TODO chiama l handler
		
	}
	@Override
	public Effect copy() {
		return new TradeEffect(this);
	}
	
	@Override
	public String toString() {
		if(receive2==null && give2==null)
			return "Trade Action:" + give1+ " to "+receive1;
		if(receive2==null && give2!=null || receive2!= null && give2==null)
			throw new IllegalArgumentException("error in creating card"); //  creation rules not followed!
		return "Trade Action:" + give1+ " to "+receive1+" or " + give2+ " to " +receive2;
	}
	
	
}
