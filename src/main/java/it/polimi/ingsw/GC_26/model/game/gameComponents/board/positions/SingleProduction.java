package it.polimi.ingsw.GC_26.model.game.gameComponents.board.positions;

import it.polimi.ingsw.GC_26.model.game.gameComponents.board.positions.common.SinglePosition;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the Single Production zone of the board
 *
 */
public class SingleProduction extends SinglePosition{

	/**
	 * Constructor: it creates a Single Production zone with the minimum required value that the family members of the player
	 * must have to be put in this Single Production zone
	 * @param valueOfPosition It's the minimum required value that the family members of the player 
	 * must have to be put in this zone
	 */
	public SingleProduction(int valueOfPosition) {
		super(valueOfPosition);
	}
	
	
}
