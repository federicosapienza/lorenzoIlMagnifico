package it.polimi.ingsw.gc_26.model.game.game_components.board.positions;

import it.polimi.ingsw.gc_26.model.game.game_components.board.positions.common.SinglePosition;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Class that represents the Single harvest zone
 *
 */
public class SingleHarvest extends SinglePosition {

	/**
	 * Constructor: it creates a Single Harvest zone with the minimum required value that the family members of the player 
	 * must have to be put in this zone
	 * @param valueOfPosition the minimum required value that the family members of the player 
	 * must have to be put in this zone
	 */
	public SingleHarvest(int valueOfPosition) {
		super(valueOfPosition);
	}

	
	
	

}
