package it.polimi.ingsw.GC_26_cards.excommunicationTile;

import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_player.Player;

public class ExcommunicationTileImplementation implements ExcommunicationTile{
	/**
	 * 
	 */
	private final Effect effect;
	private final int period;
	private final String name;
	
	public ExcommunicationTileImplementation(int period, Effect effect,String name) {
		this.effect=effect;
		this.period=period;
		this.name = name;
	}

	public Effect getEffect() {
		return effect;
	}
	@Override
	public void runEffect(Player player) {
		effect.doEffect(player, false);
	}

	public String getPermanentEffectDescriber() {
		if(effect!=null){
			return effect.toString();
		}
		else return null;
	}
	public int getPeriod() {
		return period;
	}

	public String getName(){
		return name;
	}


}
