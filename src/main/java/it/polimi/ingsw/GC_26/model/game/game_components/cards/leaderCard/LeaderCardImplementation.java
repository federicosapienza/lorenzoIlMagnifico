package it.polimi.ingsw.GC_26.model.game.game_components.cards.leaderCard;

import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.Effect;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class implements the LeaderCard interface
 *
 */
public class LeaderCardImplementation implements LeaderCard{
	/**
	 * 
	 */
	private final String name;
	private final Requirement requirement;
	private final Effect immediateEffect;
	private final Effect permanentEffect;

	/**
	 * Constructor: it creates the Leader card, with the name, the requirement, the immediate effect and the permanent effect
	 * as indicated in the parameters
	 * @param name the name of the Leader card
	 * @param requirement the requirement that the player has to satisfy to get the card
	 * @param immediateEffect the immediate effect applied by the card
	 * @param permanentEffect the permanent effect applied by the card
	 */
	public LeaderCardImplementation(String name , Requirement requirement, Effect immediateEffect, Effect permanentEffect) {
		this.name=name;
		this.immediateEffect= immediateEffect;
		this.permanentEffect=permanentEffect;
		this.requirement=requirement;
	}
	
	/**
	 * Method that returns the name of the Leader card
	 * @return the name of the Leader card
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * Method that checks if the player contained in the parameter can satisfy the requirement of the Leader card
	 */
	@Override
	public boolean checkRequirement(Player player) {
		return requirement.checkRequirement(player);
	}
	
	/**
	 * Method called to apply the immediate effect on the player contained in the parameter
	 */
	@Override
	public void runImmediateEffect(Player player) {
		if(immediateEffect!=null){
			immediateEffect.doEffect(player, false);
		}
	}
	
	/**
	 * Method called to apply the permanent effect on the player contained in the parameter
	 */
	@Override
	public void runPermanentEffect(Player player) {
		if(permanentEffect!=null){
			permanentEffect.doEffect(player, false);
		}
	}
	
	/**
	 * Method that returns the description of the immediate effect of the Leader card as a string. If the immediate effect
	 * is null, it returns null.
	 * @return he description of the immediate effect of the Leader card as a string if the effect isn't null;
	 * null if the effect is null.
	 */
	public String getImmediateEffectDescriber() {
		if(immediateEffect!=null){
			return immediateEffect.toString();
		}
		else return null;
	}

	/**
	 * Method that returns the description of the permanent effect of the Leader card as a string. If the permanent effect
	 * is null, it returns null.
	 * @return he description of the permanent effect of the Leader card as a string if the effect isn't null;
	 * null if the effect is null.
	 */
	public String getPermanentEffectDescriber() {
		if(permanentEffect!=null){
			return permanentEffect.toString();
		}
		else return null;
	}

	
	/**
	 * Method that returns the description of the requirement as a string. If the requirement is null, it returns null
	 * @return the description of the requirement as a string if the requirement isn't null; null if the requirement is null
	 */
	public String getRequirementDescriber(){
		if(requirement!=null){
			return requirement.toString();
		}
		else return null;
	}

	/**
	 * Method that checks if the Leader card has a permanent effect.
	 * @return true if there is permanent effect, which means permanentEffect is not null; false if there isn't any
	 * permanent effect, which means permanentEffect==null.
	 */
	@Override
	public boolean hasAPermanentEffect() {
		return permanentEffect!=null; //if null it will means there is an immediate effect
	}
}
