package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;

//Setting dices Value: 
	//Ludovico Il Moro and Federico da Montefeltro effect. 
	//The first parameter shows how many colored family Member will have "value" value
public class FamilyMembersValueSetterEffect implements Effect{
	private final int value;
	private final int howManyDicesSetted;
	
	public FamilyMembersValueSetterEffect(int howManyDicesSetted, int value) {
		this.howManyDicesSetted= howManyDicesSetted;
		this.value = value;
	}

	
	
	@Override
	public void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setDicesSetted(howManyDicesSetted, value);
		
	}
	
	@Override
	public String toString() {
		return howManyDicesSetted+ " of your colored Family Members has a value of " +value+ " ,regardless of its related dice.";
	}

}
