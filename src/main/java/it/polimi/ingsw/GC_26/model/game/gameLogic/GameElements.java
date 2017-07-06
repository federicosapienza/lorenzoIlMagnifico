package it.polimi.ingsw.GC_26.model.game.gameLogic;

import java.util.List;
import java.util.Map;

import it.polimi.ingsw.GC_26.messages.Message;
import it.polimi.ingsw.GC_26.messages.action.ActionNotification;
import it.polimi.ingsw.GC_26.model.game.gameComponents.board.Board;
import it.polimi.ingsw.GC_26.model.game.gameComponents.dices.Dices;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.handlers.actionHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.utilities.observer.Observable;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * Class containing the game elements necessary to play the game correctly.
 */
public class GameElements extends Observable<ActionNotification>{  
	private Game game;
	private Board board;
	private Dices dices;
	private NextRoundOrder nextRoundOrder;
	private List<Player> players;
	private int numberOfPlayers;
	private MainActionHandler handlers;
	private Map<Integer, Integer> faithPointsTrack;
	
	/**
	 * Constructor: it creates the correct game parameters according to the objects contained in the parameters
	 * @param game It's the game that will contain these game parameters
	 * @param players It's the list of players that are playing the game
	 * @param numberOfPlayers It's the number of players contained in the list of players
	 * @param resourcesOrPointsList It's the list of resources and points that have to be initialized in the game
	 * @param faithPointsTrack It's the track of Faith Points needed for the Vatican Report phase of the game
	 */
	public GameElements(Game game, List<Player> players, int numberOfPlayers, List<ResourcesOrPoints[]> resourcesOrPointsList, Map<Integer, Integer> faithPointsTrack ) {
		/**
		 * Creating the correct board according to the number of players
		 */
		if (game == null) {
			throw new NullPointerException();
		}
		
		if (numberOfPlayers < 0 || numberOfPlayers > 4) {
			throw new IllegalArgumentException();
		}
		board = new Board(numberOfPlayers, resourcesOrPointsList);
		dices = new Dices();
		nextRoundOrder = new NextRoundOrder();
		this.game =game;
		this.faithPointsTrack= faithPointsTrack;
		this.players=players;
		this.handlers= new MainActionHandler(this);
	}
	
	/**
	 * Getter method that returns the current game
	 * @return the current game
	 */
	public Game getGame() {
		return game;
	}
	
	/**
	 * Getter method that returns the board of the current game
	 * @return the board of the current game
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Getter method that returns the dices of the current game
	 * @return the dices of the current game
	 */
	public Dices getDices() {
		return dices;
	}
	
	/**
	 * Getter method that returns the list of players that are playing the game
	 * @return the list of players that are playing the game
	 */
	public List<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Getter method that returns the order for the next round, which is determined by the Council Palace, if it contains at least
	 * one family member, or is equal to the prevoius one if the Council Palace doesn't contain any family member
	 * @return the order for the next round
	 */
	public NextRoundOrder getNextRoundOrder() {
		return nextRoundOrder;
	}
	
	/**
	 * Getter method that returns the number of players that are playing the game
	 * @return the number of players that are playing the game
	 */
	public  int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	/**
	 * Getter method that returns the track of Faith Points that determine the Vatican Report Phase
	 * @return the track of Faith Points
	 */
	public Map<Integer, Integer> getFaithPointsTrack() {
		return faithPointsTrack;
	}
	
	/**
	 * Getter method that returns the handlers for main actions
	 * @return the handlers for the main actions
	 */
	public MainActionHandler getHandlers() {
		return handlers;
	}
	
	

	/**
	 * Method used to share broadcast messages and notify observers when calling players
	 * @param message It's the broadcast message that are shared with this method
	 */
    public void notifyPlayers(Message message){  
       for(Player p: players){
    	   p.notifyObservers(message);
       }
       
       
    }
    
    
    
}
