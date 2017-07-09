package it.polimi.ingsw.gc_26.model.game.game_components.cards.effects;


import it.polimi.ingsw.gc_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the effects that give additional resources to the affected players when they are gaining some
 * resources or points
 *
 */
public class ReceiveResourcesOrPointsEffect implements Effect{
	private final ResourcesOrPoints resources; 
	
	/**
	 * Constructor: it creates the effect with the resources or points contained in the parameter 
	 * @param resources the resources or points given additionally by the effect to the affected player
	 */
	public ReceiveResourcesOrPointsEffect(ResourcesOrPoints resources) {
		this.resources=resources;
	}
	
	/**
	 * Method that returns the resources or points given additionally by the effect
	 * @return the resources or points given additionally by the effect
	 */
	public ResourcesOrPoints getResources() {
		return resources;
	}
	
	/**
	 * Method called to apply the effect to the player who is involved in the effect: it adds the additional resources given
	 * by the effect to the warehouse of the affected player. Moreover, if the player owns the Leader card Santa Rita,
	 * each time he receives wood, stone, coins, or servants as an immediate effect from Development Cards 
	 * (not from an action space), he will receive the resources twice.
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getWarehouse().add(resources);
		
	
		
		if(immediate && player.getPermanentModifiers().isDoubleEarningOn()){
			//Santa Rita's Effect: only resources (not points are given again)
			ResourcesOrPoints temp = ResourcesOrPoints.newResources(resources.getResources().getCoins(), resources.getResources().getServants(),
					resources.getResources().getWood(), resources.getResources().getStone());
			player.getWarehouse().add(temp);
			
		}
	}

	/**
	 * Method that explains the effect as a string message
	 */
	@Override 
	public String toString(){
		return " Receive: "+ resources.toString(); 
	}
	

}
