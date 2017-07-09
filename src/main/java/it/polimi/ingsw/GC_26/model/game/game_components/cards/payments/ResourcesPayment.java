package it.polimi.ingsw.GC_26.model.game.game_components.cards.payments;

import it.polimi.ingsw.GC_26.model.game.game_components.board.BoardZone;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.payments.common.Payment;
import it.polimi.ingsw.GC_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that implements the Payment interface and represents the payment of resources and points
 *
 */
public class ResourcesPayment implements Payment{
	private final ResourcesOrPoints price;
	
	/**
	 * Constructor: it creates a payment with the resources and points to pay, indicated in the parameter
	 * @param price It's the amount of resources and points to pay
	 */
	public ResourcesPayment(ResourcesOrPoints price) {
		this.price=price;	
	}
	
	/**
	 * Method that returns the amount of resources and points to pay with this payment
	 * @return the amount of resources and points to pay with this payment
	 */
	public ResourcesOrPoints getPrice() {
		return price;
	}
	
	/**
	 * Method that checks if the player contained in the parameter can get the type of card indicated in the parameter,
	 * by checking if the player has enough resources and points in his warehouse to get that type of card, considering 
	 * also the eventual discounts created by permanent effects.
	 * @return true if the player has enough resources and points in his warehouse; false if the player doesn't have enough
	 * resources and points in his warehouse.
	 */
	@Override
	public synchronized boolean canPlayerGetThis(Player player, DevelopmentCardTypes type) {
		
		BoardZone zone = cardTypeToBoardZone(type);
		ResourcesOrPoints temp= price;
		if(player.getPermanentModifiers().IsTherediscountOnResources(zone))
			 temp= player.getPermanentModifiers().resourcesOrPointsDiscount(zone, price);
		
		return player.getTestWarehouse().areResourcesEnough(temp);
			
		
	}

	/**
	 * Method that makes the player contained in the parameter perform the payment to get the type of card contained 
	 * in the parameter, considering also the eventual discounts created by the permanent effects.
	 */
	@Override
	public synchronized void pay(Player player, DevelopmentCardTypes type) { 
		//calculating discount 
		BoardZone zone = cardTypeToBoardZone(type);
		ResourcesOrPoints temp= price;
		if(player.getPermanentModifiers().IsTherediscountOnResources(zone))
			 temp= player.getPermanentModifiers().resourcesOrPointsDiscount(zone, price);	
		player.getWarehouse().spendResources(temp);
	
	}
	
	/**
	 * Method that explains the conditions of the payment as a string
	 */
	@Override
	public String toString(){
		return " "+ price.toString();
	}
	
	/**
	 * Method that associates the type of Development card with the corresponding Board Zone. 
	 * @param type the type of Development card
	 * @return the Board Zone which matchers with the type of Development card
	 */
	private BoardZone cardTypeToBoardZone(DevelopmentCardTypes type){
		switch (type) {
		case TERRITORYCARD:
			return BoardZone.TERRITORYTOWER;
		case BUILDINGCARD:
			return BoardZone.BUILDINGTOWER;
		case CHARACTERCARD:
			return BoardZone.CHARACTERTOWER;
		default:
			return BoardZone.VENTURETOWER;
		}
	}

}
