package it.polimi.ingsw.GC_26.client.cli;

import java.util.Set;

import it.polimi.ingsw.GC_26.client.view.BoardView;
import it.polimi.ingsw.GC_26.client.view.MainClientView;
import it.polimi.ingsw.GC_26.client.view.PlayerView;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the interface of the Output
 *
 */
public interface Output {
	void printBoard(BoardView board);
	void printExcommunicationTiles(BoardView board);
	void printString(String string);
	void printResources(PlayerView player); // for a given player prints name and his status of resources
	void printCompleteStatus(PlayerView player); // for the player given displays name, permanents effect, personal board.
	void printRankings(MainClientView view);  //prints the rankings: military points , victory points, faith points
	void printFamilyMembers(PlayerView player);
	void printCards(PlayerView thisPlayer);
	void printCards(Set<CardDescriber> cards);
	void printLeaderCards(Set<CardDescriber> cards);
	void printFinalRankings(MainClientView view);
	void printTitle();
}
