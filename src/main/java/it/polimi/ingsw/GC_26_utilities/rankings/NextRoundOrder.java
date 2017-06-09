package it.polimi.ingsw.GC_26_utilities.rankings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.GC_26_player.Player;

/*Keeps the rankings. Periodically updated during the game.
 * Furthermore handles the rounds' order changing
 * 
 */

public class NextRoundOrder {
	
	private List<String> nextRoundOrder= new ArrayList<>();
	public NextRoundOrder(List<Player> players) {
		initialise(players);
		
	}

	private void initialise(List<Player> players) {
		for(Player p: players){
			String name = p.getName();
			//In the first round, the order is determined by the order in which the player were added to the game
			// a "fair" principle common in multiplayers online game.
		}
	}

	
	
	// logic of new round' s order 
	public void nextRoundChanging(Player player){ // updating the next round's order if the player was not already in the list 
		String name = player.getName();
		if(!nextRoundOrder.contains(name))
		nextRoundOrder.add(name);
	}
	
	public List<String> getNextRoundOrder() {
		return nextRoundOrder;
	}
	
	
	//it's called when the round starts: it returns next round's order by passing players' names(string)   
	public void changeNextRoundOrder(List<Player> players){  
		//completing the nextRoundOrderList: adding all the players who are not in nextRoundOrder
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
				else throw new IllegalArgumentException(" could not reorder List ");
			}
		}
		players= temp;
		// emptying nextRoundOrder List
		while(!nextRoundOrder.isEmpty()){
			nextRoundOrder.remove(0);
		}
	}
	
}