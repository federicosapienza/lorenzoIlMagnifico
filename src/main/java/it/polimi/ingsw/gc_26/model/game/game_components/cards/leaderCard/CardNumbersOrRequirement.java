package it.polimi.ingsw.gc_26.model.game.game_components.cards.leaderCard;

import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.gc_26.model.game.game_components.personal_board.PersonalBoard;
import it.polimi.ingsw.gc_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class implements the Requirement interface and represents the requirement that is satisfied when
 * the player's personal board satisfies at least the requirement of the number of one type of cards.
 *
 */
public class CardNumbersOrRequirement implements Requirement{
	private final int territoryCardsRequired;
	private final int buildingCardsRequired;
	private final int characterCardsRequired;
	private final int ventureCardsRequired;
	
	/**
	 * Constructor: it creates the requirement for the number of type of cards indicated in the parameters
	 * @param territoryCardsRequired It's the number of the Territory cards required
	 * @param buildingCardsRequired It's the number of the Building cards required
	 * @param characterCardsRequired It's the number of the Character cards required
	 * @param ventureCardsRequired It's the number of the Venture cards required
	 */
	public CardNumbersOrRequirement(int territoryCardsRequired, int buildingCardsRequired, int characterCardsRequired,
			int ventureCardsRequired) {
		this.territoryCardsRequired = territoryCardsRequired;
		this.buildingCardsRequired = buildingCardsRequired;
		this.characterCardsRequired = characterCardsRequired;
		this.ventureCardsRequired = ventureCardsRequired;
	}

	/**
	 * Method that checks if the player contained in the parameter has a personal board which satisfies at least one requirement
	 * @param player It's the player whose personal board has to be checked for the requirements
	 */
	@Override
	public synchronized boolean checkRequirement(Player player) {
		PersonalBoard personalBoard = player.getPersonalBoard();
		return (personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.TERRITORYCARD)>=territoryCardsRequired && territoryCardsRequired!=0) ||
				(personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.BUILDINGCARD)>=buildingCardsRequired && buildingCardsRequired!=0) ||
				(personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.CHARACTERCARD)>=characterCardsRequired && characterCardsRequired!=0) ||
				(personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.VENTURECARD)>=ventureCardsRequired&& ventureCardsRequired!=0);
	}
	
	/**
	 * Method that explains the conditions of the requirement as a string
	 * @return the explanation of the conditions of the requirement as a string
	 */
	@Override
	public String toString() {
		   //returns only the non 0 fields.
			StringBuilder temp = new StringBuilder("Player needs to satisfy at least one of the following conditions: ");
			if(territoryCardsRequired!=0)
				temp.append(territoryCardsRequired + " territory Cards ");
			if(buildingCardsRequired!=0)
				temp.append(buildingCardsRequired + " building Cards ");
			if(characterCardsRequired!=0)
				temp.append(characterCardsRequired + " character Cards ");
			if(ventureCardsRequired!=0)
				temp.append(ventureCardsRequired + " venture Cards");
			return temp.toString(); 
		}
	

}
