package it.polimi.ingsw.GC_26_actionsHandlers;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26_player.Player;


//Handles the activation of leaderCards : he keeps memory of the cards used this turn to prevent the player from trying using the same card twice
public class LeaderCardHandler {
	private List<LeaderCard> used;

	public LeaderCardHandler() {
		used =new ArrayList<>();
	}
	
	public boolean isPossible(Player player , int choice){  
		List<LeaderCard> list = player.getPersonalBoard().getLeadersCard();
		LeaderCard card=null;
		try {
			card= list.get(choice);
		} catch (IndexOutOfBoundsException e) {
			player.notifyObservers("wrong value");
			return false;
		}
		if(used.contains(card)){
			player.notifyObservers("You have already used the card");
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
			player.notifyObservers("wrong value");
			throw new IllegalStateException();
		}
		if (!card.checkRequirement(player))
			throw new IllegalStateException();
		
		card.runEffect(player);
		setCardUsed(card);
			
	}
	
	
	private void setCardUsed(LeaderCard card){
		used.add(card);
	}
	
	public void endTurn(){
		used.clear();
	}
	
}