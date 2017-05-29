package it.polimi.ingsw.GC_26_player;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_gameLogic.Game;
import it.polimi.ingsw.GC_26_gameLogic.Round;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.dices.Dices;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembers;

public class PlayerTest {
	
	@Test
	public void test() {
		Dices dices = new Dices();
		FamilyMembers familyMembers1 = new FamilyMembers();
		FamilyMembers familyMembers2 = new FamilyMembers();
		Player player1 = new Player(1, "player1");
		Player player2 = new Player(2, "player2");
		dices.throwDices();
		familyMembers1.FamilyMembersSet(player1);
		familyMembers2.FamilyMembersSet(player2);
		familyMembers1.setValues(dices);
		familyMembers2.setValues(dices);
		player1.getFamilyMembers();
		player2.getFamilyMembers();
		assertTrue(familyMembers1.getValue(Colour.BLACK) == familyMembers2.getValue(Colour.BLACK));
		assertNotEquals(player1.getFamilyMembers(), player2.getFamilyMembers());
	}

}
