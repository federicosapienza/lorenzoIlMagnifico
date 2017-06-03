package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class PermanentActionIncrementEffect implements Effect{
	private final BoardZone zone;
	private final int value;
	private final ResourcesOrPoints discount;
	
	public BoardZone getZone() {
		return zone;
	}
	public ResourcesOrPoints getDiscount() {
		return discount;
	}

	public PermanentActionIncrementEffect(BoardZone zone, int value , ResourcesOrPoints discount) {
		this.zone = zone;
		this.value= value;
		this.discount = discount;
	}

	@Override
	public synchronized void doEffect(Player player, boolean immediate) {
		player.getPermanentModifiers().addModifier(zone, value);
		
	}

}
