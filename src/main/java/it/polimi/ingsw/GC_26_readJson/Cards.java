package it.polimi.ingsw.GC_26_readJson;

import java.util.List;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCard;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * Interface that represents the skeleton of the logic around the cards.
 * 
 */
public interface Cards {
	List<DevelopmentCard> getRandomDevelopmentCards(int period, DevelopmentCardTypes type);
	List<LeaderCard> getRandomLeaderCards(int numOfPlayers);
	List<ExcommunicationTile> getRandomExcommunicationTiles();

}
