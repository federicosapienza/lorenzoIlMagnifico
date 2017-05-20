package it.polimi.ingsw.GC_26_gameLogic;

import java.util.Set;

import it.polimi.ingsw.GC_26_board.Board;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.DicesSet;
import it.polimi.ingsw.GC_26_utilities.rankings.Rankings;

public class GameElements {
	private Board board;
	private DicesSet dices;
	private Rankings rankings;
	private List<Player> players;
	
	
	public GameElements(Set<Player> players) {
		
	}
	public Board getBoard() {
		return board;
	}
	public DicesSet getDices() {
		return dices;
	}
	public Set<Player> getPlayers() {
		return players;
	}
	public Rankings getRankings() {
		return rankings;
	}
	

}
