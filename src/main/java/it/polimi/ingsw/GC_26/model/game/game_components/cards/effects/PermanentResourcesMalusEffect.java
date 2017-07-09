package it.polimi.ingsw.GC_26.model.game.game_components.cards.effects;

import it.polimi.ingsw.GC_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This class represents the 1st period Excommunication tiles effects that cause a reduction of a certain type of 
 * resources or points, each time the affected player gains a certain amount of that type of resources or points  
 *
 */
public class PermanentResourcesMalusEffect implements Effect{
	private final ResourcesOrPoints malus;
	
	
	/**
	 * Constructor: it creates the effect with the malus of resources or malus expressed in the parameter
	 * @param malus It indicates the reduction of a certain type of resources or points 
	 */
	public PermanentResourcesMalusEffect(ResourcesOrPoints malus) {
		
		this.malus = malus;
	}
	
	/**
	 * Method called to apply the effect to the player who is involved in the effect: it adds the type of resources or points
	 * that cause a reduction to the list of resources or points that are already causing reductions
	 */

	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setMalus(malus);		
	}
	
	/**
	 * Method that explains the effect as a string message
	 */
	@Override
	public String toString() {
		return " Each time player gains something, gains "+ malus+ " less";
	}

}
