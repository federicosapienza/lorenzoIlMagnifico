package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This class represents the Excommunication tile effect that cause a loss of Victory Points for every N Victory Points
 * that the affected player has at the end of the game, before the Final Scoring
 */

public class LoseVictoryPointsforEachNVictoryPointsEffect implements Effect{
	int malus;

	/**
	 * Constructor: it creates the effect. The number N of Victory Points that
	 * @param malus
	 */
	public LoseVictoryPointsforEachNVictoryPointsEffect(int malus) {
		this.malus = malus;
	}
	
	/**
	 * Method called to apply the effect to the player who is involved in the effect: 1 Victory Point will be lost for every
	 * "malus" Victory Points
	 */
	@Override
	public void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setVictoryPointsReducer(malus);
		
	}
	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		return " before the Final Scoring, you lose 1 Victory Point for every"+  malus+" Victory Points you have";
	}

}
