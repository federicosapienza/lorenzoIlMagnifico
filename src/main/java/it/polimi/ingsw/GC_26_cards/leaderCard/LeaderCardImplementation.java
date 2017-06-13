package it.polimi.ingsw.GC_26_cards.leaderCard;

import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_player.Player;

public class LeaderCardImplementation implements LeaderCard{
	/**
	 * 
	 */
	private final String name;
	private final Requirement requirement;
	private final Effect immediateEffect;
	private final Effect permanentEffect;

	
	public LeaderCardImplementation(String name , Requirement requirement, Effect immediateEffect, Effect permanentEffect) {
		this.name=name;
		this.immediateEffect= immediateEffect;
		this.permanentEffect=permanentEffect;
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
	public void  runImmediateEffect(Player player) {
		if(immediateEffect!=null){
			immediateEffect.doEffect(player, false);
		}
	}
	
	
	@Override
	public void  runPermanentEffect(Player player) {
		if(immediateEffect!=null){
			immediateEffect.doEffect(player, false);
		}
	}
	
	
	public String getImmediateEffectDescriber() {
		return immediateEffect.toString();
	}

	public String getPermanentEffectDescriber() {
		return immediateEffect.toString();
	}
	
	public String getRequirementDescriber() {
		return requirement.toString();
	}
}
