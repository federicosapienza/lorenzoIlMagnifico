package it.polimi.ingsw.GC_26_cards.developmentCards;

import it.polimi.ingsw.GC_26_cards.effects.Effect;

import it.polimi.ingsw.GC_26_cards.payments.Payment;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;

//It 's the implementation used by Character and Venture Cards. Territories and building cards extend this. 
public class DevelopmentCardImplementation extends DevelopmentCard{
	/**
	 * 
	 */
	private final String name;
	private final int period;
	private final DevelopmentCardTypes type;
	private final Payment payment;
	private final Effect immediateEffect;
	private final Effect permanentEffect;
	private final int actionValue;
	
	private DevelopmentCardImplementation(String name, int period, DevelopmentCardTypes type, Payment payment, Effect immediate, Effect permanent, int actionValue) {
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
	
	public static DevelopmentCard territoryCard(String name, int period, Payment payment, Effect immediate, Effect permanent, int actionValue){
		return new DevelopmentCardImplementation(name, period, DevelopmentCardTypes.TERRITORYCARD, payment, immediate, permanent, actionValue);
	}
	
	public static DevelopmentCard buildingCard(String name, int period, Payment payment, Effect immediate, Effect permanent, int actionValue){
		return new DevelopmentCardImplementation(name, period, DevelopmentCardTypes.BUILDINGCARD, payment, immediate, permanent, actionValue);
	}
	
	public static DevelopmentCard characterCard(String name, int period, Payment payment, Effect immediate, Effect permanent){
		return new DevelopmentCardImplementation(name, period, DevelopmentCardTypes.CHARACTERCARD, payment, immediate, permanent, 0);
	}
	
	public static DevelopmentCard ventureCard(String name, int period, Payment payment, Effect immediate, Effect permanent){
		return new DevelopmentCardImplementation(name, period, DevelopmentCardTypes.VENTURECARD, payment, immediate, permanent, 0);
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
	public boolean canPlayerGetThis(Player player) {
		// if the card is a territory card we first of all need to check if player has enough military points
		//also checks Cesare Borgia Effect
		if(type== DevelopmentCardTypes.TERRITORYCARD && !player.getPermanentModifiers().isMilitaryPointRequirementNotNeeded()){
			int territoryCardOwned = player.getPersonalBoard().getNumberOfCardPerType(DevelopmentCardTypes.TERRITORYCARD);
			if(player.getWarehouse().getMilitaryPoints() < GameParameters.getTerritoryCardRequirements(territoryCardOwned))
				player.notifyObservers("not enough military points  for a new territory card");
			return false;
		}
		if(payment==null)
			return true;
		
		 if(!payment.canPlayerGetThis(player, type)){
			 player.notifyObservers("not enough resources for getting the card");
		 	return false;
		 	}
		return true;
	}
	@Override
	public void pay(Player player) {
		if(payment !=null)
			payment.pay(player, type);
		
	}

	@Override
	public void runImmediateEffect(Player player) {
		if(immediateEffect!= null)
			immediateEffect.doEffect(player, true);
		
	}

	@Override
	public void runPermanentEffect(Player player) {
		if(immediateEffect!= null)
			permanentEffect.doEffect(player, false);
		
	}

	

	@Override
	public DevelopmentCardTypes getCardType() {
		return type;
	}

	@Override
	public String getImmediateEffectDescriber() {
		return immediateEffect.toString();
	}

	@Override
	public String getPermanentEffectDescriber() {
		return permanentEffect.toString();
	}

	

	

}
