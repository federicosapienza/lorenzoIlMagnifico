package it.polimi.ingsw.GC_26;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_gameLogic.Game;
import it.polimi.ingsw.GC_26_gameLogic.Round;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.dices.Dices;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembers;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class PlayerTest {
	
	@Test
	public void test() {
		Dices dices = new Dices();
		
		Player player1 = new Player("Luke", ResourcesOrPoints.newResources(5, 3, 2, 2));
		Player player2 = new Player("Ivan", ResourcesOrPoints.newResources(6, 3, 2, 2));
		dices.throwDices();
		player1.getFamilyMembers().setValues(dices);
		player2.getFamilyMembers().setValues(dices);
		assertTrue(player1.getFamilyMembers().getfamilyMember(Colour.BLACK).getValue() == player2.getFamilyMembers().getfamilyMember(Colour.BLACK).getValue());
		assertTrue(player1.getFamilyMembers().getfamilyMember(Colour.WHITE).getValue() == player2.getFamilyMembers().getfamilyMember(Colour.WHITE).getValue());
		assertNotEquals(player1.getFamilyMembers(), player2.getFamilyMembers());
	}

}
