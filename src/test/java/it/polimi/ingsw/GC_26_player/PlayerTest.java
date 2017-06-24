package it.polimi.ingsw.GC_26_player;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26_gameLogic.Game;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;
import it.polimi.ingsw.GC_26_readJson.ReadAll;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class PlayerTest {
	
	Request request1 = new Request(PlayerStatus.PLAYING, "Player 1 is playing", null);
	Request request2 = new Request(PlayerStatus.CHOOSINGPAYMENT, "Player 2 is choosing the payment", null);
	
	Cards cards;
	BonusInterface bonus;
	TimerValuesInterface times;
	List<ResourcesOrPoints[]> resourcesOrPointsList = new ArrayList<ResourcesOrPoints[]>();
	ReadAll readAll = new ReadAll();
	
	@Test
	public void testCorrectPlayersList() {
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		Player player1 = game.addPlayer("David");
		Player player2 = game.addPlayer("Steph");
		game.initialiseGame();
		game.startGame();
		assertTrue(player1.getWarehouse().getCoins() == 5 && player2.getWarehouse().getCoins() == 6 &&
				player1.getFamilyMembers().getfamilyMember(Colour.BLACK).getValue() == player2.getFamilyMembers().getfamilyMember(Colour.BLACK).getValue() &&
				player1.getFamilyMembers().getfamilyMember(Colour.NEUTRAL).getValue() == 0 &&
				player2.getFamilyMembers().getfamilyMember(Colour.NEUTRAL).getValue() == 0);
	}
	
	
	@Test
	public void testStatus(){
		readAll.start();
		resourcesOrPointsList = readAll.getBonus().getListOfResourcesOfPointsArray();
		cards = readAll.getCards();
		bonus = readAll.getBonus();
		times = readAll.getTimes();
		Game game = new Game(cards, bonus, times);
		Player player1 = game.addPlayer("David");
		Player player2 = game.addPlayer("Steph");
		game.initialiseGame();
		game.startGame();
		player2.setStatus(request2);
		assertTrue(player1.getStatus() == PlayerStatus.PLAYING && player2.getStatus() == PlayerStatus.CHOOSINGPAYMENT);
	}

}
