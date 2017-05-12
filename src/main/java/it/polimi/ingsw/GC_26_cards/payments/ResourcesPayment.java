package it.polimi.ingsw.GC_26_cards.payments;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class ResourcesPayment implements OnePayment{
	private final ResourcesOrPoints price;
	
	public ResourcesPayment(ResourcesOrPoints price) {
		this.price=price;	
	}
	
	private ResourcesPayment(ResourcesPayment other){  // constructor used for deep cloning. See copy here and in ResourcesAndPoints
		price = other.getPrice().copy();
	}
	
	public ResourcesOrPoints getPrice() {
		return price;
	}
	
	

	@Override
	public boolean canPlayerGetThis(Player player, ResourcesOrPoints resourcesUsedUntilNow) {
			ResourcesOrPoints temp = ResourcesOrPoints.sum(price, resourcesUsedUntilNow);
			ResourcesOrPoints temp2 =  ResourcesOrPoints.sum(temp2, /*TODO permanent modifiers*/);
			return player.getWarehouse.areResourcesEnough(temp2);
	}
	
	

	@Override
	public void pay(Player player) { // come gestisce l eccezione???
		player.getWarehouse.subctract(price);

		//TODO
		
	}

	@Override
	public Payment copy() {    // passes an object which is identical to the first: allows deep cloning
			return new ResourcesPayment(this);
	}
	
}
