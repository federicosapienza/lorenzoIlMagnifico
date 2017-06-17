package it.polimi.ingsw.GC_26_utilities.familyMembers;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class FamilyMemberTest {

	@Test
	public void testColourException() {
		Colour colour = null;
		boolean thrownException = false;
		try {
			FamilyMember nullMember = new FamilyMember(colour, new Player("Antonio", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0)));
		} catch (NullPointerException e) {
			thrownException = true;
		}
		
	}

	@Test
	public void testNullPlayer() {
		Colour colour = Colour.BLACK;
		boolean thrownException = false;
		try {
			FamilyMember nullMember = new FamilyMember(colour, null);
		} catch (NullPointerException e) {
			thrownException = true;
		}
	}
	
	@Test
	public void testCorrectCreation() {
		Colour colour = Colour.ORANGE;
		Player justine = new Player("Justine", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
		FamilyMember correctFamilyMember = new FamilyMember(colour, justine);
		assertNotNull(correctFamilyMember);
		assertEquals(justine, correctFamilyMember.getPlayer());
		assertEquals(colour, correctFamilyMember.getColour());
		assertTrue(correctFamilyMember.isFree());
	}
	
	@Test
	public void testIllegalValue() {
		boolean thrownException = false;
		Colour colour = Colour.WHITE;
		Player player1 = new Player("Greg", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
		FamilyMember familyMember = new FamilyMember(colour, player1);
		familyMember.setValue(3);
		assertEquals(3, familyMember.getValue());
		try {
			familyMember.setValue(-1);
		} catch (IllegalArgumentException e) {
			thrownException = true;
		}
		assertTrue(thrownException);
	}
}
