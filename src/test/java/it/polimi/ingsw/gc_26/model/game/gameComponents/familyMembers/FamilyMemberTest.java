package it.polimi.ingsw.gc_26.model.game.gameComponents.familyMembers;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.gc_26.model.game.game_components.dices.Colour;
import it.polimi.ingsw.gc_26.model.game.game_components.familyMembers.FamilyMember;
import it.polimi.ingsw.gc_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.player.Player;

public class FamilyMemberTest {

	@Test (expected = NullPointerException.class)
	public void testColourException() {
		Colour colour = null;
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		FamilyMember nullMember = new FamilyMember(colour, player);
		nullMember.setUsed();
	}

	@Test (expected = NullPointerException.class)
	public void testNullPlayer() {
		Colour colour = Colour.BLACK;
		FamilyMember nullMember = new FamilyMember(colour, null);
		nullMember.setValue(2);
	}
	
	@Test
	public void testCorrectFamilyMembersValues() {
		Colour colour = Colour.ORANGE;
		Player justine = new Player("Justine", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
		Player david = new Player("David", ResourcesOrPoints.newResources(6, 3, 2, 2));
		FamilyMember correctFamilyMember = new FamilyMember(colour, justine);
		FamilyMember correctFamilyMember2 = new FamilyMember(Colour.WHITE, david);
		correctFamilyMember2.setUsed();
		assertTrue(correctFamilyMember.isFree() && !correctFamilyMember2.isFree() && 
				!correctFamilyMember.equals(correctFamilyMember2) && correctFamilyMember.getColour() == colour && 
				correctFamilyMember2.getColour() == Colour.WHITE);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testIllegalValue() {
		Colour colour = Colour.WHITE;
		Player player1 = new Player("Greg", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
		FamilyMember familyMember = new FamilyMember(colour, player1);
		familyMember.setValue(-1);
	}
}
