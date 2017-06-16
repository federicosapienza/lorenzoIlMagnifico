package it.polimi.ingsw.GC_26_player;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_gameLogic.Game;
import it.polimi.ingsw.GC_26_gameLogic.Round;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.dices.Dices;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembers;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class PlayerTest {
	Player player1 = new Player("Luke", ResourcesOrPoints.newResources(5, 3, 2, 2));
	Player player2 = new Player("Ivan", ResourcesOrPoints.newResources(6, 3, 2, 2));
	Dices dices = new Dices();
	Request request1 = new Request(PlayerStatus.PLAYING, "Player 1 is playing", null);
	Request request2 = new Request(PlayerStatus.CHOOSINGPAYMENT, "Player 1 is choosing the payment", null);
	@Test
	public void testExistence() {
		assertNotNull(player1);
		assertNotNull(player2);
	}
	
	@Test
	public void testValues(){
		dices.rollDices();
		player1.getFamilyMembers().setValues(dices);
		player2.getFamilyMembers().setValues(dices);
		assertTrue(player1.getFamilyMembers().getfamilyMember(Colour.BLACK).getValue() == player2.getFamilyMembers().getfamilyMember(Colour.BLACK).getValue());
		assertTrue(player1.getFamilyMembers().getfamilyMember(Colour.WHITE).getValue() == player2.getFamilyMembers().getfamilyMember(Colour.WHITE).getValue());
		assertNotEquals(player1.getFamilyMembers(), player2.getFamilyMembers());
	}
	
	@Test
	public void testStatus(){
		player1.setStatus(request1);
		assertNotNull(player1.getStatus());
		assertEquals(PlayerStatus.PLAYING, player1.getStatus());
		player2.setStatus(request2);
		assertNotNull(player2.getStatus());
		assertEquals(PlayerStatus.CHOOSINGPAYMENT, player2.getStatus());
	}

}
