package it.polimi.ingsw.gc_26.model.game.game_components.family_members;
import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.gc_26.model.game.game_components.dices.*;
import it.polimi.ingsw.gc_26.model.game.game_components.family_members.FamilyMembers;
import it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points.*;
import it.polimi.ingsw.gc_26.model.player.*;
public class FamilyMembersTest {
	
	Dices dices = new Dices();
	Player player = new Player("David", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testIllegalColourException(){
		FamilyMembers familyMembers = new FamilyMembers(player);
		familyMembers.getfamilyMember(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void testNotNullPlayerException() {
		FamilyMembers nullFamilyMembers = new FamilyMembers(null);
		nullFamilyMembers.getfamilyMember(Colour.BLACK);
	}
	
	@Test (expected = NullPointerException.class)
	public void testNullDicesException() {
		Dices nullDices = null;
		FamilyMembers familyMembers = new FamilyMembers(player);
		familyMembers.setValues(nullDices);
	}
	
	@Test
	public void testCorrectIntervalChangeValues() {
		FamilyMembers familyMembers = new FamilyMembers(player);
		dices.rollDices();
		familyMembers.setValues(dices);
		familyMembers.changeValues();
		assertTrue(familyMembers.getfamilyMember(Colour.BLACK).getValue() >0 && 
				familyMembers.getfamilyMember(Colour.BLACK).getValue() < 7 &&
				familyMembers.getfamilyMember(Colour.WHITE).getValue() > 0 &&
				familyMembers.getfamilyMember(Colour.WHITE).getValue() < 7 &&
				familyMembers.getfamilyMember(Colour.ORANGE).getValue() > 0 &&
				familyMembers.getfamilyMember(Colour.ORANGE).getValue() < 7 &&
				familyMembers.getfamilyMember(Colour.NEUTRAL).getValue() == 0);
	}
	
	@Test
	public void testCorrectValues(){
		dices.rollDices();
		int whiteValue = dices.readDice(Colour.WHITE);
		int blackValue = dices.readDice(Colour.BLACK);
		int orangeValue = dices.readDice(Colour.ORANGE);
		FamilyMembers familyMembersSet = player.getFamilyMembers();
		familyMembersSet.setValues(dices);
		assertTrue(blackValue == familyMembersSet.getfamilyMember(Colour.BLACK).getValue() &&
				whiteValue == familyMembersSet.getfamilyMember(Colour.WHITE).getValue() &&
				orangeValue == familyMembersSet.getfamilyMember(Colour.ORANGE).getValue() &&
				0 == familyMembersSet.getfamilyMember(Colour.NEUTRAL).getValue());
		
    }
	
	@Test
	public void testNoChangeValues() {
		FamilyMembers familyMembers = new FamilyMembers(player);
		dices.rollDices();
		familyMembers.setValues(dices);
		familyMembers.changeValues();
		int blackValue = familyMembers.getfamilyMember(Colour.BLACK).getValue();
		int orangeValue = familyMembers.getfamilyMember(Colour.ORANGE).getValue();
		int whiteValue = familyMembers.getfamilyMember(Colour.WHITE).getValue();
		int neutralValue = familyMembers.getfamilyMember(Colour.NEUTRAL).getValue();
		familyMembers.changeValues();
		assertTrue(familyMembers.getfamilyMember(Colour.BLACK).getValue() == blackValue &&
				familyMembers.getfamilyMember(Colour.ORANGE).getValue() == orangeValue &&
				familyMembers.getfamilyMember(Colour.WHITE).getValue() == whiteValue &&
				familyMembers.getfamilyMember(Colour.NEUTRAL).getValue() == neutralValue && neutralValue == 0);
	}
	@Test
	public void testSamePlayerNameButNotSamePlayer() {
		FamilyMembers familyMembers = new FamilyMembers(player);
		Player player2 = new Player("David", ResourcesOrPoints.newResources(6, 3, 2, 2));
		FamilyMembers familyMembers2 = new FamilyMembers(player2);
		assertTrue(familyMembers.getPlayerName() == familyMembers2.getPlayerName() && !familyMembers.getPlayer().equals(familyMembers2.getPlayer()));
	}
	
	
	
	
	
	
	
    
    
    

}
