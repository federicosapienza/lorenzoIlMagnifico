package it.polimi.ingsw.GC_26_gameLogic;


import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.Cards;
import it.polimi.ingsw.GC_26_cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_serverView.Observable;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;




public class Game extends Observable<CardDescriber>{
	private final Cards cards;
	private  List<Player> players;
	private int numberOfPlayers;
	private GameElements gameElements;
	private List<ResourcesOrPoints[]> resourcesOrPointsBonus;
	private final int  numberOfPeriods =GameParameters.getNumberOfPeriods(); 
	
	private List<ResourcesOrPoints> startingResources;
	private int times[];
	
	Period period;
	private int periodNumber;
	private List<ExcommunicationTile> excommunicationTiles;

	
	public Game(Cards cards, List<ResourcesOrPoints[]> resourcesOrPointsList, List<ResourcesOrPoints> startingResources, int times[]){
		this.cards= cards;
		this.resourcesOrPointsBonus= resourcesOrPointsList;
		this.times= times;
		periodNumber=1;
		players= new ArrayList<>();
	}
	
	public GameElements getGameElements() {
		return gameElements;
	}
	
	public int getPeriodNumber() {
		return periodNumber;
	}
	public Period getPeriod() {
		return period;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player addPlayer(String name){
		Player player = new Player(name, startingResources.get(numberOfPlayers-1));
		players.add(player);
		numberOfPlayers++;
		return player;
	}
	
	public void startGame(){
		excommunicationTiles = cards.getExcommunicationTiles();
		
		//todo mandargliele
		while(periodNumber<= numberOfPeriods){
			period= new Period(periodNumber, cards, gameElements, excommunicationTiles.get(periodNumber-1));
			period.start();
		}
		
		//TODO decide Winner!!!
	}
	
	public void initialiseGame(){
		gameElements= new GameElements(this ,players, numberOfPlayers, resourcesOrPointsBonus, times);
		
		
		//TODO notificare i giocatori
		
		
		//TODO prendere 4 carte leader per giocatore e notificarliele 
	}
}
