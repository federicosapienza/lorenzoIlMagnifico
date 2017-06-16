package it.polimi.ingsw.GC_26_utilities.familyMembers;
import it.polimi.ingsw.GC_26_player.*;
import static org.junit.Assert.*;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.*;
import org.junit.Test;
import it.polimi.ingsw.GC_26_utilities.dices.*;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembers;
public class FamilyMembersTest {
	Dices dices = new Dices();
    FamilyMembers familyMembersSet;
    Player player;
    ResourcesOrPoints startingResources= ResourcesOrPoints.newResources(1, 1, 0, 0);
    
    @Test
    public void testValues() {
    	dices.rollDices();
    	int whiteValue = dices.readDice(Colour.WHITE);
    	int blackValue = dices.readDice(Colour.BLACK);
    	int orangeValue = dices.readDice(Colour.ORANGE);
    	player =new Player("test", startingResources);
    	familyMembersSet = player.getFamilyMembers();
    	familyMembersSet.setValues(dices);
    	assertEquals(blackValue, familyMembersSet.getfamilyMember(Colour.BLACK).getValue());
    	assertEquals(whiteValue, familyMembersSet.getfamilyMember(Colour.WHITE).getValue());
    	assertEquals(orangeValue, familyMembersSet.getfamilyMember(Colour.ORANGE).getValue());
    	assertEquals(0, familyMembersSet.getfamilyMember(Colour.NEUTRAL).getValue());
    	assertTrue(familyMembersSet.getfamilyMember(Colour.BLACK).getValue() == blackValue || familyMembersSet.getfamilyMember(Colour.BLACK).getValue() == player.getPermanentModifiers().getValue3dicesChanged() + player.getPermanentModifiers().getColouredMemberChange() || familyMembersSet.getfamilyMember(Colour.BLACK).getValue() == player.getPermanentModifiers().getColouredMemberChange() + player.getPermanentModifiers().getValue1diceChanged());
    	assertTrue(familyMembersSet.getfamilyMember(Colour.ORANGE).getValue() == orangeValue || familyMembersSet.getfamilyMember(Colour.ORANGE).getValue() == player.getPermanentModifiers().getValue3dicesChanged() + player.getPermanentModifiers().getColouredMemberChange() || familyMembersSet.getfamilyMember(Colour.ORANGE).getValue() == player.getPermanentModifiers().getColouredMemberChange() + player.getPermanentModifiers().getValue1diceChanged());
    	assertTrue(familyMembersSet.getfamilyMember(Colour.WHITE).getValue() == whiteValue || familyMembersSet.getfamilyMember(Colour.WHITE).getValue() == player.getPermanentModifiers().getValue3dicesChanged() + player.getPermanentModifiers().getColouredMemberChange() || familyMembersSet.getfamilyMember(Colour.WHITE).getValue() == player.getPermanentModifiers().getColouredMemberChange() + player.getPermanentModifiers().getValue1diceChanged());
    	assertTrue(familyMembersSet.getfamilyMember(Colour.NEUTRAL).getValue() == 0 || familyMembersSet.getfamilyMember(Colour.NEUTRAL).getValue() == player.getPermanentModifiers().getneutralMemberChange());
    }

}
