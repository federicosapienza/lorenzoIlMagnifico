package it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards;


/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Enumeration created in order to reduce the complexity and the length of the code.
 * It's used by some types of effects only.
 * It must not be misunderstood as the four subclasses that really implement the differences between
 * development card types.
*/
public enum DevelopmentCardTypes {
	TERRITORYCARD("Territory card"),
	BUILDINGCARD("Building card"),
	CHARACTERCARD("Character card"),
	VENTURECARD("Venture card");
	
	private String stringDescriber;
	
	/**
	 * Constructor of DevelopmentCardTypes: it sets the stringDescriber to the one contained in the @param stringDescriber
	 * @param stringDescriber It's the describer of the string.
	 */
	private DevelopmentCardTypes(String stringDescriber) {
		this.stringDescriber=stringDescriber;
	}
	
	/**
	 * Method that returns a textual representation of the object
	 */
	@Override
	public String toString() {
		return stringDescriber;
	}
	
	
}
