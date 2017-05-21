package it.polimi.ingsw.GC_26_personalBoard;

import it.polimi.ingsw.GC_26_cards.*;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
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
	
	public void callCard(){
		//TODO
		//non rimembro cosa dovrebbe fare questa funzione 
		//probabilmente non serve btw
	}
	
	public int checkNumberOfTerritoryCards(){
		return territoryCardCounter;
	}
	
	public Set<DevelopmentCard> getCurrentCards(int switchCounter){
		switch(switchCounter){
		case 1:
			return territoryCardSet;
		case 2:
			return buildingCardSet;
		case 3:
			return characterCardSet;
		case 4:
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
