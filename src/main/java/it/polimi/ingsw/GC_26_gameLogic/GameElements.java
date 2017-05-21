package it.polimi.ingsw.GC_26_gameLogic;

import java.util.List;
import java.util.Set;

import it.polimi.ingsw.GC_26_board.Board;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Dices;
import it.polimi.ingsw.GC_26_utilities.rankings.Rankings;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class GameElements {
	private Board board;
	private Dices dices;
	private Rankings rankings;
	private List<Player> players;
	
	
	public GameElements(List<Player> players, int numberOfPlayers, List<ResourcesOrPoints[]> resourcesOrPointsList) {
		board=new Board(numberOfPlayers, resourcesOrPointsList);
		dices= new Dices();
		rankings = new Rankings(players);
		
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
	

}