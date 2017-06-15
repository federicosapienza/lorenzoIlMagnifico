package it.polimi.ingsw.GC_26_cards.effects;


import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;


/*Trade effect 
 * 1) as permanent effect : needs dialog with user: there is often choice and player can also decide not to trade!
 * 2) as immediate effect: automatically done is possible (works with one receive- one give) (not present in the game but anyway is implemented)
*Creation rules: 
 * if a resource to be given can be traded in two different ways , this resource must be inserted both in give1 and give2
 * if  there is a choice in giving and only one possible outcome, the outcome must be inserted both in receive1 that receive2. 
 * If there is only one income and only one outcome leave give2 and receive2 null; 
 * 
 * So if one of both give2 and receive2 is not null , the other one (receive2 or give2) is not null
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
	public synchronized void doEffect(Player player, boolean immediate) {
		//if effect is immediate player is not asked to choose: if he did not wanted trade , he would not have gone here.
		if(immediate){
			if(player.getWarehouse().areResourcesEnough(receive1)){
					player.getWarehouse().spendResources(give1);
					player.getWarehouse().add(receive1);
			}
			return;
		}
		
		synchronized (player.getStatus()) {
			player.setStatus(new Request(PlayerStatus.TRADING, null,  new CardDescriber(player.getCardUsed())));// status change will call the interaction!
		}
		
	}
	@Override
	public String toString() {
		if(receive2==null && give2==null)
			return "Trade Action:" + give1+ " to "+receive1;
		else return "Trade Action: (1) " + give1+ " to "+receive1+" or (2) " + give2+ " to " +receive2;
	}
	
	
}
