package it.polimi.ingsw.GC_26.model.game.game_components.board.positions;

import it.polimi.ingsw.GC_26.model.game.game_components.board.positions.common.MultiplePosition;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Class that represents the Multiple Harvest zone of the board (activated only if the number of players is greater than 2)
 *
 */
public class MultipleHarvest extends MultiplePosition {

	/**
	 * Constructor: it creates a Multiple Harvest zone, with the minimum required value of the action that the player
	 * has to perform
	 * @param valueOfPosition the minimum required value of the action that the player
	 * has to perform
	 */
	public MultipleHarvest(int valueOfPosition ) {
		super(valueOfPosition);
	}
	
	
	
	

}
