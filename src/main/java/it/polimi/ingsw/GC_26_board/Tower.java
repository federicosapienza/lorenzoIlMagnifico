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
	private TowerPosition towerPositionFloor1;
	private TowerPosition towerPositionFloor2;
	private TowerPosition towerPositionFloor3;
	private TowerPosition towerPositionFloor4;
	
	public Tower(ResourcesOrPoints[] resourcesOrPoints){
		createTowerPositions(resourcesOrPoints);
	}
	
	public void createTowerPositions(ResourcesOrPoints[] resourcesOrPoints){
		towerPositionFloor1 = new TowerPosition(1,resourcesOrPoints[0],1);
		towerPositionFloor2 = new TowerPosition(2,resourcesOrPoints[1],3);
		towerPositionFloor3 = new TowerPosition(3,resourcesOrPoints[2],5);
		towerPositionFloor4 = new TowerPosition(4,resourcesOrPoints[3],7);
	}

	public TowerPosition getPosition(int floor){
		switch(floor) {
		case 1:
			return towerPositionFloor1;
		case 2:
			return towerPositionFloor2;
		case 3:
			return towerPositionFloor3;
		case 4:
			return towerPositionFloor4;
		default:
			System.out.println("Hai inserito un numero non corretto");
			return null;
		}//exception?
	}
	
	public void setCardsForThisPeriod(List<DevelopmentCard> cards){
		cardsForThisPeriod=cards;
		DevelopmentCard randomCard1 =takeRandomCardFromList(cardsForThisPeriod);
		cardsForThisPeriod.remove(randomCard1);
		DevelopmentCard randomCard2 = takeRandomCardFromList(cardsForThisPeriod);
		cardsForThisPeriod.remove(randomCard2);
		DevelopmentCard randomCard3 = takeRandomCardFromList(cardsForThisPeriod);
		cardsForThisPeriod.remove(randomCard3);
		DevelopmentCard randomCard4 = takeRandomCardFromList(cardsForThisPeriod);
		cardsForThisPeriod.remove(randomCard4);
		//giveRandomCardsToPosition(cardsForThisPeriod)
	}
	
	public DevelopmentCard takeRandomCardFromList(List<DevelopmentCard> cards){
		Random randomizer = new Random();
		DevelopmentCard randomCard = cards.get(randomizer.nextInt(cards.size()));
		return randomCard;
	}
	
	private void giveRandomCardsToPosition(DevelopmentCard developmentCard1 ,DevelopmentCard developmentCard2, DevelopmentCard developmentCard3,DevelopmentCard developmentCard4){
		towerPositionFloor1.setCard(developmentCard1);
		towerPositionFloor2.setCard(developmentCard2);
		towerPositionFloor3.setCard(developmentCard3);
		towerPositionFloor4.setCard(developmentCard4);
	}
	
	private void setCardsInPositions(){
		//prendi una carta a caso 
		//towerposition1.setcard(card);
		//delete card dalla lista cardforthisperiod
	}
	
	public void newRound(){
		//chiama le ultime 4 carte 
		//chiamo setcardsinpositions;
		giveRandomCardsToPosition(developmentCard1,developmentCard2,developmentCard3,developmentCard4);
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
	
	
	

	
	
}
