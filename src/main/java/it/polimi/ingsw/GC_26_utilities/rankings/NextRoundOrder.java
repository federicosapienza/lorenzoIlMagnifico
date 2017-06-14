package it.polimi.ingsw.GC_26_utilities.rankings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.GC_26_player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Class that keeps a record of the rankings. It's periodically updated during the game.
 * Furthermore handles the changing order of the rounds.
 * 
 */

public class NextRoundOrder {
	
	private List<String> nextRoundOrder= new ArrayList<>();
	
	/**
	 * Constructor of the order for the next round.
	 * @param players It's the list of players playing the game
	 */
	public NextRoundOrder(List<Player> players) {
		initialise(players);
	}
	
	/**
	 * Method that is used for the first initialization. 
	 * In the first round, the order is determined by the order in which the player were added to the game:
	 * a "fair" principle common in multiplayer online games.
	 * @param players
	 */
	private void initialise(List<Player> players) {
		for(Player p: players){
			String name = p.getName();
		}
	}

	// logic of new round' s order 
	/**
	 * Method that updates the order for the next round if the player was not already contained in the list.
	 * @param player It's the player to add in the list if not already contained in the list of players.
	 */
	public void nextRoundChanging(Player player){
		String name = player.getName();
		if(!nextRoundOrder.contains(name))
			nextRoundOrder.add(name);
	}
	
	/**
	 * Getter method that returns the order for the next round.
	 * @return nextRoundOrder It's the order for the next round.
	 */
	public List<String> getNextRoundOrder() {
		return nextRoundOrder;
	}
	
	
	//it's called when the round starts: it returns next round's order by passing players' names(string)
	/**
	 * Method that changes the order for the next round, if it has to be changed.
	 * @param players It's the list of players playing the game
	 */
	public void changeNextRoundOrder(List<Player> players){  
		//completing the nextRoundOrderList: adding all the players that are not contained in nextRoundOrder
		for (Player p: players) {
			if (!nextRoundOrder.contains(p.getName()))
				nextRoundOrder.add(p.getName());
		}
		//sorting Player list
		List<Player> temp = new ArrayList<>();
		for(String name: nextRoundOrder){
			for(Player p : players){
				if(name== p.getName()){
					temp.add(p);
					break;
				}
				else throw new IllegalArgumentException("could not sort the List");
			}
		}
		players = temp;
		// clearing nextRoundOrder List
		while(!nextRoundOrder.isEmpty()){
			nextRoundOrder.remove(0);
		}
	}
	
}