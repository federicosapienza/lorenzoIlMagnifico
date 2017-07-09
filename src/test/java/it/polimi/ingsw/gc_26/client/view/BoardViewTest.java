package it.polimi.ingsw.gc_26.client.view;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.gc_26.client.view.BoardView;
import it.polimi.ingsw.gc_26.client.view.PositionView;
import it.polimi.ingsw.gc_26.messages.action.Action;
import it.polimi.ingsw.gc_26.messages.action.ActionNotification;
import it.polimi.ingsw.gc_26.messages.describers.PositionDescriber;
import it.polimi.ingsw.gc_26.model.game.game_components.board.BoardZone;
import it.polimi.ingsw.gc_26.model.game.game_components.dices.Colour;
import it.polimi.ingsw.gc_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.player.Player;

public class BoardViewTest {

	Player player = new Player("David", ResourcesOrPoints.newResources(0, 0, 0, 0));
	Player player2 = new Player("Fred", ResourcesOrPoints.newResources(6, 3, 2, 2));
	List<Player> playersList = new ArrayList<Player>();
	Action action = new Action(BoardZone.VENTURETOWER, 1, player.getFamilyMembers().getfamilyMember(Colour.BLACK).getColour(), 1);
	ActionNotification actionNotification = new ActionNotification(player.getName(), action);
	Action action2 = new Action(BoardZone.VENTURETOWER, 1, player2.getFamilyMembers().getfamilyMember(Colour.ORANGE).getColour(), 1);
	ActionNotification actionNotification2 = new ActionNotification(player2.getName(), action2);
	PositionDescriber posDescr = new PositionDescriber(BoardZone.VENTURETOWER, 1, 1, "resources or points in position 1");
	PositionView posView = new PositionView(posDescr);
	PositionDescriber posDescr2 = new PositionDescriber(BoardZone.VENTURETOWER, 2, 2, "resources or points in position 2");
	PositionView posView2 = new PositionView(posDescr2);
	BoardView boardView = new BoardView();

	@Test
	public void testAddPosition() {
		boardView.addPosition(posView);
		boardView.addPosition(posView2);
		assertTrue(boardView.getVenturesTower().size() == 2 && boardView.getVenturesTower().get(0) == posView && boardView.getVenturesTower().get(1) == posView2);
	}

	@Test
	public void testUpdate() {
		boardView.addPosition(posView);
		boardView.addPosition(posView2);
		boardView.update(actionNotification);
		assertTrue(boardView.getVenturesTower().get(0).getPlayersHere().get(player.getName()) == Colour.BLACK && boardView.getVenturesTower().get(0).getCardHere() == null);
	}
	
	@Test
	public void testClean() {
		boardView.addPosition(posView);
		boardView.addPosition(posView2);
		boardView.update(actionNotification);
		boardView.cleanBoard();
		assertTrue(boardView.getBuildingsTower().size() == 0 && boardView.getCharactersTower().size() == 0);
	}
}