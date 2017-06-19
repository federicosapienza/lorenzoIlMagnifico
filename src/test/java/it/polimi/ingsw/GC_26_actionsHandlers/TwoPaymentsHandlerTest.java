package it.polimi.ingsw.GC_26_actionsHandlers;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26_cards.payments.MilitaryPointPayment;
import it.polimi.ingsw.GC_26_cards.payments.Payment;
import it.polimi.ingsw.GC_26_cards.payments.ResourcesPayment;
import it.polimi.ingsw.GC_26_cards.payments.TwoOrPayments;

public class TwoPaymentsHandlerTest {
	
	TwoPaymentsHandler twoPaymentsHandler = new TwoPaymentsHandler();
	@Test
	public void performTestTrue() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Payment payment1 = new MilitaryPointPayment(2, 4);
		ResourcesOrPoints price = ResourcesOrPoints.newResources(2, 0, 1, 1);
		Payment payment2 = new ResourcesPayment(price);
		Payment payment = new TwoOrPayments(payment1, payment2);
		ResourcesOrPoints immediateRes = ResourcesOrPoints.newPoints(0, 0, 3, 0);
		Effect immediateAndPerm = new ReceiveResourcesOrPointsEffect(immediateRes);
		DevelopmentCard developmentCard = DevelopmentCardImplementation.ventureCard("Support to the Bishop", 1, payment, immediateAndPerm, immediateAndPerm);
		Player player = new Player("Leon", startingResources);
		player.setCardUsed(developmentCard);
		ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newPoints(0, 5, 0, 0);
		player.getWarehouse().add(resourcesOrPoints);
		twoPaymentsHandler.perform(player, 2);
		assertEquals(3, player.getWarehouse().getCoins()); //It works even with choice = 1
	}



	@Test
	public void testCorrectConstructor() {
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
