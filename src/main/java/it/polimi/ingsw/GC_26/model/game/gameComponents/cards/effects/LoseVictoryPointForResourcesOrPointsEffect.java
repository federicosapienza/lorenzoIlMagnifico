package it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects;

import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * This class represents the Excommunication tile effect that, at the end of the game, causes a loss of Victory Points for 
 * certain types of resources to the players affected.
 * Here, resources or points are used to save what and how many resources or points cause loss on Victory Points:
 * 0 means no malus; 1,2, ... stand for the amount of Victory Points subtracted for type.
 *
 */
	

public class LoseVictoryPointForResourcesOrPointsEffect implements Effect{
	ResourcesOrPoints malus;
	
	/**
	 * Constructor: it creates the effect. The resources that will cause the loss of Victory Points are the ones expressed
	 * in the parameter
	 * @param resources the resources that cause a loss of Victory Points
	 */
	public LoseVictoryPointForResourcesOrPointsEffect(ResourcesOrPoints resources) {
		malus= resources;
	}

	/**
	 * Method called to apply the effect to the affected player: it adds the malus to the list of parameters that cause a 
	 * loss of Victory Points
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().addParameterForLosingPoints(malus);
	}
	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		return " At the end of the game, player loses Victory Point : (means:  number of loss for every type written) " + malus;
	}

}
