package it.polimi.ingsw.GC_26_cards.payments;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class ResourcesPayment implements Payment{
	private final ResourcesOrPoints price;
	
	public ResourcesPayment(ResourcesOrPoints price) {
		this.price=price;	
	}
	
	
	public ResourcesOrPoints getPrice() {
		return price;
	}
	
	

	@Override
	public synchronized boolean canPlayerGetThis(Player player, DevelopmentCardTypes type) {
		
		//calculating discount(if exists) 
		BoardZone zone = cardTypeToBoardZone(type);
		ResourcesOrPoints temp= price;
		if(player.getPermanentModifiers().IsTherediscountOnResources(zone))
			 temp= player.getPermanentModifiers().resourcesOrPointsDiscount(zone, price);
		
		// The player is notified if he has not enough resources for getting the card, and false is returned
		return player.getTestWarehouse().areResourcesEnough(temp);
			
		
	}
	
	

	@Override
	public synchronized void pay(Player player, DevelopmentCardTypes type) { 
		//calculating discount 
		BoardZone zone = cardTypeToBoardZone(type);
		ResourcesOrPoints temp= price;
		if(player.getPermanentModifiers().IsTherediscountOnResources(zone))
			 temp= player.getPermanentModifiers().resourcesOrPointsDiscount(zone, price);	
		player.getWarehouse().spendResources(temp);
	
	}
	
	@Override
	public String toString(){
		return " payment of "+ price.toString();
	}
	
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
