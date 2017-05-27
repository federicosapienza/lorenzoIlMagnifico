package it.polimi.ingsw.GC_26_utilities.familyMembers;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
public class FamilyMember {
	private int value;
	private boolean free;
	private Colour colour;
	Player player;
	
	public FamilyMember(Colour colour, Player player) {
		this.colour = colour;
		this.player= player;
	}
	public boolean isFree() {
		if (this.free == true) {
			return true;
		}
		else{
			return false;
		}
	}
	
	public Colour getColour() {
		return colour;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
	public void setUsed() {
		this.free = false;
	}
	
	public void setFree() {
		this.free = true;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public boolean equals(Object obj) {
		   if (this==obj) 
			   return true;
		   if (obj == null)
			   return false;
		   if (this.getClass() != obj.getClass()) 
			   return false;
		   FamilyMember other =(FamilyMember) obj;
		   if(player.equals(other.getPlayer()) && colour== other.getColour())
			   return true;
		   else return false;   
	}
}
