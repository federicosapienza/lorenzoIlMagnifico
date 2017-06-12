package it.polimi.ingsw.GC_26_gameLogic;

import java.util.List;

import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_board.Board;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_utilities.Message;
import it.polimi.ingsw.GC_26_utilities.dices.Dices;
import it.polimi.ingsw.GC_26_utilities.rankings.NextRoundOrder;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class GameElements {  //used at the beginning of the game to pass resources bonus in positions
	Game game;
	private Board board;
	private Dices dices;
	private NextRoundOrder nextRoundOrder;
	private List<Player> players;
	private int numberOfPlayers;
	private TimerValuesInterface timerInfo;
	private MainActionHandler handlers = new MainActionHandler();
	private ActionsPerformed gameMemory;
	
	public GameElements(Game game, List<Player> players, int numberOfPlayers, List<ResourcesOrPoints[]> resourcesOrPointsList, TimerValuesInterface times) {
		board=new Board(numberOfPlayers, resourcesOrPointsList);
		dices= new Dices();
		nextRoundOrder = new NextRoundOrder(players);
		timerInfo= times;
		gameMemory =new ActionsPerformed();
		this.game =game;
		this.players=players;
	}
	
	public Game getGame() {
		return game;
	}
	
	public ActionsPerformed getGameMemory() {
		return gameMemory;
	}
	
	public Board getBoard() {
		return board;
	}
	public Dices getDices() {
		return dices;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public NextRoundOrder getNextROundOrder() {
		return nextRoundOrder;
	}
	
	public  int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	
	public MainActionHandler getHandlers() {
		return handlers;
	}
	

	//calling players notify observer method to share broadcast messages
    public void notifyPlayers(Message message){  
       for(Player p: players){
    	   p.notifyObservers(message);
       }
       
    }
    
}
