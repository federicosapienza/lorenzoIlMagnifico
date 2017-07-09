package it.polimi.ingsw.gc_26.model.game.game_components.board.positions;

import it.polimi.ingsw.gc_26.model.game.game_components.board.positions.common.MultiplePosition;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the Multiple Production zone of the board
 *
 */
public class MultipleProduction extends MultiplePosition {

	/**
	 * Constructor: it creates a Multiple Production zone with the minimum required value of the action that the player
	 * has to perform
	 * @param valueOfPosition It's the minimum required value of the action that the player
	 * has to perform
	 */
	public MultipleProduction(int valueOfPosition ) {
		super(valueOfPosition);
	}
	
	

}
