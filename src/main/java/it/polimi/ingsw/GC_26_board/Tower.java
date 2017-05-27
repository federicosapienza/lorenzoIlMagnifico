package it.polimi.ingsw.GC_26_board;

import java.util.*;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
//To import Set interface
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.familyMembers.*;

public class Tower {
	private boolean isTowerFree;
	private List<Player> playersInTheTower = new ArrayList<>();
	private List<DevelopmentCard> cardsForThisPeriod = new ArrayList<>();
	private TowerPosition towerPositionFloor1;
	private TowerPosition towerPositionFloor2;
	private TowerPosition towerPositionFloor3;
	private TowerPosition towerPositionFloor4;
	
	public Tower(ResourcesOrPoints[] resourcesOrPoints){
		createTowerPositions(resourcesOrPoints);
	}
	
	private void createTowerPositions(ResourcesOrPoints[] resourcesOrPoints){
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
			throw new IllegalArgumentException();
		}
	}
	
	public void setCardsForThisPeriod(List<DevelopmentCard> cards){
		cardsForThisPeriod=cards;
	}
	
	
	public void setCardsInPositions(){
		DevelopmentCard randomCard1 = takeRandomCardFromList(cardsForThisPeriod);
		cardsForThisPeriod.remove(randomCard1);
		DevelopmentCard randomCard2 = takeRandomCardFromList(cardsForThisPeriod);
		cardsForThisPeriod.remove(randomCard2);
		DevelopmentCard randomCard3 = takeRandomCardFromList(cardsForThisPeriod);
		cardsForThisPeriod.remove(randomCard3);
		DevelopmentCard randomCard4 = takeRandomCardFromList(cardsForThisPeriod);
		cardsForThisPeriod.remove(randomCard4);
		giveRandomCardsToPosition(randomCard1,randomCard2,randomCard3,randomCard4);
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
			
	public void clearCardsAndFamilyMembers(){ 
		isTowerFree=true;
		towerPositionFloor1.clear();
		towerPositionFloor2.clear();
		towerPositionFloor3.clear();
		towerPositionFloor4.clear();
	    }
	
	public void newRound(){
		clearCardsAndFamilyMembers();
		setCardsInPositions();
	}
	
	    //checks if  the family member can be put there 
		//1) if the member is neutral , player is not added to the list 
		//2) if is a second action familyMember is null and player is not added
	public boolean canFamilyMemberGoToTheTower(FamilyMember familyMember){
		if(familyMember==null ||  familyMember.getColour()==Colour.NEUTRAL)
			return true;
		for(Player p: playersInTheTower){
			if(p.equals(familyMember.getPlayer())){
				return isTowerFree=true;
			}
		}
			return isTowerFree=false; 	
	}
	
	//sets that the player is in the Tower, passing the familyMember so that:
	//1) if the member is neutral , player is not added to the list 
	//2) if is a second action familyMember is null and player is not added
	public void setPlayerInTheTower(FamilyMember familyMember){
		if(familyMember== null)
			return;
		if( familyMember.getColour()!=Colour.NEUTRAL)
			playersInTheTower.add(familyMember.getPlayer());
		if(canFamilyMemberGoToTheTower(familyMember))
			throw new IllegalArgumentException();
		isTowerFree = false;
	}
	
	public boolean isTheTowerOccupied(){
		return isTowerFree;
	}

	
	
	

	
	
}
