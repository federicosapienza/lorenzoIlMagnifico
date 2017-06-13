package it.polimi.ingsw.GC_26_cards.leaderCard;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_personalBoard.PersonalBoard;
import it.polimi.ingsw.GC_26_player.Player;

public class CardNumbersOrRequirement implements Requirement{
	private final int territoryCardsRequired;
	private final int buildingCardsRequired;
	private final int characterCardsRequired;
	private final int ventureCardsRequired;
	
	
	public CardNumbersOrRequirement(int territoryCardsRequired, int buildingCardsRequired, int characterCardsRequired,
			int ventureCardsRequired) {
		this.territoryCardsRequired = territoryCardsRequired;
		this.buildingCardsRequired = buildingCardsRequired;
		this.characterCardsRequired = characterCardsRequired;
		this.ventureCardsRequired = ventureCardsRequired;
	}

	@Override
	public synchronized boolean checkRequirement(Player player) {
		PersonalBoard personalBoard =player.getPersonalBoard();
		return (personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.TERRITORYCARD)>=territoryCardsRequired && territoryCardsRequired!=0) ||
				(personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.BUILDINGCARD)>=buildingCardsRequired && buildingCardsRequired!=0) ||
				(personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.CHARACTERCARD)>=characterCardsRequired && characterCardsRequired!=0) ||
				(personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.VENTURECARD)>=ventureCardsRequired&& ventureCardsRequired!=0);
	}
	
	@Override
	public String toString() {
		   //returns only the non 0 fields.
			StringBuilder temp = new StringBuilder("Playr needs one condition true out of these: ");
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
