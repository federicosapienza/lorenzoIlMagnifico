package it.polimi.ingsw.GC_26.model.game.game_components.cards.effects;

import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the cases in which two effects are applied at the same time: for example getting both faith 
 * points and picking a card
 * 
 *
 */
public class TwoAndEffect implements Effect{
	Effect effect1;
	Effect effect2;
	
	/**
	 * Constructor: it creates the case in which two effects (expressed by effect1 and effect2) are applied at the same time.
	 * @param effect1 It's the first effect applied
	 * @param effect2 It's the second effect applied
	 */
	public TwoAndEffect(Effect effect1, Effect effect2) {
		this.effect1=effect1;
		this.effect2=effect2;
	}
	
	/**
	 * Method that returns the first effect
	 * @return the first effect
	 */
	public Effect getEffect1() {
		return effect1;
	}
	
	/**
	 * Method that returns the second effect
	 * @return the second effect
	 */
	public Effect getEffect2() {
		return effect2;
	}
	
	/**
	 * Method called to apply the effect to the player who is involved in the effect: it applies both the effect1 and the 
	 * effect2 on the affected player
	 * 
	 */
	@Override
	public void doEffect(Player player, boolean immediate) {
		effect1.doEffect(player, immediate);
		effect2.doEffect(player, immediate);
		
	}
	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		return  " Two effects: "+  effect1.toString()+" and "+effect2.toString();
	}
	

}
