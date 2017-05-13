package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;

public class DoHarvest implements Effect{
	private int value;
	
	public DoHarvest(int value) {
		this.value= value;
	}
	
	public int getValue() {
		return value;
	}

	@Override
	public void doEffect(Player player, boolean immediate) {
		// TODO chiamerà l handler
		
	}
	
	private DoHarvest(DoHarvest other) {
		this.value = other.getValue();
	}

	@Override
	public Effect copy() {
		return new DoHarvest(this);
	}
	
	@Override
	public String toString() {
		return "Harvest action of value " +value;
	}
	

}
