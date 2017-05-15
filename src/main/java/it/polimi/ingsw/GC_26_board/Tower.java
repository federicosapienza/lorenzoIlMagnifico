package it.polimi.ingsw.GC_26_board;

import java.util.*;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
//To import Set interface
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class Tower {
	private boolean free;
	private List<Player> playersInTheTower = new ArrayList<>();
	private List<DevelopmentCard> cardsForThisPeriod = new ArrayList<>();
	
	public Tower(ResourcesOrPoints [] resourcesOrPoints){
		createTowerPosition(resourcesOrPoints);
	}
	
	public void createTowerPositions(ResourcesOrPoints [] resourcesOrPoints){
		TowerPosition towerPosition1 = new TowerPosition(1,resourcesOrPoints[0],1);
		TowerPosition towerPosition2 = new TowerPosition(2,resourcesOrPoints[1],3);
		TowerPosition towerPosition3 = new TowerPosition(3,resourcesOrPoints[2],5);
		TowerPosition towerPosition4 = new TowerPosition(4,resourcesOrPoints[3],7);
	}

	public TowerPosition getPosition(int floor){
		
	}

	public void setCardsForThisPeriod(Set<DevelopmentCard> cards){
		cardForthisPeriod=cards;
		//carico da file e inserisco 8 carte in cardsForThisPeriod
		//DevelopmentCard developmentCard1= oggetto letto da file
		
	}
	
	private void giveRandomCardsToPosition(DevelopmentCard developmentCard1 ,DevelopmentCard developmentCard2, DevelopmentCard developmentCard3,DevelopmentCard developmentCard4){
		towerPosition1.setCard(developmentCard1);
		towerPosition2.setCard(developmentCard2);
		towerPosition3.setCard(developmentCard3);
		towerPosition4.setCard(developmentCard4);
	}
	
	public void newRound(){
		//chiama le ultime 4 carte 
		//chiamo setcardsinpositions;
		giveRandomCardsToPosition(developmentCard1,developmentCard2,developmentCard3,developmentCard4);
	}
	
	private void setCardsInPositions(){
		//prendi una carta a caso 
		//towerposition1.setcard(card);
		//delete card dalla lista cardforthisperiod
	}
	
	public void clearCardsAndPlayers(){ // clear cards and players
		//towerposition1.clear
		//free=true;
		//chiama i vari metodi clear relativi alle varie posizioni
	}
	
	public boolean isThePlayerInTheTower(Player player){
		for(Player p: playersInTheTower){
			if(p.equals(player)){
				return true;
			}
			else{return false;}
	}
		
		
		
	public void setPlayerInTheTower(Player player){
		//playerintower .add(player p);
		//set free = false
	}
	
	public boolean isTheTowerOccupied(){
		if(free==false){
			return true;
		}
		else{return false;
		}
	}
	
	public void setCardsForThisPeriod(List<DevelopmentCards> cards=new ArrayList<DevelopmentCard>()){
		
	}
	

	
	
}
