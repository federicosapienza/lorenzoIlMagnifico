package it.polimi.ingsw.GC_26_cards.leaderCard;

import it.polimi.ingsw.GC_26_cards.effects.Effect;

public class LeaderCardImplementation implements LeaderCard{
	private final String name;
	private final Requirement requirement;
	private final Effect effect;
	
	public LeaderCardImplementation(String name , Requirement requirement, Effect effect) {
		this.name=name;
		this.effect= effect;
		this.requirement=requirement;
	}
	
	
	//TODO copy
	
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public Requirement getRequirement() {
		return requirement;
	}
	@Override
	public Effect getEffect() {
		return effect;
	}
	@Override
	public LeaderCard copy() {
		// TODO Auto-generated method stub
		return null;
	}
}
