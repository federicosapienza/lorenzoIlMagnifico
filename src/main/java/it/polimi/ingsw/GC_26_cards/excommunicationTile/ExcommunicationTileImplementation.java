package it.polimi.ingsw.GC_26_cards.excommunicationTile;

import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_player.Player;

public class ExcommunicationTileImplementation implements ExcommunicationTile{
	private String IdCode;
	private Effect effect;
	
	
	@Override
	public String getIdCode() {
		return IdCode;
	}

	public Effect getEffect() {
		return effect;
	}
	@Override
	public void runEffect(Player player) {
		effect.doEffect(player, false);
	}

	@Override
	public String getTypeOfCard() {
		return "Excommunication Tile";
	}

	@Override
	public String getName() {
		return null;
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
		return null;
	}



}
