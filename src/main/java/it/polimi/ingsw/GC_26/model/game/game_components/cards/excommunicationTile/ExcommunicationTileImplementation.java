package it.polimi.ingsw.GC_26.model.game.game_components.cards.excommunicationTile;

import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.Effect;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the implementation of the interface for the Excommunication tile. 
 *
 */
public class ExcommunicationTileImplementation implements ExcommunicationTile{
	/**
	 * 
	 */
	private final Effect effect;
	private final int period;
	
	/**
	 * Constructor: it creates the correct Excommunication tile with period and the effect indicated in the parameters
	 * @param period It's the period in which the Excommunication tile can be activated
	 * @param effect It's the effect that the Excommunication applies onto the excommunicated player
	 */
	public ExcommunicationTileImplementation(int period, Effect effect) {
		this.effect=effect;
		this.period=period;
	}

	/**
	 * Method that returns the effect applied by the Excommunication tile
	 * @return the effect applied by the Excommunication tile
	 */
	public Effect getEffect() {
		return effect;
	}
	
	/**
	 * Method called to apply the effect to the excommunicated player
	 */
	@Override
	public void runEffect(Player player) {
		effect.doEffect(player, false);
	}

	/**
	 * Method that describes the effect of the Excommunication tile as a string. If the effect is null, the returned object is null
	 * @return the effect of the Excommunication tile as a string if the effect isn't null; null if the effect is null
	 */
	public String getPermanentEffectDescriber() {
		if(effect!=null){
			return effect.toString();
		}
		else return null;
	}
	
	/**
	 * Method that returns the period in which the Excommunication tile can be activated
	 */
	public int getPeriod() {
		return period;
	}


}
