package it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments;

import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that implements the Payment interface and that represents the Military Point payment
 *
 */
public class MilitaryPointPayment implements Payment{
	private final int toSpend;  // the amount player has  really to spend
	private final int needed;  // the requirement
	
	/**
	 * Constructor: it creates a Military Point payment with the amount of Military Points to spend and the amount of
	 * the required Military Points, as contained in the parameters
	 * @param toSpend It's the amount of Military Points to spend with this payment
	 * @param needed It's the required Military Points to perform this payment
	 */
	public MilitaryPointPayment(int toSpend, int needed) {
		this.toSpend=toSpend;
		this.needed= needed;
	}
	
	/**
	 * Method that returns the amount of Military Points to spend with this payment
	 * @return the amount of Military Points to spend with this payment
	 */
	public int getToSpend() {
		return toSpend;
	}
	
	/**
	 * Method that returns the amount of the required Military Points to perform this payment
	 * @return the amount of the required Military Points to perform this payment
	 */
	public int getNeeded() {
		return needed;
	}
	
	/**
	 * Method that checks if the player contained in the parameter can get the type of card contained in the parameter,
	 * by checking the amount of the Military Points of his warehouse and comparing them to the needed and toSpend
	 * values of the payment
	 */
	@Override
	public synchronized boolean canPlayerGetThis(Player player, DevelopmentCardTypes type) {
		if(player.getWarehouse().getMilitaryPoints()< needed )
			return false;
		if(player.getWarehouse().getMilitaryPoints()<toSpend)  //useless if isMilitaryPointRequirementNotNeeded()==false
			return false;
		else return true;
		
	}

	/**
	 * Method that makes the player contained in the parameter perform the payment to get the type of card contained 
	 * in the parameter
	 */
	@Override
	public synchronized void pay(Player player, DevelopmentCardTypes type) {
		player.getWarehouse().spendResources(ResourcesOrPoints.newPoints(0,toSpend,0,0));
	}

	/**
	 * Method that explains the required Military points and the Military points that have to be spent as a string
	 */
	@Override
	public String toString(){
		return " Military points: needed: "+needed +", to consume: "+ toSpend;
		
	}
	
}
