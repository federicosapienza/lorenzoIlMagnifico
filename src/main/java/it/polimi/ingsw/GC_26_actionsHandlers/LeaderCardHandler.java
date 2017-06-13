package it.polimi.ingsw.GC_26_actionsHandlers;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.Info;
import it.polimi.ingsw.GC_26_utilities.Request;


//Handles the activation of leaderCards : he keeps memory of the cards used this turn to prevent the player from trying using the same card twice
public class LeaderCardHandler {
	private GameElements gameElements;
	private List<LeaderCard> used;

	public LeaderCardHandler(GameElements gameElements) {
		this.gameElements= gameElements;
		used =new ArrayList<>();
	}
	
	public boolean isPossible(Player player , int choice){  
		List<LeaderCard> list = player.getPersonalBoard().getLeadersCard();
		LeaderCard card=null;
		try {
			card= list.get(choice);
		} catch (IndexOutOfBoundsException e) {
			player.notifyObservers(new Request(player.getStatus(),"Wrong value", null));
			return false;
		}
		if(used.contains(card)){
			player.notifyObservers(new Request(player.getStatus(),"You have already used the card", new CardDescriber(card)));
			return false;
		}
			
		return card.checkRequirement(player);
			
		
		
	}
	
	public void perform(Player player , int choice){  
		List<LeaderCard> list = player.getPersonalBoard().getLeadersCard();
		LeaderCard card=null;
		try {
			card= list.get(choice);
		} catch (IndexOutOfBoundsException e) {
			player.notifyObservers(new Request(player.getStatus(),"Wrong value", null));
			throw new IllegalStateException();
		}
		if (!card.checkRequirement(player))
			throw new IllegalStateException();
		
		card.runEffect(player);
		setCardUsed(card);
		//notifies the players
		gameElements.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName() + "uses LeaderCard "+ card));
			
	}
	
	
	private void setCardUsed(LeaderCard card){
		used.add(card);
	}
	
	public void endTurn(){
		used.clear();
	}
	
}
