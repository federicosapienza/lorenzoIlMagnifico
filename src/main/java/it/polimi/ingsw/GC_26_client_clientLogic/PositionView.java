package it.polimi.ingsw.GC_26_client_clientLogic;

import java.util.HashMap;
import java.util.Map;


import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_board.PositionDescriber;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;

public class PositionView {
	private final PositionDescriber describer;
	private final Map<String, Colour> playersHere;
	private CardDescriber cardHere;
	
	public PositionView(PositionDescriber describer) {  //decorator pattern
		this.describer= describer;
		playersHere= new HashMap<>();
		
	}
	
	
	public void setCardHere(CardDescriber cardHere) {
		this.cardHere = cardHere;
	}
	
	public void setPlayerhere(String player, Colour colour) {
		playersHere.put(player, colour);
	}
	
	public void endRound(){
		cardHere= null;
		playersHere.clear();
	}
	
	
	//don ' t like it
	public CardDescriber getCardHere() {
		return cardHere;
	}
	
	public PositionDescriber getPositionDescriber(){
		return describer;
	}
	
	public Map<String, Colour> getPlayersHere() {
		return playersHere;
	}
	
	public BoardZone getZone() {
		return describer.getZone();
	}
	public int getPositionIndex(){
		return describer.getPosition();
	}
	public String getBonusPosition(){
		return describer.getResourcesOrPointsOnPosition();
	}
	
	public int getPositionValue(){
		return describer.getValue();
	}
}
