package it.polimi.ingsw.GC_26_utilities.familyMembers;
import it.polimi.ingsw.GC_26_player.*;
import static org.junit.Assert.*;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.*;
import org.junit.Test;
import it.polimi.ingsw.GC_26_utilities.dices.*;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembers;
public class FamilyMembersTest {
	
	Dices dices = new Dices();
	Player player = new Player("Ivan", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
	
	
	@Test
	public void testNotNullConstructor(){
		boolean thrownException = false;
		try {
			FamilyMembers nullFamilyMembers = new FamilyMembers(null); 
		} catch (NullPointerException e) {
			thrownException = true;
		}
		assertTrue(thrownException);
	}
	
	@Test
	public void testNotNullValues() {
		boolean thrownException = false;
		try {
			FamilyMembers familyMembers = new FamilyMembers(player);
			familyMembers.setValues(null);
		} catch (NullPointerException e) {
			thrownException = true;
		}
		assertTrue(thrownException);
	}
	
	@Test
	public void testCorrectOwner() {
		FamilyMembers familyMembers = new FamilyMembers(new Player("Lucia", ResourcesOrPoints.newResourcesOrPoints(6, 3, 2, 2, 0, 0, 0, 0)));
		assertEquals("Lucia", familyMembers.getPlayerName());
	}
	
	@Test
	public void testFreeMembers() {
		FamilyMembers familyMembers = new FamilyMembers(new Player("Gregory", ResourcesOrPoints.newResourcesOrPoints(7, 3, 2, 2, 0, 0, 0, 0)));
		assertTrue(familyMembers.getfamilyMember(Colour.BLACK).isFree());
		familyMembers.getfamilyMember(Colour.BLACK).setUsed();
		assertFalse(familyMembers.getfamilyMember(Colour.BLACK).isFree());
	}
	
	@Test
	public void testCorrectValues(){
		dices.rollDices();
		int whiteValue = dices.readDice(Colour.WHITE);
		int blackValue = dices.readDice(Colour.BLACK);
		int orangeValue = dices.readDice(Colour.ORANGE);
		FamilyMembers familyMembersSet = player.getFamilyMembers();
		familyMembersSet.setValues(dices);
		assertEquals(blackValue, familyMembersSet.getfamilyMember(Colour.BLACK).getValue());
		assertEquals(whiteValue, familyMembersSet.getfamilyMember(Colour.WHITE).getValue());
		assertEquals(orangeValue, familyMembersSet.getfamilyMember(Colour.ORANGE).getValue());
		assertEquals(0, familyMembersSet.getfamilyMember(Colour.NEUTRAL).getValue());
		/*
		assertTrue(familyMembersSet.getfamilyMember(Colour.BLACK).getValue() == blackValue || familyMembersSet.getfamilyMember(Colour.BLACK).getValue() == player.getPermanentModifiers().getValue3dicesChanged() + player.getPermanentModifiers().getColouredMemberChange() || familyMembersSet.getfamilyMember(Colour.BLACK).getValue() == player.getPermanentModifiers().getColouredMemberChange() + player.getPermanentModifiers().getValue1diceChanged());
		assertTrue(familyMembersSet.getfamilyMember(Colour.ORANGE).getValue() == orangeValue || familyMembersSet.getfamilyMember(Colour.ORANGE).getValue() == player.getPermanentModifiers().getValue3dicesChanged() + player.getPermanentModifiers().getColouredMemberChange() || familyMembersSet.getfamilyMember(Colour.ORANGE).getValue() == player.getPermanentModifiers().getColouredMemberChange() + player.getPermanentModifiers().getValue1diceChanged());
		assertTrue(familyMembersSet.getfamilyMember(Colour.WHITE).getValue() == whiteValue || familyMembersSet.getfamilyMember(Colour.WHITE).getValue() == player.getPermanentModifiers().getValue3dicesChanged() + player.getPermanentModifiers().getColouredMemberChange() || familyMembersSet.getfamilyMember(Colour.WHITE).getValue() == player.getPermanentModifiers().getColouredMemberChange() + player.getPermanentModifiers().getValue1diceChanged());
		assertTrue(familyMembersSet.getfamilyMember(Colour.NEUTRAL).getValue() == 0 || familyMembersSet.getfamilyMember(Colour.NEUTRAL).getValue() == player.getPermanentModifiers().getneutralMemberChange());
		*/
    }
	
	@Test
	public void testFreeMembersSet(){
		FamilyMembers familyMembers = new FamilyMembers(new Player("Rebecca", ResourcesOrPoints.newResourcesOrPoints(8, 3, 2, 2, 0, 0, 0, 0)));
		assertTrue(familyMembers.whatIsFree().contains(Colour.BLACK));
		assertTrue(familyMembers.whatIsFree().contains(Colour.ORANGE));
		assertTrue(familyMembers.whatIsFree().contains(Colour.WHITE));
		assertTrue(familyMembers.whatIsFree().contains(Colour.NEUTRAL));
		familyMembers.getfamilyMember(Colour.BLACK).setUsed();
		assertFalse(familyMembers.whatIsFree().contains(Colour.BLACK));
	}
	
    
    
    

}
