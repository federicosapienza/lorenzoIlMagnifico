package it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments;


import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments.MilitaryPointPayment;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;

public class MilitaryPointPaymentTest {

	int toSpend;
	int needed;
	@Test
	public void testPlayerCannotGetThis() {
		toSpend = 3;
		needed = 5;
		MilitaryPointPayment militaryPointPayment = new MilitaryPointPayment(toSpend, needed);
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		assertFalse(militaryPointPayment.canPlayerGetThis(player, DevelopmentCardTypes.VENTURECARD));
	}

	@Test
	public void testPlayerCanGetThis() {
		int toSpend = 3;
		int needed = 5;
		MilitaryPointPayment militaryPointPayment = new MilitaryPointPayment(toSpend, needed);
		Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		player.getWarehouse().add(ResourcesOrPoints.newPoints(0, 7, 0, 0));
		assertTrue(militaryPointPayment.canPlayerGetThis(player, DevelopmentCardTypes.VENTURECARD));
	}
	
	
	
	@Test
	public void testPlayerCannotGetThisAfterPaying(){
		MilitaryPointPayment militaryPointPayment = new MilitaryPointPayment(3, 5);
		Player player = new Player("David", ResourcesOrPoints.newResourcesOrPoints(5, 3, 2, 2, 0, 0, 0, 0));
		Player player2 = new Player("Albert", player.getWarehouse().getStatus());
		player.getWarehouse().add(ResourcesOrPoints.newPoints(0, 6, 0, 0));
		player2.getWarehouse().add(ResourcesOrPoints.newResourcesOrPoints(1, 0, 0, 0, 0, 6, 6, 6));
		
		militaryPointPayment.pay(player2, DevelopmentCardTypes.VENTURECARD);
		assertTrue(militaryPointPayment.canPlayerGetThis(player, DevelopmentCardTypes.VENTURECARD) && 
				!militaryPointPayment.canPlayerGetThis(player2, DevelopmentCardTypes.VENTURECARD));
	}
	
}
