package it.polimi.ingsw.GC_26_cards.payments;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class ResourcesPayment implements Payment{
	private final ResourcesOrPoints price;
	
	public ResourcesPayment(ResourcesOrPoints price) {
		this.price=price;	
	}
	
	private ResourcesPayment(ResourcesPayment other){  // constructor used for deep cloning. See copy() here and in ResourcesAndPoints
		price = other.getPrice().copy();
	}
	
	public ResourcesOrPoints getPrice() {
		return price;
	}
	
	

	@Override
	public boolean canPlayerGetThis(Player player) {
		ResourcesOrPoints temp=price;
		if(player.getPermanentModifiers().IsTherediscountOnResources()){  //handling Pico Della Mirandola Card: (and even similar cards if added)
			temp= ResourcesOrPoints.newResourcesOrPointsDiscount(temp, player.getPermanentModifiers().getDiscount());
		}
		
		// The player is notified if the player has not enough resources for getting the card, and false is returned
		if (!player.getTestWarehouse().areResourcesEnough(temp)){
			return false;
		}
		else return true;
	}
	
	

	@Override
	public void pay(Player player) { // come gestisce l eccezione???
		ResourcesOrPoints temp=price;
		if(player.getPermanentModifiers().IsTherediscountOnResources()){  //handling Pico Della Mirandola Card: (and even similar cards if added)
			temp= ResourcesOrPoints.newResourcesOrPointsDiscount(temp, player.getPermanentModifiers().getDiscount());
		}

		player.getWarehouse().spendResources(temp);

		//TODO	
	}
	
	@Override
	public String toString(){
		return " payment of "+ price.toString();
	}

	@Override
	public Payment copy() {    // passes an object which is identical to the first: allows deep cloning
			return new ResourcesPayment(this);
	}
	
}
