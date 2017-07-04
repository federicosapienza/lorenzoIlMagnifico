package it.polimi.ingsw.GC_26_controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_gameLogic.Game;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;
import it.polimi.ingsw.GC_26_readJson.ReadAll;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class ChoiceControllerTest {

	Cards cards;
	BonusInterface bonus;
	TimerValuesInterface times;
	List<ResourcesOrPoints[]> resourcesOrPointsList = new ArrayList<ResourcesOrPoints[]>();
	ReadAll readAll = new ReadAll();
	
	@Test
	public void testCorrectPlayerStatusChangeForSuspendedPlayer() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		Player player1 = game.addPlayer("David");
		Player player2 = game.addPlayer("Steph");
		List<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		game.initialiseGame();
		game.startGame();
		MainActionHandler handler = new MainActionHandler(game.getGameElements());
		player1.setStatus(new Request(PlayerStatus.SUSPENDED, "Player 1 is suspended", null));
		ChoiceController choiceController = new ChoiceController(player1, handler);
		choiceController.update(0);
		assertTrue(player1.getStatus() == PlayerStatus.WAITINGHISTURN && handler.getGameElements().getGame().getPlayersNoMoreSuspended().contains(player1));
	}
	
	
	

}
