package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_player.Player;


//malus shows how many victory points are needed for losing one victory point
public class LoseVictoryPointsforEachNVictoryPointsEffect implements Effect{
	int malus;

	@Override
	public void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().setVictoryPointsReducer(malus);
		
	}
	
	
	@Override
	public String toString() {
		return " before the Final Scoring, you lose 1 Victory Point for every"+  malus+" Victory Points you have";
	}

}
