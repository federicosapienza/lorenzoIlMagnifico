package it.polimi.ingsw.GC_26_client_clientLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.ActionNotification;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class MainClientViewTest {

	
	@Test
	public void testCorrectUpdate() {
		Player player1 = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		PlayerWallet wallet1 = new PlayerWallet(player1.getWarehouse());
		PlayerView playerview1 = new PlayerView(wallet1);
		//Action action = new Action(BoardZone.VENTURETOWER, 1, player1.getFamilyMembers().getfamilyMember(Colour.BLACK).getColour(), 0);
		//ActionNotification actionNotification = new ActionNotification(player1.getName(), action);
		MainClientView mainClientView = new MainClientView();
		mainClientView.setPlayerUsername("David");
		mainClientView.updatePlayerWallet(wallet1);
		assertTrue(mainClientView.getThisPlayer().getName() == playerview1.getName() && mainClientView.getThisPlayer().getFamilyMembers() == playerview1.getFamilyMembers());
		//mainClientView.getBoard().update(actionNotification);
		//assertTrue(mainClientView.getBoard().getVenturesTower().get(0).getPlayersHere().containsKey(player1.getName()));
		//assertTrue(mainClientView.getBoard().getBuildingsTower().isEmpty());
		//mainClientView.updatePlayerWallet(wallet1);
		//assertTrue(mainClientView.getThisPlayer().getName() == "David");
		
	}

}
