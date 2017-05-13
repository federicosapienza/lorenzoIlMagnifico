package it.polimi.ingsw.GC_26_cards.developmentCards;

import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.payments.Payment;

public class BuildingAndTerritoriesCardImplementation extends CharacterAndVenturesCards{
	private final int actionValue;
	
	public BuildingAndTerritoriesCardImplementation(String name, int period, DevelopmentCardTypes type, Payment payment,
			Effect immediate, Effect permanent, int actionValue) {
		super(name, period, type, payment, immediate, permanent);
		this.actionValue=actionValue;
	}
	
	private BuildingAndTerritoriesCardImplementation(BuildingAndTerritoriesCardImplementation other) {
		super(other);
		actionValue= other.getActionValue();
	}

	public int getActionValue() {
		return actionValue;
	}
	
	@Override
	public DevelopmentCard copy(){
		return  new BuildingAndTerritoriesCardImplementation(this);
	}

}
