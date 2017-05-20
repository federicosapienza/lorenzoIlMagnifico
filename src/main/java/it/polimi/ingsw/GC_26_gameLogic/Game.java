package it.polimi.ingsw.GC_26_gameLogic;


import java.util.List;

import javax.annotation.processing.RoundEnvironment;

import it.polimi.ingsw.GC_26_cards.Cards;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class Game {
	private final Cards cards;
	private  List<Player> players;
	private int numberOfPlayers;
	private GameElements gameElements;
	private List<ResourcesOrPoints[]> resourcesOrPointsBonus;
	private final int  numberOfPeriods =GameParameters.getNumberOfPeriods(); 
	private int currentPeriod=1;
	
	public Game(Cards cards, List<ResourcesOrPoints[]> resourcesOrPointsList){
		this.cards= cards;
		this.resourcesOrPointsBonus= resourcesOrPointsList;
	}
	
	public GameElements getGameElements() {
		return gameElements;
	}
	
	public int getCurrentPeriod() {
		return currentPeriod;
	}
	
	
	public Player addPlayer(String name){
		Player player = new Player(name);
		players.add(player);
		numberOfPlayers++;
		return player;
	}
	
	public void startGame(){
		initialiseGame();
		while(currentPeriod<= numberOfPeriods){
			Period period= new Period(currentPeriod, cards, gameElements);
			period.start();
		}
	}
	
	private void initialiseGame(){
		gameElements= new GameElements(players, numberOfPlayers, resourcesOrPointsBonus);
		
	}
}
