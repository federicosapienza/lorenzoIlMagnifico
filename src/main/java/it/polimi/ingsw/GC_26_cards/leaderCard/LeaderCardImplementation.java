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
		if(permanentEffect!=null){
			permanentEffect.doEffect(player, false);
		}
	}
	
	
	public String getImmediateEffectDescriber() {
		if(immediateEffect!=null){
			return immediateEffect.toString();
		}
		else return null;
	}

	public String getPermanentEffectDescriber() {
		if(permanentEffect!=null){
			return permanentEffect.toString();
		}
		else return null;
	}

	

	public String getRequirementDescriber(){
		if(requirement!=null){
			return requirement.toString();
		}
		else return null;
	}


	@Override
	public boolean isAPermanentEffect() {
		return permanentEffect!=null; //if null it will means there is an immediate effect
	}
}
