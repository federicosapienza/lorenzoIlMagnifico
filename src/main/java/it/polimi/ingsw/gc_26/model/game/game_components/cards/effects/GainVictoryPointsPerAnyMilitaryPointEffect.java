package it.polimi.ingsw.gc_26.model.game.game_components.cards.effects;

import it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points.Warehouse;
import it.polimi.ingsw.gc_26.model.player.Player;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the effect that gives a bonus of Victory Points for each n Military Points
 *
 */
public class GainVictoryPointsPerAnyMilitaryPointEffect implements Effect{
	private final ResourcesOrPoints toHave;
	
	/**
	 * Constructor: it creates the effect with the required resources or points indicated in the parameter
	 * @param toHave
	 */
	public GainVictoryPointsPerAnyMilitaryPointEffect(ResourcesOrPoints toHave) {
		this.toHave= toHave;
	}
	
	
	/**
	 * Method that applies the effect on the player's warehouse
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		Warehouse temp =player.getWarehouse();
		int test;
		if(toHave.getMilitaryPoints()==0)
			throw new IllegalArgumentException();
		test = temp.getMilitaryPoints()/toHave.getMilitaryPoints();
		temp.add(ResourcesOrPoints.newPoints(test, 0, 0, 0));
		}
		
	
	/**
	 * Method that describes the effect as a string
	 */
	@Override
	public String toString(){
		return " Gives "+ toHave+ " for any Military Point"; 
	}

}