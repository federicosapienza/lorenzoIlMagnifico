package it.polimi.ingsw.gc_26.model.handlers.choice_handlers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.effects.Effect;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.payments.MilitaryPointPayment;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.payments.ResourcesPayment;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.payments.TwoOrPayments;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.payments.common.Payment;
import it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.handlers.choice_handlers.TwoPaymentsHandler;
import it.polimi.ingsw.gc_26.model.player.Player;

public class TwoPaymentsHandlerTest {
	List<Player> players= new ArrayList<>();
	TwoPaymentsHandler twoPaymentsHandler = new TwoPaymentsHandler(players);
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
	public void testNullCardUsedAfterPerform() {
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
		assertNull(player.getCardUsed());
	}

	
	
	
}
