package it.polimi.ingsw.GC_26_cards.excommunicationTile;

import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_player.Player;

//TODO
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
	public ExcommunicationTile copy() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
