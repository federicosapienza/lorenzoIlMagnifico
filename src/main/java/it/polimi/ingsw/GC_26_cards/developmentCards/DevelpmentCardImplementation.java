package it.polimi.ingsw.GC_26_cards.developmentCards;

import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.payments.Payment;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;


//It 's the implementation used by Character and Venture Cards. Territories and building cards extend this. 
public class DevelpmentCardImplementation implements DevelopmentCard{
	private final String name;
	private final int period;
	private final DevelopmentCardTypes type;
	private final Payment payment;
	private final Effect immediateEffect;
	private final Effect permanentEffect;
	private final int actionValue;
	
	private DevelpmentCardImplementation(String name, int period, DevelopmentCardTypes type, Payment payment, Effect immediate, Effect permanent, int actionValue) {
		this.name=name;
		this.period= period;
		this.type= type;
		if(!(period>=1 && period <=3))
				 throw new IllegalArgumentException();
		this.payment= payment;
		this.immediateEffect= immediate;
		this.permanentEffect= permanent;
		this.actionValue= actionValue;
	}
	/*protected DevelpmentCardImplementation(DevelpmentCardImplementation other) { //used for deep cloning
		name=new String( other.getName());  
		period= other.getPeriod();
		type=other.getType();
		payment= other.getPayment().copy();
		immediateEffect=other.getImmediateEffect().copy();
		permanentEffect=other.getPermanentEffect().copy();
		this.actionValue= other.getActionValue();	
	}
	*/
	
	public static DevelopmentCard territoryCard(String name, int period, Payment payment, Effect immediate, Effect permanent, int actionValue){
		return new DevelpmentCardImplementation(name, period, DevelopmentCardTypes.TERRITORYCARD, payment, immediate, permanent, actionValue);
	}
	
	public static DevelopmentCard buildingCard(String name, int period, Payment payment, Effect immediate, Effect permanent, int actionValue){
		return new DevelpmentCardImplementation(name, period, DevelopmentCardTypes.BUILDINGCARD, payment, immediate, permanent, actionValue);
	}
	
	public static DevelopmentCard characterCard(String name, int period, Payment payment, Effect immediate, Effect permanent){
		return new DevelpmentCardImplementation(name, period, DevelopmentCardTypes.CHARACTERCARD, payment, immediate, permanent, 0);
	}
	
	public static DevelopmentCard ventureCard(String name, int period, Payment payment, Effect immediate, Effect permanent, int actionValue){
		return new DevelpmentCardImplementation(name, period, DevelopmentCardTypes.VENTURECARD, payment, immediate, permanent, 0);
	}
	
	public String getName() {
		return name;
	}
	
	public int getPeriod() {
		return period;
	}
	public DevelopmentCardTypes getType() {
		return type;
	}
	public Payment getPayment() {
		return payment;
	}
	public Effect getImmediateEffect() {
		return immediateEffect;
	}
	public Effect getPermanentEffect() {
		return permanentEffect;
	}
	@Override
	public int getActionValue() {
		return actionValue;
	}

	@Override
	public boolean canPlayerGetThis(Player player, ResourcesOrPoints resourcesUsedUntilNow) {
		/* the second parameter allows to check if the player has enough resources even after other previous payments 
		 * (such as coins or servants) */
		return payment.canPlayerGetThis(player, resourcesUsedUntilNow);
		
	}
	@Override
	public void pay(Player player) {
		payment.pay(player);
		
	}

	@Override
	public void runImmediateEffect(Player player) {
		immediateEffect.doEffect(player, true);
		
	}

	@Override
	public void runPermanentEffect(Player player) {
		permanentEffect.doEffect(player, false);
		
	}
	/*@Override
	public DevelopmentCard copy() {
		return new DevelpmentCardImplementation(this);
	} */
	
	//TODO to string completi e parziali

}
