package it.polimi.ingsw.GC_26_cards.payments;

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
	public synchronized boolean canPlayerGetThis(Player player) {
		ResourcesOrPoints temp=price;
		if(player.getPermanentModifiers().IsTherediscountOnResources()){  //handling Pico Della Mirandola Card: (and even similar cards if added)
			temp=player.getPermanentModifiers().resourcesOrPointsDiscount(temp);
		}
		
		// The player is notified if the player has not enough resources for getting the card, and false is returned
		if (!player.getTestWarehouse().areResourcesEnough(temp)){
			return false;
		}
		else return true;
	}
	
	

	@Override
	public synchronized void pay(Player player) { // come gestisce l eccezione???
		ResourcesOrPoints temp=price;
		if(player.getPermanentModifiers().IsTherediscountOnResources()){  //handling Pico Della Mirandola Card: (and even similar cards if added)
			temp= player.getPermanentModifiers().resourcesOrPointsDiscount(temp);
			}

		player.getWarehouse().spendResources(temp);
	
	}
	
	@Override
	public String toString(){
		return " payment of "+ price.toString();
	}

}
