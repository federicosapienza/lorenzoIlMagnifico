package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;

public class DoProduction implements Effect{
	private int value;
	
	public DoProduction(int value) {
		this.value= value;
	}
	
	public int getValue() {
		return value;
	}

	@Override
	public void doEffect(Player player, boolean immediate) {
		// TODO chiamerà l handler
		
	}
	
	private DoProduction(DoProduction other) {
		this.value = other.getValue();
	}

	@Override
	public Effect copy() {
		return new DoProduction(this);
	}
	
	@Override
	public String toString() {
		return "Production action of value " +value;
	}
	

}
