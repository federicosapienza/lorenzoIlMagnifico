package it.polimi.ingsw.GC_26_gameLogic;


import java.io.Serializable;
import java.util.List;
import java.util.function.IntToLongFunction;

import it.polimi.ingsw.GC_26_board.Board;
import it.polimi.ingsw.GC_26_cards.Cards;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Dices;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;




public class Game implements Serializable, Cloneable{
	private final Cards cards;
	private Player[] players;
	private int numberOfPlayers;
	private GameElements gameElements;
	private List<ResourcesOrPoints[]> resourcesOrPointsBonus;
	private final int  numberOfPeriods =GameParameters.getNumberOfPeriods(); 
	private int currentPeriod=1;
	private List<ResourcesOrPoints> startingResources;
	private Board board;
	public static final int MAXPLAYERS = 4;
	public static final int MINPLAYERS = 2;
	private boolean active; //true if this game is active
	private int currentPlayerID;
	private int firstPlayerID;
	private Dices currentDices;
	
	public Game(Cards cards, List<ResourcesOrPoints[]> resourcesOrPointsList, List<ResourcesOrPoints> startingResources){
		this.cards= cards;
		this.resourcesOrPointsBonus= resourcesOrPointsList;
	}
	
	public GameElements getGameElements() {
		return gameElements;
	}
	
	public int getCurrentPeriod() {
		return currentPeriod;
	}
	
	public Board getBoard() {
		return board;
	}
	
	protected void setBoard(Board gBoard) {
		board = gBoard;
	} 
	
	public Player addPlayer(int id, String name){
		Player player = new Player(id, name);
		players[id] = player;
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
		gameElements = new GameElements(players, numberOfPlayers, resourcesOrPointsBonus);
		
	}
}
