package it.polimi.ingsw.gc_26.model.game.game_components.personal_board;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.leaderCard.LeaderCardImplementation;
import it.polimi.ingsw.gc_26.model.game.game_components.personal_board.PersonalBoard;
import it.polimi.ingsw.gc_26.model.game.game_components.personal_board.PersonalBoardTile;
import it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.player.Player;

public class PersonalBoardTest {
	
	
	PersonalBoard personalBoard = new PersonalBoard();
	
	
	
	@Test (expected = NullPointerException.class)
	public void testCorrectAddLeaderCard() {
		LeaderCardImplementation nullLeaderCard = null;
		personalBoard.addLeaderCard(nullLeaderCard);
		
	}
	
	@Test (expected = NullPointerException.class)
	public void testCorrectAddDevCard() {
		DevelopmentCardImplementation nullDevCard = null;
		personalBoard.add(nullDevCard);
		
	}
	
	@Test (expected = NullPointerException.class)
	public void testAnin() {
		PersonalBoardTile nullPersonalBoardTile = null;
		personalBoard.setPersonalBoardTile(nullPersonalBoardTile);
		
		
	}
	
	
	
	@Test
	public void testCorrectProductionBonus() {
		PersonalBoard testPersonalBoard = new PersonalBoard();
		Player player = new Player("Ricky", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
		PersonalBoardTile personalBoardTile = new PersonalBoardTile(ResourcesOrPoints.newResourcesOrPoints(2, 0, 0, 0, 0, 1,0, 0), ResourcesOrPoints.newResources(0, 1, 1, 1));
		testPersonalBoard.setPersonalBoardTile(personalBoardTile);
		testPersonalBoard.getPersonalBoardTile().giveProductionBonus(player);
		assertTrue(player.getWarehouse().getCoins() == 7 && player.getWarehouse().getMilitaryPoints() == 1);
	}
	
	
	
	
}
