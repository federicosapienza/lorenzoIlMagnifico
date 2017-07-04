package it.polimi.ingsw.GC_26.model.actionHandlers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.GC_26.messages.Info;
import it.polimi.ingsw.GC_26.messages.PersonalBoardChangeNotification;
import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.model.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26.model.game.gameLogic.GameStatus;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.utilities.IllegalActionException;


//Handles the activation of leaderCards : he keeps memory of the cards used this turn to prevent the player from trying using the same card twice
public class LeaderCardHandler extends Handler{
	private String errorString = "Wrong value";
	private static final Logger LOG = Logger.getLogger(LeaderCardHandler.class.getName());


	public LeaderCardHandler(List<Player> players) {
		super(players);
	}
	
	public boolean isPossible(Player player , int choice){  
		List<LeaderCard> list = player.getPersonalBoard().getLeadersCard();
		LeaderCard card;
		if(choice>4){
			player.notifyObservers(new Request(player.getStatus(),errorString, null));
			return false;
		}	
		try {
			card= list.get(choice);
		} catch (IndexOutOfBoundsException e) {
			LOG.log( Level.FINE, "index out of bound in vector  ", e);
		player.notifyObservers(new Request(player.getStatus(),errorString, null));
			return false;
		}
		if(player.getPersonalBoard().isLeaderCardUsedYet(card)){
			player.notifyObservers(new Request(player.getStatus(),"You have already used the card", new CardDescriber(card)));
			return false;
		}
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
			LOG.log( Level.FINE, "index out of bound in vector  ", e);
			player.notifyObservers(new Request(player.getStatus(),errorString, null));
			throw new IllegalActionException();
		}
		if (player.getPersonalBoard().isLeaderCardUsedYet(card) || !card.checkRequirement(player))
			throw new IllegalActionException();
		
		player.getPersonalBoard().setLeaderCardUsed(card);
		card.runImmediateEffect(player);
		card.runPermanentEffect(player);
		super.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.PLAYING, player.getName(), new CardDescriber(card), null));			

		//notifies the players
		super.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName() + " uses Leader Card "+ card));
		}
	
	
	

	
}
