package it.polimi.ingsw.GC_26_gameLogic;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;

public class ActionTest {
	
	Action action = new Action(BoardZone.TERRITORYTOWER, 2, Colour.BLACK, 0);
	@Test
	public void test() {
		assertNotNull(action);
		assertTrue(Colour.BLACK==action.getFamilyMemberColour());
		assertEquals(0, action.getServantsUsed());
		assertEquals(2, action.getPosition());
		assertEquals(BoardZone.TERRITORYTOWER, action.getZone());
	}

}
