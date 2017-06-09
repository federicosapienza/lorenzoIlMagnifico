package it.polimi.ingsw.GC_26_cards.excommunicationTile;

import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_player.Player;

public class ExcommunicationTileImplementation extends ExcommunicationTile{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Effect effect;
	private final int period;
	
	public ExcommunicationTileImplementation(int period, Effect effect) {
		this.effect=effect;
		this.period=period;
	}

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

	
	@Override
	public int getPeriod() {
		return period;
	}


}
