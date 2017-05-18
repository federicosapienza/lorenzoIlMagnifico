package it.polimi.ingsw.GC_26_cards.developmentCards;


/*this enum has been created in order to reduce the complexity and the length of the code.
 * It 's only used by some types of effects.
 * It must not be mistaken with the four subclasses that truly implements the differences between development card types.
*/
public enum DevelopmentCardTypes {
	TERRITORYCARD("Territory card"),
	BUILDINGCARD("Building card"),
	CHARACTERCARD("Character card"),
	VENTURECARD("Venture card");
	
	private String stringDescriber;
	
	private DevelopmentCardTypes(String stringDescriber) {
		this.stringDescriber=stringDescriber;
	}
	
	@Override
	public String toString() {
		return stringDescriber;
	}
	
	
}
