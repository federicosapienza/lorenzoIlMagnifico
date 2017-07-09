package it.polimi.ingsw.gc_26.model.game.game_components.familyMembers;
import it.polimi.ingsw.gc_26.model.game.game_components.dices.Colour;
import it.polimi.ingsw.gc_26.model.player.Player;
/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that represents the single family member of a player.
 */

public class FamilyMember {
	
	// this is the value of the family member, which is the same of the corresponding dice
	private int value;
	// this denotes if the family member is free, i.e. ready to be used (it can be used only once for each round)
	private boolean free=true;
	// this is the colour of the family member
	private Colour colour;
	
	// this is the player that owns this family member
	Player player;
	
	/**
	 * Constructor that creates a family member 
	 * @param colour the colour of the family member
	 * @param player It's the player that this family member belongs to.
	 */
	public FamilyMember(Colour colour, Player player) {
		if(colour == null || player == null)
			throw new NullPointerException("null parameter"); 
		this.colour = colour;
		this.player= player;
		this.value=0;
	}
	
	/**
	 * Method that checks if the family member is free and it returns the result of this check.
	 * @return true if the family member hasn't been used in this round; 
	 * false if the family member has been already used in this round.
	 */
	public boolean isFree() {
		return free;
		
	}
	
	/**
	 * Method that returns the colour of the family member
	 * @return the colour of the family member
	 */
	public Colour getColour() {
		return colour;
	}
	
	/**
	 * Method that sets the value of the family member to an int.
	 * @param value It's the value that will be assigned to the family member.
	 */
	public void setValue(int value) {
		if(value<0)
			throw new IllegalArgumentException("value must be greater than 1"); 
		this.value = value;
	}
	
	/**
	 * Method that returns the value of the family member
	 * @return the value of the family member
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Method that records when the family member is used, setting the attribute free to false. 
	 */
	public void setUsed() {
		this.free = false;
	}
	
	/**
	 * Method that resets the freedom of a family member, setting free to true (it has to be used for every beginning of a round)
	 */
	public void setFree() {
		this.free = true;
	}
	
	/**
	 * Method that returns the player who owns this family member.
	 * @return the player who owns this family member.
	 */
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
		   FamilyMember other = (FamilyMember) obj;
		   return player.equals(other.getPlayer()) && colour== other.getColour() ;
	}
	
	 @Override
	public int hashCode() {
		return super.hashCode();
	}
	
}
