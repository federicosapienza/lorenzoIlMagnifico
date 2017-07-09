package it.polimi.ingsw.GC_26.model.handlers.choice_handlers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.GC_26.messages.Info;
import it.polimi.ingsw.GC_26.messages.PersonalBoardChangeNotification;
import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26.model.game.game_logic.GameStatus;
import it.polimi.ingsw.GC_26.model.handlers.Handler;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.utilities.exceptions.IllegalActionException;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the handler for the Leader Cards.
 * Particularly, it handles the activation of Leader Cards: it keeps memory of the cards used during this turn to prevent the player 
 * from trying to use the same card twice 
 *
 */

public class LeaderCardHandler extends Handler{
	private String errorString = "Wrong value";
	private static final Logger LOG = Logger.getLogger(LeaderCardHandler.class.getName());

	/**
	 * Constructor: it creates a handler for the Leader cards, for the list of players contained in the parameter
	 * @param players It's the list of players who are playing the game
	 */
	public LeaderCardHandler(List<Player> players) {
		super(players);
	}
	
	/**
	 * Method that checks if the choice selected by the player contained in the parameter is possible according to the rules of the game
	 * @param player It's the player who is selecting the choice 
	 * @param choice It's the choice selected by the player
	 * @return true if the choice selected by the player is possible; else false
	 */
	public boolean isPossible(Player player, int choice){  
		List<LeaderCard> list = player.getPersonalBoard().getLeadersCard();
		LeaderCard card;
		if(choice>4){
			player.notifyObservers(new Request(player.getStatus(),errorString, null));
			return false;
		}	
		try {
			int temp= choice -1;
			card= list.get(temp);
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
	
	/**
	 * Method that performs the choice selected by the player
	 * @param player It's the player who has selected the choice 
	 * @param choice It's the choice selected by the player
	 */
	public void perform(Player player, int choice){  
		List<LeaderCard> list = player.getPersonalBoard().getLeadersCard();
		LeaderCard card=null;
		try {
			int temp=choice -1;
			card= list.get(temp);
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
		super.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName() + " uses Leader Card "+ card.getName()));
		}
	
	
	

	
}
