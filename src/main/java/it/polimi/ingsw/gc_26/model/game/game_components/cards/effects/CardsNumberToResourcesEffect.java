package it.polimi.ingsw.gc_26.model.game.game_components.cards.effects;



import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the effect which converts the number of a type of cards into an amount of certain resources.  
 *
 */
public class CardsNumberToResourcesEffect implements Effect{
	private final DevelopmentCardTypes type;
	private final ResourcesOrPoints resourcesOrPoints;
	
	/**
	 * Constructor: it creates the correct effect for the type of cards and with the correct resources expressed in the parameters
	 * @param type It's the type of cards involved in the effect
	 * @param resources the resources or points given by the effect
	 */
	public CardsNumberToResourcesEffect(DevelopmentCardTypes type, ResourcesOrPoints resources) {
		this.type= type;
		this.resourcesOrPoints= resources;
	}
	
	/**
	 * Method that returns the resources or points given by the effect
	 * @return the resources or points given by the effect
	 */
	public ResourcesOrPoints getResourcesOrPoints() {
		return resourcesOrPoints;
	}
	
	/**
	 * Method that returns the type of cards involved in the effect
	 * @return the type of cards involved in the effect
	 */
	public DevelopmentCardTypes getType() {
		return type;
	}
	

	/**
	 * Method called to apply the effect to the player who is involved in the effect
	 */
	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		int number = player.getPersonalBoard().getNumberOfCardPerType(type);
		for(int i=0; i <number; i++){
			player.getWarehouse().add(resourcesOrPoints);
		}
		
	}

	/**
	 * Method that describes the effect as a string
	 */
	@Override
	public String toString(){
		return " Gives "+ resourcesOrPoints+ " for any card of type " +type.toString(); 
	}
		

}
