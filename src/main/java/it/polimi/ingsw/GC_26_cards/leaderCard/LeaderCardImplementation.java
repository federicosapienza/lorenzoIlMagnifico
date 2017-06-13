package it.polimi.ingsw.GC_26_cards.leaderCard;

import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_player.Player;

public class LeaderCardImplementation implements LeaderCard{
	/**
	 * 
	 */
	private final String name;
	private final Requirement requirement;
	private final Effect effect;
	
	public LeaderCardImplementation(String name , Requirement requirement, Effect effect) {
		this.name=name;
		this.effect= effect;
		this.requirement=requirement;
	}
	

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public boolean checkRequirement(Player player) {
		return requirement.checkRequirement(player);
	}
	@Override
	public void  runEffect(Player player) {
		effect.doEffect(player, false);
	}
	
	
	public String getPermanentEffectDescriber() {
		return effect.toString();
	}


	
	public String getRequirementDescriber() {
		return requirement.toString();
	}
}
