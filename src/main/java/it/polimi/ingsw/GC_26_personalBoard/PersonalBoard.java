package it.polimi.ingsw.GC_26_personalBoard;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26_server.Observable;

import java.util.Set;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This class represents the personal board that every player owns.
 */


public class PersonalBoard extends Observable<CardDescriber>{  //sometimes we need to pass cards only to a player: (i.e LeaderCards)
	//it's the list of territory cards
	private List<DevelopmentCard> territoryCardSet= new ArrayList<>();
	
	//it's the list of building cards
	private List<DevelopmentCard> buildingCardSet= new ArrayList<>();
	
	//it's the list of character cards
	private List<DevelopmentCard> characterCardSet= new ArrayList<>();
	
	//it's the list of venture cards
	private List<DevelopmentCard> ventureCardSet= new ArrayList<>();
	
	//it's the list of leader cards owned
	private List<LeaderCard> leadersCardList= new ArrayList<>();
	
	//it's the set of leader cards the player has used at least once yet.
	private Set<LeaderCard> leaderCardsUsed = new HashSet<>();
	
	
	
	//it's the personal board tile to append at the left of the personal board
	private PersonalBoardTile personalBoardTile;
	
	public PersonalBoard(){
		
	}
	
	/**
	 * Method that adds a development card to the personal board.
	 * @param card It's the card to add
	 */
	public void add(DevelopmentCard card){
		if (card == null) {
			throw new NullPointerException("card is null");
		}
		
			switch(card.getType()){
			case TERRITORYCARD: 
				territoryCardSet.add(card);
			case BUILDINGCARD: 
				buildingCardSet.add(card);
			case CHARACTERCARD:
				characterCardSet.add(card);
			case VENTURECARD:
				ventureCardSet.add(card);
			default:
				throw new IllegalArgumentException();
			}
		}
	
	/**
	 * Method that counts the number of cards for the desired type of cards and returns the result of the count
	 * @param type It's the type of the desired set of development cards 
	 * @return territoryCardSet.size() The number of territory cards if the @param type is TERRITORYCARD
	 * @return buildingCardSet.size() The number of building cards if the @param type is BUILDINGCARD
	 * @return characterCardSet.size() The number of character cards if the @param type is CHARACTERCARD
	 * @return ventureCardSet.size() The number of venture cards if the @param type is VENTURECARD
	 */
	public int getNumberOfCardPerType(DevelopmentCardTypes type){
		switch(type){
		case TERRITORYCARD: 
			return territoryCardSet.size();
		case BUILDINGCARD: 
			return buildingCardSet.size();
		case CHARACTERCARD:
			return characterCardSet.size();
		case VENTURECARD:
			return ventureCardSet.size();
		default:
			throw new IllegalArgumentException();
		}
}

	/**
	 * Method that adds a leader card to the set of leader cards
	 * @param leaderCard It's the leader card to add
	 */
	public void addLeaderCard(LeaderCard leaderCard){
		if (leaderCard == null) {
			throw new NullPointerException("leaderCard is null");
		}
		leadersCardList.add(leaderCard);
	}
	
	/**
	 * Method that checks the number of territory cards and returns the result of this count.
	 * @return territoryCardCounter It's the number of territory cards in the personal board.
	 */
	public int checkNumberOfTerritoryCards(){
		return territoryCardSet.size();
	}
	
	/**
	 * Method that returns the set of development cards for the desired type of cards.
	 * @param type It's the desired type of cards 
	 * @return territoryCardSet The list of territory cards if the @param type is TERRITORYCARD
	 * @return buildingCardSet The list of building cards if the @param type is BUILDINGCARD
	 * @return characterCardSet The list of character cards if the @param type is CHARACTERCARD
	 * @return ventureCardSet The list of venture cards if the @param type is VENTURECARD
	 */
	public List<DevelopmentCard> getCurrentCards(DevelopmentCardTypes type){
		switch(type){
		case TERRITORYCARD:
			return  Collections.unmodifiableList(territoryCardSet);
		case BUILDINGCARD:
			return Collections.unmodifiableList(buildingCardSet);
		case CHARACTERCARD:
			return Collections.unmodifiableList(characterCardSet);
		case VENTURECARD:
			return Collections.unmodifiableList(ventureCardSet);
		default: 
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Method that returns the list of leader cards.
	 * @return leaderCardSet It's the list of leader cards
	 */
	public List<LeaderCard> getLeadersCard(){
		List<LeaderCard>  temp = Collections.unmodifiableList(leadersCardList);
		return temp;
	}
	
	/**
	 * Method that returns the personal board tile.
	 * @return personalBoardTile It's the personal board tile.
	 */
	public PersonalBoardTile getPersonalBoardTile() {
		return personalBoardTile;
	}
	
	/**
	 * Method that sets the personal board tile.
	 * 
	 */
	public void setPersonalBoardTile(PersonalBoardTile personalBoardTile) {
		if (personalBoardTile == null) {
			throw new NullPointerException("personalBoardTile is null");
		}
		this.personalBoardTile = personalBoardTile;
	}
	
	
	/**
	 * Method that sees if a player has already used a leaderCard in this game.
	 * @return true if the card has been used yet.
	 */
	public boolean isLeaderCardUsedYet(LeaderCard leaderCard){
		return leaderCardsUsed.contains(leaderCard);
	}
	
	/**
	 * Method that sets the Leader Card as used during this game by this player, if this was note before.
	 * 
	 */

	public void setLeaderCardUsed(LeaderCard leaderCard){
		if (leaderCard == null) {
			throw new NullPointerException("leaderCard is null");
		}
		if(!isLeaderCardUsedYet(leaderCard))
			leaderCardsUsed.add(leaderCard);
	}
	
	
}
