package it.polimi.ingsw.GC_26_utilities.familyMembers;
import it.polimi.ingsw.GC_26_utilities.dices.*;
import java.util.Set;

<<<<<<< HEAD
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.dices.Dices;
=======
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.dices.Dice;
import it.polimi.ingsw.GC_26_utilities.dices.DicesSet;

>>>>>>> fcfd1c1935be0eb0dad88e215df8ff8270013bc4
public class FamilyMembers {
	FamilyMember orangeMember;
	FamilyMember whiteMember;
	FamilyMember blackMember;
	FamilyMember neutralMember;
	public void FamilyMembersSet(Player player) {
		orangeMember = new FamilyMember(Colour.ORANGE);
		whiteMember = new FamilyMember(Colour.WHITE);
		blackMember = new FamilyMember(Colour.BLACK);
		neutralMember = new FamilyMember(Colour.NEUTRAL);
	}
	public Set whatIsFree() {
		//TODO
	}
	public void setValues(Dices dices) {
		orangeMember.getValue();
		blackMember.getValue();
		whiteMember.getValue();
		neutralMember.getValue();
	}
	public boolean isFree() {
		
		return true;
	}
	public Setvalues(DicesSet dices){
		dices.throwDices();
		dices.read(Colour.ORANGE)
	}
	
	public setDices()
	public void setUsed(Colour colour) {
		
	}
	
	public void setUnusedAll() {
		//da implementare
	}

}
