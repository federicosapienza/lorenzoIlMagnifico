package it.polimi.ingsw.GC_26_cards.leaderCard;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_personalBoard.PersonalBoard;
import it.polimi.ingsw.GC_26_player.Player;


public class CardNumbersRequirement implements Requirement{
	private final int territoryCardsRequired;
	private final int buildingCardsRequired;
	private final int characterCardsRequired;
	private final int ventureCardsRequired;
	
	
	public CardNumbersRequirement(int territoryCardsRequired,int characterCardsRequired , int buildingCardsRequired,
			int ventureCardsRequired) {
		this.territoryCardsRequired = territoryCardsRequired;
		this.buildingCardsRequired = buildingCardsRequired;
		this.characterCardsRequired = characterCardsRequired;
		this.ventureCardsRequired = ventureCardsRequired;
	}

	@Override
	public synchronized boolean checkRequirement(Player player) {
		PersonalBoard personalBoard =player.getPersonalBoard();
		return personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.TERRITORYCARD)>=territoryCardsRequired &&
				personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.BUILDINGCARD)>=buildingCardsRequired &&
				personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.CHARACTERCARD)>=characterCardsRequired &&
				personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.VENTURECARD)>=ventureCardsRequired;
	}
	
	@Override
	public String toString() {
		return "Player needs "+ territoryCardsRequired +" territory Cards " + buildingCardsRequired +" building Cards "
				+ characterCardsRequired +" character Cards "+ ventureCardsRequired +" venture Cards ."  ;
	}

	

}
