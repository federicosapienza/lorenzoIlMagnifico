package it.polimi.ingsw.GC_26_gameLogic;

import java.util.List;

import it.polimi.ingsw.GC_26_cards.Cards;
import it.polimi.ingsw.GC_26_player.Player;

public class Game {
	private Cards cards;
	private List<Player> players;
	public int numberOfPlayer()
	public Game(Cards cards){
		this.cards= cards;
	}
	
	public Player addPlayer(String name){
		Player player = new Player(name);
		players.add(player);
		numberOfPlayer++;
	}
	
}
