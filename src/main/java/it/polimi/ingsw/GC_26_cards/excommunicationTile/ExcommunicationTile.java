package it.polimi.ingsw.GC_26_cards.excommunicationTile;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_player.Player;

public abstract class ExcommunicationTile implements CardDescriber{  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract void runEffect(Player player);
	
	
	
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
	public DevelopmentCardTypes getCardType() {
		return null;
	}

	

	@Override
	public String getImmediateEffectDescriber() {
		return null;
	}
	
	@Override
	public String getRequirementDescriber() {
		return null;
	}
	

}
