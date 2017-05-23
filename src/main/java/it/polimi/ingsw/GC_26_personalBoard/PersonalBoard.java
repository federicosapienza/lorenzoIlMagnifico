package it.polimi.ingsw.GC_26_personalBoard;

import it.polimi.ingsw.GC_26_cards.*;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCard;
import java.util.Set;
import java.util.HashSet;



public class PersonalBoard {
	private Set<DevelopmentCard> territoryCardSet= new HashSet<>();
	private Set<DevelopmentCard> buildingCardSet= new HashSet<>();
	private Set<DevelopmentCard> characterCardSet= new HashSet<>();
	private Set<DevelopmentCard> ventureCardSet= new HashSet<>();
	private Set<LeaderCard> leaderCardSet= new HashSet<>();
	private int territoryCardCounter;
	private PersonalBoardTile personalBoardTile;
	
	public PersonalBoard(){
		
	}
	
	public void addTerritoryCard(DevelopmentCard territoryCard){
		territoryCardSet.add(territoryCard);
		territoryCardCounter++;
	}
	public void addBuildingCard(DevelopmentCard buildingCard){
		buildingCardSet.add(buildingCard);
	}
	
	public void addCharacterCard(DevelopmentCard characterCard){
		characterCardSet.add(characterCard);
	}
	
	public void addVenturesCard(DevelopmentCard ventureCard){
		ventureCardSet.add(ventureCard);
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
