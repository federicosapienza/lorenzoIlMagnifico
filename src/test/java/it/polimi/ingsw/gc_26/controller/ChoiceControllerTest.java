package it.polimi.ingsw.gc_26.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.gc_26.controller.ChoiceController;
import it.polimi.ingsw.gc_26.json_reader.BonusInterface;
import it.polimi.ingsw.gc_26.json_reader.Cards;
import it.polimi.ingsw.gc_26.json_reader.ReadAll;
import it.polimi.ingsw.gc_26.json_reader.TimerValuesInterface;
import it.polimi.ingsw.gc_26.messages.Request;
import it.polimi.ingsw.gc_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.game.game_logic.Game;
import it.polimi.ingsw.gc_26.model.handlers.action_handlers.MainActionHandler;
import it.polimi.ingsw.gc_26.model.player.Player;
import it.polimi.ingsw.gc_26.model.player.PlayerStatus;

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
