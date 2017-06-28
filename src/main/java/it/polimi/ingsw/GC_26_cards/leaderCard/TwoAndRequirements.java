package it.polimi.ingsw.GC_26_cards.leaderCard;

import it.polimi.ingsw.GC_26_player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that implements the Requirement interface and represents a particular requirement present only in 
 * the Cesare Borgia Leader card: it checks if the player can satisfy both 2 types of requirements.
 *
 */
public class TwoAndRequirements implements Requirement{
	
	private final Requirement requirement1;
	private final Requirement requirement2;
	
	/**
	 * Constructor: it creates a requirement composed by two normal requirements
	 * @param requirement1 It's the first requirement to satisfy
	 * @param requirement2 It's the second requirement to satisfy
	 */
	public TwoAndRequirements(Requirement requirement1, Requirement requirement2) {
		this.requirement1 = requirement1;
		this.requirement2 = requirement2;
	}

	/**
	 * Method that checks if the player contained in the parameter can satisfy both the requirement1 and the requirement2
	 */
	@Override
	public boolean checkRequirement(Player player) {
		return requirement1.checkRequirement(player) && requirement2.checkRequirement(player);
	}
	
	/**
	 * Method that explains the conditions of the requirement as a string
	 */
	@Override
	public String toString() {
		return "Player needs "+requirement1.toString()+" and "+requirement2.toString();
	}
	

}
