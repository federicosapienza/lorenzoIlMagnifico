package it.polimi.ingsw.GC_26.model.game.game_components.cards.effects;


import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.model.player.PlayerStatus;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the trade effect, which has 2 sub-categories
 * 1) as permanent effect: it needs to ask the player what he wants to do, because there is the possibility to choose 
 * and he can also decide not to trade.
 * 2) as immediate effect: automatically done if it's possible (works with receive1 - give1; not present in the 
 * original game but it is implemented anyway)
 * 
 * Creation rules:
 * - If a resource that has to be given can be traded in two different ways, this resource must be inserted both in give1 and give2.
 * - If there is a choice in giving and only one possible outcome, the outcome must be inserted both in receive1 that receive2. 
 * - If there is only one income and only one outcome give2 and receive2 have to be null; 
 * 
 * So if either give2 or receive2 is not null, the other one (receive2 or give2) cannot be null
 *
 */

public class TradeEffect implements Effect{
	private ResourcesOrPoints give1;  
	private ResourcesOrPoints give2;
	private ResourcesOrPoints receive1;
	private ResourcesOrPoints receive2;
	
	/**
	 * Constructor: it creates the trade effect with the possible resources or points that the player has to give and the 
	 * possible resources or points that the player can receive.
	 * @param give1 the first choice when the player has to choose what to give
	 * @param give2 the second choice when the player has to choose what to give
	 * @param receive1 the first possible gain
	 * @param receive2 the second possible gain
	 */
	public TradeEffect(ResourcesOrPoints give1, ResourcesOrPoints give2, ResourcesOrPoints receive1, ResourcesOrPoints receive2) {
		this.give1= give1;
		this.give2=give2;
		this.receive1= receive1;
		this.receive2= receive2;
	}
	
	/**
	 * Method that returns the first choice when the player has to choose what to give
	 * @return the first choice when the player has to choose what to give
	 */
	public ResourcesOrPoints getGive1() {
		return give1;
	}
	
	/**
	 * Method that returns the second choice when the player has to choose what to give
	 * @return the second choice when the player has to choose what to give
	 */
	public ResourcesOrPoints getGive2() {
		return give2;
	}
	
	/**
	 * Method that returns the first possible gain
	 * @return the first possible gain
	 */
	public ResourcesOrPoints getReceive1() {
		return receive1;
	}
	
	/**
	 * Method that returns the second possible gain
	 * @return the second possible gain
	 */
	public ResourcesOrPoints getReceive2() {
		return receive2;
	}
	
	/**
	 * Method called to apply the effect to the player who is involved in the effect: it sets the player's status to TRADING
	 * 
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		
		synchronized (player.getStatus()) {
			player.setStatus(new Request(PlayerStatus.TRADING, null,  new CardDescriber(player.getCardUsed())));// status change will call the interaction!
		}
		
	}
	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		if(receive2==null && give2==null)
			return " Trade Action:" + give1+ " to "+receive1;
		else return " Trade Action: (1) " + give1+ " to "+receive1+" or (2) " + give2+ " to " +receive2;
	}
	
	
}
