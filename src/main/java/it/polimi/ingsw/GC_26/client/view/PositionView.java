package it.polimi.ingsw.GC_26.client.view;

import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.messages.describers.PositionDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.board.BoardZone;
import it.polimi.ingsw.GC_26.model.game.gameComponents.dices.Colour;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the Position View
 *
 */
public class PositionView {
	private final PositionDescriber describer;
	private final Map<String, Colour> playersHere;
	private CardDescriber cardHere;
	
	/**
	 * Constructor (with decorator pattern): it creates a PositionView described by the PositionDescriber contained in the
	 * parameter and a new Map to keep record of the players occupying the position
	 * @param describer It's the PositionDescriber that describes the current PositionView
	 */
	public PositionView(PositionDescriber describer) {  //decorator pattern
		this.describer = describer;
		playersHere = new HashMap<>();
	}
	
	/**
	 * Method used to set the CardDescriber contained in the parameter in the current PositionView
	 * @param cardHere It's the CardDescriber to set in the current PositionView
	 */
	public void setCardHere(CardDescriber cardHere) {
		this.cardHere = cardHere;
	}
	
	/**
	 * Method used to keep record of a player who sets a family member, whose colour is contained in the second 
	 * parameter, in this PositionView
	 * @param player It's the player whose family member occupies this PositionView
	 * @param colour It's the colour of the player's family member which occupies this PositionView 
	 */
	public void setPlayerhere(String player, Colour colour) {
		playersHere.put(player, colour);
	}
	
	/**
	 * Method called at the end of the round to remove everything from this PositionView
	 */
	public void endRound(){
		cardHere= null;
		playersHere.clear();
	}
	
	
	/**
	 * Method that returns the CardDescriber present in this PositionView 
	 * @return the CardDescriber present in this PositionView 
	 */
	public CardDescriber getCardHere() {
		return cardHere;
	}
	
	/**
	 * Method that returns the PositionDescriber of this PositionView 
	 * @return the PositionDescriber of this PositionView 
	 */
	public PositionDescriber getPositionDescriber(){
		return describer;
	}
	
	/**
	 * Method that returns the Map of players and colours of their family members that are occupying this PositionView
	 * @return the Map of players and colours of their family members that are occupying this PositionView
	 */
	public Map<String, Colour> getPlayersHere() {
		return playersHere;
	}
	
	/**
	 * Method that returns the BoardZone of the PositionDescriber of this PositionView
	 * @return the BoardZone of the PositionDescriber of this PositionView
	 */
	public BoardZone getZone() {
		return describer.getZone();
	}
	
	/**
	 * Method that returns the index of the position described by the PositionDescriber of this PositionView
	 * @return the index of the position described by the PositionDescriber of this PositionView
	 */
	public int getPositionIndex(){
		return describer.getPosition();
	}
	
	/**
	 * Method that returns the bonus of the position described by the PositionDescriber of this PositionView, as a string
	 * @return the bonus of the position described by the PositionDescriber of this PositionView, as a string
	 */
	public String getBonusPosition(){
		return describer.getResourcesOrPointsOnPosition();
	}
	
	/**
	 * Method that returns the value of the position described by the PositionDescriber of this PositionView, as a string
	 * @return the value of the position described by the PositionDescriber of this PositionView, as a string
	 */
	public int getPositionValue(){
		return describer.getValue();
	}
}
