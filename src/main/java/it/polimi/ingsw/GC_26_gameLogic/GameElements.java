package it.polimi.ingsw.GC_26_gameLogic;

import java.util.List;
import java.util.Map;

import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_board.Board;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_server.Observable;
import it.polimi.ingsw.GC_26_utilities.Message;
import it.polimi.ingsw.GC_26_utilities.dices.Dices;
import it.polimi.ingsw.GC_26_utilities.rankings.NextRoundOrder;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class GameElements extends Observable<ActionNotification>{  
	private Game game;
	private Board board;
	private Dices dices;
	private NextRoundOrder nextRoundOrder;
	private List<Player> players;
	private int numberOfPlayers;
	private MainActionHandler handlers;
	private Map<Integer, Integer> faithPointsTrack;
	
	public GameElements(Game game, List<Player> players, int numberOfPlayers, List<ResourcesOrPoints[]> resourcesOrPointsList, Map<Integer, Integer> faithPointsTrack ) {
		board=new Board(numberOfPlayers, resourcesOrPointsList);
		dices= new Dices();
		nextRoundOrder = new NextRoundOrder(players);
		this.game =game;
		this.faithPointsTrack= faithPointsTrack;
		this.players=players;
		this.handlers= new MainActionHandler(this);
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
    
    public Map<Integer, Integer> getFaithPointsTrack() {
		return faithPointsTrack;
	}
    
}
