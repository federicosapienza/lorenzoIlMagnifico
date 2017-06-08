package it.polimi.ingsw.GC_26_personalBoard;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26_serverView.Observable;

import java.util.Set;
import java.util.ArrayList;
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
	//it's the set of territory cards
	private Set<DevelopmentCard> territoryCardSet= new HashSet<>();
	
	//it's the set of building cards
	private Set<DevelopmentCard> buildingCardSet= new HashSet<>();
	
	//it's the set of character cards
	private Set<DevelopmentCard> characterCardSet= new HashSet<>();
	
	//it's the set of venture cards
	private Set<DevelopmentCard> ventureCardSet= new HashSet<>();
	
	//it's the set of leader cards
	private List<LeaderCard> leaderCardSet= new ArrayList<>();
	
	//it's a counter that counts the number of territory cards
	private int territoryCardCounter;
	
	//it's the personal board tile to append at the left of the personal board
	private PersonalBoardTile personalBoardTile;
	
	public PersonalBoard(){
		
	}
	
	/**
	 * Method that adds a development card to the personal board.
	 * @param card It's the card to add
	 */
	public void add(DevelopmentCard card){
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
		leaderCardSet.add(leaderCard);
	}
	
	/**
	 * Method that checks the number of territory cards and returns the result of this count.
	 * @return territoryCardCounter It's the number of territory cards in the personal board.
	 */
	public int checkNumberOfTerritoryCards(){
		return territoryCardCounter;
	}
	
	/**
	 * Method that returns the set of development cards for the desired type of cards.
	 * @param type It's the desired type of cards 
	 * @return territoryCardSet The set of territory cards if the @param type is TERRITORYCARD
	 * @return buildingCardSet The set of building cards if the @param type is BUILDINGCARD
	 * @return characterCardSet The set of character cards if the @param type is CHARACTERCARD
	 * @return ventureCardSet The set of venture cards if the @param type is VENTURECARD
	 */
	public Set<DevelopmentCard> getCurrentCards(DevelopmentCardTypes type){
		switch(type){
		case TERRITORYCARD:
			return territoryCardSet;
		case BUILDINGCARD:
			return buildingCardSet;
		case CHARACTERCARD:
			return characterCardSet;
		case VENTURECARD:
			return ventureCardSet;
		default: 
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Method that returns the list of leader cards.
	 * @return leaderCardSet It's list of leader cards
	 */
	public List<LeaderCard> getLeadersCard(){
		return leaderCardSet;
	}
	
	/**
	 * Method that returns the personal board tile.
	 * @return personalBoardTile It's the personal board tile.
	 */
	public PersonalBoardTile getPersonalBoardTile() {
		return personalBoardTile;
	}
	
	
	
	
	
	
	
}
