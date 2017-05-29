package it.polimi.ingsw.GC_26_utilities.familyMembers;
import it.polimi.ingsw.GC_26_player.*;
import static org.junit.Assert.*;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.*;
import org.junit.Test;
import it.polimi.ingsw.GC_26_utilities.dices.*;
public class FamilyMembersTest {
	Dices dices = new Dices();
    FamilyMembers familyMembersSet;
    Player player;
    ResourcesOrPoints startingResources;
    @Test
    public void test() {
    	familyMembersSet = new FamilyMembers(player);
    	dices.throwDices();
    	int whiteValue = dices.readDice(Colour.WHITE);
    	int blackValue = dices.readDice(Colour.BLACK);
    	int orangeValue = dices.readDice(Colour.ORANGE);
    	familyMembersSet.setValues(dices);
    	assertEquals(blackValue, familyMembersSet.getfamilyMember(Colour.BLACK).getValue());;
    	assertEquals(whiteValue, familyMembersSet.getfamilyMember(Colour.WHITE).getValue());
    	assertEquals(orangeValue, familyMembersSet.getfamilyMember(Colour.ORANGE).getValue());
    	assertEquals(0, familyMembersSet.getfamilyMember(Colour.NEUTRAL).getValue());
    }

}
