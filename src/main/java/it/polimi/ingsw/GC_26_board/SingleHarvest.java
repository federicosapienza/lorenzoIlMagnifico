package it.polimi.ingsw.GC_26_board;

import it.polimi.ingsw.GC_26_player.Player;
//import userboard

public class SingleHarvest extends SinglePosition {

	public SingleHarvest(int valueOfPosition) {
		super(valueOfPosition);
	}

	@Override
	public void clear() {
		setFamilyMember(null);
	}
	
	public void doSingleHarvest(Player player){
		int value=getValueOfPosition();
		//for che scorre le carte della userboard
		//due if,uno che controlla valore dado evalore position 
		//uno che controlla valore dado,valore carta
	}

}
