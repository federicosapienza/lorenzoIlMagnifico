package it.polimi.ingsw.GC_26_cards.excommunicationTile;

import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_player.Player;

public class ExcommunicationTileImplementation extends ExcommunicationTile{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Effect effect;
	
	

	public Effect getEffect() {
		return effect;
	}
	@Override
	public void runEffect(Player player) {
		effect.doEffect(player, false);
	}

	

	@Override
	public String getPermanentEffectDescriber() {
		return effect.toString();
	}

	



}
