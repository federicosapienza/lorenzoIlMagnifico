package it.polimi.ingsw.GC_26_personalBoard;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_serverView.Observable;

import java.util.Set;


import java.util.HashSet;



public class PersonalBoard extends Observable<CardDescriber>{  //sometimes we need to pass cardsonly to a player: (i.e LeaderCards)
	private Set<DevelopmentCard> territoryCardSet= new HashSet<>();
	private Set<DevelopmentCard> buildingCardSet= new HashSet<>();
	private Set<DevelopmentCard> characterCardSet= new HashSet<>();
	private Set<DevelopmentCard> ventureCardSet= new HashSet<>();
	private Set<LeaderCard> leaderCardSet= new HashSet<>();
	private int territoryCardCounter;
	private PersonalBoardTile personalBoardTile;
	
	public PersonalBoard(){
		
	}
	
	
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

	
	public void addLeaderCard(LeaderCard leaderCard){
		leaderCardSet.add(leaderCard);
	}
	
	
	
	public int checkNumberOfTerritoryCards(){
		return territoryCardCounter;
	}
	
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
	
	public Set<LeaderCard> getLeaderCard(){
		return leaderCardSet;
	}
	
	public PersonalBoardTile getPersonalBoardTile() {
		return personalBoardTile;
	}
	
	
	
	
	
	
	
}
