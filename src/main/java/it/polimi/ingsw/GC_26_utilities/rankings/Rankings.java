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

public class Rankings {
	private Map<String, Integer> victoryPoints= new HashMap<>();
	private Map<String, Integer> militaryPoints= new HashMap<>();
	private Map<String, Integer> faithPoints= new HashMap<>();
	private List<String> nextRoundOrder= new ArrayList<>();
	
	public Rankings(Player[] players) {
		initialise(players);
		
	}

	private void initialise(Player[] players) {
		for(Player p: players){
			String name = p.getName();
			victoryPoints.put(name, 0);
			victoryPoints.put(name, 0);
			victoryPoints.put(name, 0); 
			//In the first round, the order is determined by the order in which the player were added to the game
			// a "fair" principle common in multiplayers online game.
		}
	}
	
	// Rankings getters
	public Map<String, Integer> getFaithPointsRank() {
		return faithPoints;
	}
	public Map<String, Integer> getMilitaryPointsRank() {
		return militaryPoints;
	}
	public Map<String, Integer> getVictoryPointsRank() {
		return victoryPoints;
	}
	
	
	
	
	//setter of rankings
	public void updateRankingPlayer(Player player){
		String name = player.getName();
		victoryPoints.replace(name, player.getWarehouse().getVictoryPoints());
		faithPoints.replace(name, player.getWarehouse().getFaithPoints());
		militaryPoints.replace(name, player.getWarehouse().getMilitaryPoints());
	}
	
	
	// logic of new round' s order 
	public void nextRoundChanging(Player player){ // updating the next round's order if the player was not already in the list 
		String name = player.getName();
		if(!nextRoundOrder.contains(name))
		nextRoundOrder.add(name);
	}
	
	//it ' s called when the round starts: it returns next round's order by passing players' names(string)   
	public void getNextRoundOrder(List<Player> players){  
		//completing the nextRoundOrderList: adding all the players who are not in nextRoundOrder
		for ( Player p: players) {
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