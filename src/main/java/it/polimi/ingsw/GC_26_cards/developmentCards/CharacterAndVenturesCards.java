package it.polimi.ingsw.GC_26_cards.developmentCards;

import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.payments.Payment;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;


//It 's the implementation used by Character and Venture Cards. Territories and building cards extend this. 
public class CharacterAndVenturesCards implements DevelopmentCard{
	private final String name;
	private final int period;
	private final DevelopmentCardTypes type;
	private final Payment payment;
	private final Effect immediateEffect;
	private final Effect permanentEffect;
	
	public CharacterAndVenturesCards(String name, int period, DevelopmentCardTypes type, Payment payment, Effect immediate, Effect permanent) {
		this.name=name;
		this.period= period;
		this.type= type;
		if(!(period>=1 && period <=3))
				 throw new IllegalArgumentException();
		this.payment= payment;
		this.immediateEffect= immediate;
		this.permanentEffect= permanent;
	}
	protected CharacterAndVenturesCards(CharacterAndVenturesCards other) { //used for deep cloning
		name=new String( other.getName());  
		period= other.getPeriod();
		type=other.getType();
		payment= other.getPayment().copy();
		immediateEffect=other.getImmediateEffect().copy();
		permanentEffect=other.getPermanentEffect().copy();
		
		
	}
	@Override
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
	@Override
	public DevelopmentCard copy() {
		return new CharacterAndVenturesCards(this);
	}
	
	//TODO to string completi e parziali
	

	

}
