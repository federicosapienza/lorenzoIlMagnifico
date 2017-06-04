package it.polimi.ingsw.GC_26_cards.leaderCard;

import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_player.Player;

public class LeaderCardImplementation implements LeaderCard{
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
	


	@Override
	public String getTypeOfCard() {
		return "Leader Card";
	}


	@Override
	public int getActionValue() {
		return 0;
	}


	@Override
	public String getCardType() {
		return null;
	}


	@Override
	public int getPeriod() {
		return 0;
	}


	@Override
	public String getImmediateEffectDescriber() {
		return null;
	}


	@Override
	public String getPermanentEffectDescriber() {
		return effect.toString();
	}


	@Override
	public String getRequirementDescriber() {
		return requirement.toString();
	}
}
