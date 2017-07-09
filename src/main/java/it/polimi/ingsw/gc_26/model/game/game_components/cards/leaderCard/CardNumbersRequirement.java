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
 * the player's personal board satisfies all requirements for every type of card.
 *
 */
public class CardNumbersRequirement implements Requirement{
	private final int territoryCardsRequired;
	private final int buildingCardsRequired;
	private final int characterCardsRequired;
	private final int ventureCardsRequired;
	
	/**
	 * Constructor: it creates the requirement for the number of type of cards indicated in the parameters
	 * @param territoryCardsRequired It's the number of the Territory cards required
	 * @param characterCardsRequired It's the number of the Character cards required
	 * @param buildingCardsRequired It's the number of the Building cards required
	 * @param ventureCardsRequired It's the number of the Venture cards required
	 */
	public CardNumbersRequirement(int territoryCardsRequired,int characterCardsRequired , int buildingCardsRequired,
			int ventureCardsRequired) {
		this.territoryCardsRequired = territoryCardsRequired;
		this.buildingCardsRequired = buildingCardsRequired;
		this.characterCardsRequired = characterCardsRequired;
		this.ventureCardsRequired = ventureCardsRequired;
	}

	/**
	 * Method that checks if the player contained in the parameter has a personal board which satisfies all the requirements
	 * for every type of card
	 * @param player It's the player whose personal board has to be checked for the requirements
	 */
	@Override
	public synchronized boolean checkRequirement(Player player) {
		PersonalBoard personalBoard = player.getPersonalBoard();
		return personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.TERRITORYCARD)>=territoryCardsRequired &&
				personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.BUILDINGCARD)>=buildingCardsRequired &&
				personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.CHARACTERCARD)>=characterCardsRequired &&
				personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.VENTURECARD)>=ventureCardsRequired;
	}
	
	/**
	 * Method that explains the conditions of the requirement as a string
	 */
	@Override
	public String toString() {
		return "Player needs "+ territoryCardsRequired +" territory Cards " + buildingCardsRequired +" building Cards "
				+ characterCardsRequired +" character Cards "+ ventureCardsRequired +" venture Cards ."  ;
	}

	

}
