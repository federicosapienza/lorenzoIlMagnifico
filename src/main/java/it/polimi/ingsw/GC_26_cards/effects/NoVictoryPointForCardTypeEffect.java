package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_player.Player;

public class NoVictoryPointForCardTypeEffect implements Effect{
	private DevelopmentCardTypes type;
	
	public NoVictoryPointForCardTypeEffect(DevelopmentCardTypes type) {
		this.type= type;
	}

	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().noVictoryPointsForCardType(type);
	}
	
	@Override
	public String toString() {
		return "Player don’t score points for any of "+ type.toString()+ " card"; 
	}
	
	
	

}
