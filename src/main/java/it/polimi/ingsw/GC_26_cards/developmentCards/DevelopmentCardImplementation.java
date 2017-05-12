package it.polimi.ingsw.GC_26_cards.developmentCards;

public class DevelopmentCardImplementation implements DevelopmentCard{
	private String name;
	private int period;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean canPlayerGetThis(Player player, ResourcesOrPoints resourcesUsedUntilNow) {
		/* the second parameter allows to check if the player has enough resources even after other previous payments 
		 * (such as coins or servants) */
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pay(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runImmediateEffect(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runPermanentEffect(Player player) {
		// TODO Auto-generated method stub
		
	}
	

}
