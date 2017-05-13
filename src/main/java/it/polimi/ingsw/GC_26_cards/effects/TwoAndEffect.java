package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;

//this classes handles the cases in which two effect are obtained at the same time: for example getting faith points and picking a card
public class TwoAndEffect implements Effect{
	Effect effect1;
	Effect effect2;
	
	public TwoAndEffect(Effect effect1, Effect effect2) {
		this.effect1=effect1;
		this.effect2=effect2;
	}
	public Effect getEffect1() {
		return effect1;
	}
	public Effect getEffect2() {
		return effect2;
	}
	private TwoAndEffect(TwoAndEffect other) { //created for deep cloning
		this.effect1= other.getEffect1().copy();
		this.effect2=other.getEffect2().copy();
	}
	
	
	@Override
	public void doEffect(Player player, boolean immediate) {
		effect1.doEffect(player, immediate);
		effect2.doEffect(player, immediate);
		
	}
	@Override
	public Effect copy() {
		return new TwoAndEffect(this);
	}
	
	
	

}
