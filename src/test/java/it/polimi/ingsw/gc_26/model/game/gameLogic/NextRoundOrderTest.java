package it.polimi.ingsw.gc_26.model.game.gameLogic;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import it.polimi.ingsw.gc_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.game.game_logic.NextRoundOrder;
import it.polimi.ingsw.gc_26.model.player.Player;

public class NextRoundOrderTest {
	
	Player player1 = new Player("Aldo", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
	Player player2 = new Player("Bob", ResourcesOrPoints.newResourcesOrPoints(6, 3, 2, 2, 0, 0, 0, 0));
	List<Player> playersList = new ArrayList<Player>();
	NextRoundOrder nextRdOrd;
	Player player3 = new Player("Charles", ResourcesOrPoints.newResourcesOrPoints(7, 3, 2, 2, 0, 0, 0, 0));
	Player player4 = new Player("Greg", ResourcesOrPoints.newResourcesOrPoints(8, 3, 2, 2, 0, 0, 0, 0));
	Player player5 = new Player("Matt", ResourcesOrPoints.newResourcesOrPoints(9, 3, 2, 2, 0, 0, 0, 0));
	
	@Test
	public void testPlayerAddedIfNotContainedInNextRoundOrder() {
		nextRdOrd = new NextRoundOrder();
		nextRdOrd.nextRoundChanging(player1);
		nextRdOrd.nextRoundChanging(player2);
		assertTrue(nextRdOrd.getNextRoundOrder().size() == 2 && 
				nextRdOrd.getNextRoundOrder().get(0) == "Aldo" &&
				nextRdOrd.getNextRoundOrder().get(1) == "Bob");
	}
	
	@Test
	public void testDoNotAddSamePlayer() {
		nextRdOrd = new NextRoundOrder();
		nextRdOrd.nextRoundChanging(player1);
		nextRdOrd.nextRoundChanging(player1);
		assertTrue(nextRdOrd.getNextRoundOrder().size() == 1 && 
				nextRdOrd.getNextRoundOrder().get(0) == "Aldo");
	}

	@Test
	public void testChangeOrder() {
		nextRdOrd = new NextRoundOrder();
		nextRdOrd.nextRoundChanging(player1);
		nextRdOrd.nextRoundChanging(player2);
		nextRdOrd.nextRoundChanging(player3);
		playersList.add(player4);
		playersList.add(player5);
		List<Player> changedList;
		changedList = nextRdOrd.changeNextRoundOrder(playersList);
		assertTrue(changedList.size() == 2 && changedList.get(0).getName() == "Greg" &&
				changedList.get(1).getName() == "Matt");
	}
}
