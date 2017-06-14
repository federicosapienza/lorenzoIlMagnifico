package it.polimi.ingsw.GC_26_client_clientLogic;

import it.polimi.ingsw.GC_26_board.Board;

public interface Output {
	void printBoard(BoardView board);
	void printExcommunicationTiles(BoardView board);
	void printString(String string);
	void printResources(PlayerView player); // for a given player prints name and his status of resources
	void printCompleteStatus(PlayerView player); // for the player given displays name, permanents effect, personal board.
	void printRankings(MainClientView view);  //prints the rankings: military points , victory points, faith points
	void printFamilyMembers(PlayerView player);
}
