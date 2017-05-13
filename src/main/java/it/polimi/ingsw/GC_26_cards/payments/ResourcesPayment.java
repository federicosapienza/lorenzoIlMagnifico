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
	public boolean canPlayerGetThis(Player player, ResourcesOrPoints resourcesUsedUntilNow) {
		ResourcesOrPoints temp=price;
		if(player.getPermanentModifiers().IsTherediscountOnResources()){  //handling Pico Della Mirandola Card: (and even similar cards if added)
			temp= ResourcesOrPoints.newResourcesOrPointsDiscount(temp, player.getPermanentModifiers().getDiscount());
		}
		temp = ResourcesOrPoints.sum(temp, resourcesUsedUntilNow); //  the total cost for picking the card is obtained 
		return player.getWarehouse().areResourcesEnough(temp);
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
