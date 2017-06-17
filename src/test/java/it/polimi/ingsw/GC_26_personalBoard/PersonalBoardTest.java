package it.polimi.ingsw.GC_26_personalBoard;

import static org.junit.Assert.*;

import org.junit.Test;
import it.polimi.ingsw.GC_26_cards.*;
import it.polimi.ingsw.GC_26_cards.payments.*;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCardImplementation;
import it.polimi.ingsw.GC_26_cards.payments.ResourcesPayment;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_readJson.TerritoryCardsReader;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class PersonalBoardTest {
	
	
	PersonalBoard personalBoard = new PersonalBoard();
	
	@Test
	public void testNotNull() {
		
		assertNotNull(personalBoard);
	}
	
	@Test
	public void testCorrectAddLeaderCard() {
		LeaderCardImplementation nullLeaderCard = null;
		boolean thrownNullExcep = false;
		try {
			personalBoard.addLeaderCard(nullLeaderCard);
		} catch (NullPointerException e) {
			thrownNullExcep = true;
		}
		assertTrue(thrownNullExcep);
	}
	
	@Test
	public void testCorrectAddDevCard() {
		DevelopmentCardImplementation nullDevCard = null;
		boolean thrownNullExcep = false;
		try {
			personalBoard.add(nullDevCard);
		} catch (NullPointerException e) {
			thrownNullExcep = true;
		}
		assertTrue(thrownNullExcep);
	}
	
	@Test
	public void testCorrectSetBonusTile() {
		PersonalBoardTile nullPersonalBoardTile = null;
		boolean thrownNullExcep = false;
		try {
			personalBoard.setPersonalBoardTile(nullPersonalBoardTile);
		} catch (NullPointerException e) {
			thrownNullExcep = true;
		}
		assertTrue(thrownNullExcep);
		
		PersonalBoardTile personalBoardTile = new PersonalBoardTile(ResourcesOrPoints.newResourcesOrPoints(2, 0, 0, 0, 0, 1,0, 0), ResourcesOrPoints.newResources(0, 1, 1, 1));
		personalBoard.setPersonalBoardTile(personalBoardTile);
		assertEquals(personalBoardTile, personalBoard.getPersonalBoardTile());
		
	}
	
	@Test
	public void testCorrectProductionBonus() {
		PersonalBoard testPersonalBoard = new PersonalBoard();
		Player player = new Player("Ricky", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
		PersonalBoardTile personalBoardTile = new PersonalBoardTile(ResourcesOrPoints.newResourcesOrPoints(2, 0, 0, 0, 0, 1,0, 0), ResourcesOrPoints.newResources(0, 1, 1, 1));
		testPersonalBoard.setPersonalBoardTile(personalBoardTile);
		testPersonalBoard.getPersonalBoardTile().giveProductionBonus(player);
		assertEquals(7, player.getWarehouse().getCoins());
		assertEquals(1, player.getWarehouse().getMilitaryPoints());
	}
	
	
	
	
}
