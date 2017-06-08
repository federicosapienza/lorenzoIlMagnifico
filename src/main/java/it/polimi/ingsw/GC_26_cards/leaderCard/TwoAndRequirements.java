package it.polimi.ingsw.GC_26_cards.leaderCard;

import it.polimi.ingsw.GC_26_player.Player;

//used only by Cesare Borgia
public class TwoAndRequirements implements Requirement{
	
	private final Requirement requirement1;
	private final Requirement requirement2;
	
	public TwoAndRequirements(Requirement requirement1, Requirement requirement2) {
		this.requirement1 = requirement1;
		this.requirement2 = requirement2;
	}

	@Override
	public boolean checkRequirement(Player player) {
		return requirement1.checkRequirement(player) && requirement2.checkRequirement(player);
	}
	
	@Override
	public String toString() {
		return "Player needs "+requirement1.toString()+" and "+requirement2.toString();
	}
	

}
