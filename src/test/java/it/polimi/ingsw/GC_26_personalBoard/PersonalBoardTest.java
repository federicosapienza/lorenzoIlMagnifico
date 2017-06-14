package it.polimi.ingsw.GC_26_personalBoard;

import static org.junit.Assert.*;

import org.junit.Test;
import it.polimi.ingsw.GC_26_cards.*;
import it.polimi.ingsw.GC_26_cards.payments.*;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.payments.ResourcesPayment;
import it.polimi.ingsw.GC_26_readJson.TerritoryCardsReader;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class PersonalBoardTest {
	
	DevelopmentCard card1;
	DevelopmentCard card2;
	DevelopmentCard card3;
	DevelopmentCard card4; 
	DevelopmentCardImplementation devCard1;
	DevelopmentCardImplementation devCard2;
	DevelopmentCardImplementation devCard3;
	DevelopmentCardImplementation devCard4;
	PersonalBoard personalBoard = new PersonalBoard();
	
	@Test
	public void testCreation() {
		assertNotNull(personalBoard);
	}
	
	/**
	@Test
	public void testCountingCards() {
		PersonalBoard personalBoard = new PersonalBoard();
		card1 = devCard1.territoryCard("woods", 1, null, null, null, 2);
		card2 = devCard2.buildingCard("Mint", 1, null, null, null, 0);
		card3 = devCard3.characterCard("Warloard", 1, null, null, null);
		card4 = devCard4.ventureCard("Hiring Recruits", 1, null, null, null);
		
		personalBoard.add(card1);
		
		assertEquals(1, personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.TERRITORYCARD));
		
	}
	
	*/
}
