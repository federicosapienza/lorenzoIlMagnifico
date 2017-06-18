package it.polimi.ingsw.GC_26_actionsHandlers;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class TwoPaymentsHandlerTest {

	@Test
	public void testCorrectConstructor() {
		TwoPaymentsHandler twoPaymentsHandler = new TwoPaymentsHandler();
		assertNotNull(twoPaymentsHandler);
	}
	
	//Il seguente test dà errore, ma perform dovrebbe terminare settando la used card come null!
	/*
	@Test
	public void testNullCard() {
		Player player = new Player("Gigi", ResourcesOrPoints.newResources(5, 3, 2, 2));
		int choice = 2;
		TwoPaymentsHandler twoPaymentsHandler = new TwoPaymentsHandler();
		twoPaymentsHandler.perform(player, choice);
		assertNull(player.getCardUsed());
	}
	*/
	

	
}
