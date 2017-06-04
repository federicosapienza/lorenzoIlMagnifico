package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;

public class ReduceColouredFamilyMemberValue implements Effect{
	

	private final int value;

	public ReduceColouredFamilyMemberValue(int value) {
		this.value = value;
	}
	
	@Override
	public void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setColouredMembersMalusValue(value);
		
	}
	
	
}
