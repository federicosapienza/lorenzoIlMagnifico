package it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects;

import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This class represents the Excommunication tile effect that inhibits a type of cards to influence the score of Victory Points
 *
 */
public class NoVictoryPointForCardTypeEffect implements Effect{
	private DevelopmentCardTypes type;
	
	/**
	 * Constructor: it creates the effect for the type of card contained in the parameter
	 * @param type It's the type of card that won't influence anymore the score of Victory Points.
	 */
	public NoVictoryPointForCardTypeEffect(DevelopmentCardTypes type) {
		this.type= type;
	}

	/**
	 * Method called to apply the effect to the player who is involved in the effect: it adds the type of Development Cards
	 * contained in the constructor to the set of card types that cannot influence anymore the score of Victory Points
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().noVictoryPointsForCardType(type);
	}
	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		return " Player don’t score points for any of "+ type.toString(); 
	}
	
	
	

}
