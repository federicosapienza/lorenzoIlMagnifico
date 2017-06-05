package it.polimi.ingsw.GC_26_gameLogic;

import java.util.List;

import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_board.Board;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_serverView.Observable;
import it.polimi.ingsw.GC_26_utilities.dices.Dices;
import it.polimi.ingsw.GC_26_utilities.rankings.Rankings;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesDescriber;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class GameElements extends Observable<ResourcesDescriber>{  //used at the beginning of the game to pass resources bonus in positions
	Game game;
	private Board board;
	private Dices dices;
	private Rankings rankings;
	private List<Player> players;
	private int numberOfPlayers;
	private TimerInfo timerInfo;
	private ActionsPerformed gameMemory= new ActionsPerformed();
	private MainActionHandler handlers = new MainActionHandler();
	
	public GameElements(Game game, List<Player> players, int numberOfPlayers, List<ResourcesOrPoints[]> resourcesOrPointsList, int times[]) {
		board=new Board(numberOfPlayers, resourcesOrPointsList);
		dices= new Dices();
		rankings = new Rankings(players);
		timerInfo= new TimerInfo(times);
	}
	
	public Game getGame() {
		return game;
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
	public Rankings getRankings() {
		return rankings;
	}
	
	public  int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public ActionsPerformed getGameMemory() {
		return gameMemory;
	}
	
	public MainActionHandler getHandlers() {
		return handlers;
	}
	

	//instead of adding an other observer to send String , it calls player's observer
    public void notifyPlayers(String string){  
       for(Player p: players){
    	   p.notifyObservers(string);
       }
       
    }
	
}
