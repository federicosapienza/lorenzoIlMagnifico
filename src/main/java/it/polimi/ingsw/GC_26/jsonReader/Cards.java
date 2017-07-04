package it.polimi.ingsw.GC_26.jsonReader;

import java.util.List;

import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.LeaderCard;

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
