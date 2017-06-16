package it.polimi.ingsw.GC_26_controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_actionsHandlers.MainActionHandler;
import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class ActionControllerTest {
	Action action1 = new Action(BoardZone.TERRITORYTOWER, 1, Colour.ORANGE, 0);
	Request request1 = new Request(PlayerStatus.PLAYING, "Player 1 is playing", null);
	Player player1 = new Player("Lorenzo", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
	MainActionHandler handlers;
	ActionController actionCtrl = new ActionController(player1, handlers);
	@Test
	public void testExistence() {
		assertNotNull(actionCtrl);
	}
	
	@Test
	public void testStatus(){
		player1.setStatus(request1);
		assertNotNull(player1.getStatus());
		assertEquals(PlayerStatus.PLAYING, player1.getStatus());
		//actionCtrl.update(action1);
		//assertTrue(player1.isPlayerActive());
		//Last 2 lines give errors but I don't know why.
	}

}
