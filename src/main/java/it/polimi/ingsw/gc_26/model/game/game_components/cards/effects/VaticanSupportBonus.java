package it.polimi.ingsw.gc_26.model.game.game_components.cards.effects;

import it.polimi.ingsw.gc_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the bonus given to the players who have supported the Church at the end of each period
 *
 */
public class VaticanSupportBonus implements Effect{
	private final ResourcesOrPoints bonus;
	
	/**
	 * Constructor: it creates the bonus to give to the players who have supported the Church at the end of the period
	 * @param bonus
	 */
	public VaticanSupportBonus(ResourcesOrPoints bonus) {
		this.bonus=bonus; 
	}

	/**
	 * Method called to apply the effect to the players involved in the effect: it gives the additional Victory Points, indicated
	 * in the spot of the Faith Track where every player who has supported the Church is taking place at the end of the period
	 * 
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().gainAdditionalVP(bonus.getVictoryPoints());
	}
	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
			return " Player gains "+ bonus.getVictoryPoints() +" victory Points when supporting the Church in a Vatican Report phase.";
	}

}
