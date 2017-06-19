package it.polimi.ingsw.GC_26_actionsHandlers;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.Info;
import it.polimi.ingsw.GC_26_utilities.PersonalBoardChangeNotification;
import it.polimi.ingsw.GC_26_utilities.Request;


//Handles the activation of leaderCards : he keeps memory of the cards used this turn to prevent the player from trying using the same card twice
public class LeaderCardHandler extends Handler{
	private List<LeaderCard> usedThisRound;

	public LeaderCardHandler(List<Player> players) {
		super(players);
		usedThisRound =new ArrayList<>();
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
		if(usedThisRound.contains(card)){
			player.notifyObservers(new Request(player.getStatus(),"You have already used the card this turn", new CardDescriber(card)));
			return false;
		}
		if(player.getPersonalBoard().isLeaderCardUsedYet(card))//if the card has been activated yet, we must not check requirement is satisfied.
			return true;
			
		boolean flag= card.checkRequirement(player);
		if(!flag)
			player.notifyObservers(new Request(player.getStatus(),"You do not have the requirements for using the card", new CardDescriber(card)));
		return flag;
		
		
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
		if (!player.getPersonalBoard().isLeaderCardUsedYet(card) && !card.checkRequirement(player))
			throw new IllegalStateException();
		
		setCardUsed(card);
		card.runImmediateEffect(player);
		if(!player.getPersonalBoard().isLeaderCardUsedYet(card)){
			card.runPermanentEffect(player);
			super.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.PLAYING, player.getName(), new CardDescriber(card), null));			

		}
		//notifies the players
		super.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName() + " uses Leader Card "+ card));
		player.getPersonalBoard().setLeaderCardUsed(card);
		setCardUsed(null);	
	}
	
	
	private void setCardUsed(LeaderCard card){
		usedThisRound.add(card);
	}
	
	public void endTurn(){
		usedThisRound.clear();
	}
	
}
