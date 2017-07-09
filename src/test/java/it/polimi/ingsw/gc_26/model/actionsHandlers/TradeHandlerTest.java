package it.polimi.ingsw.gc_26.model.actionsHandlers;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.effects.Effect;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.effects.TradeEffect;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.payments.ResourcesPayment;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.payments.common.Payment;
import it.polimi.ingsw.gc_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.handlers.action_handlers.HarvestAndProductionHandler;
import it.polimi.ingsw.gc_26.model.handlers.choice_handlers.TradeHandler;
import it.polimi.ingsw.gc_26.model.player.Player;

public class TradeHandlerTest {
	HarvestAndProductionHandler harvestAndProductionHandler = new HarvestAndProductionHandler();
	TradeHandler TradeHandler = new TradeHandler();
	
	@Test
	public void isPossibleTrueTest() {
		ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResources(1, 0, 0, 0);
		Player player = new Player("Leon", resourcesOrPoints);
		ResourcesOrPoints resourcesOrPoints2 = ResourcesOrPoints.newResources(0, 0, 2, 0);
		Payment payment = new ResourcesPayment(resourcesOrPoints2);
		ResourcesOrPoints resourcesOrPoints3 = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 1, 0);
		Effect immediate = new ReceiveResourcesOrPointsEffect(resourcesOrPoints3);
		ResourcesOrPoints resourcesOrPoints4 = ResourcesOrPoints.newResources(1, 0,0,0);
		Effect permanent = new TradeEffect(resourcesOrPoints4, resourcesOrPoints3, null, null);
		DevelopmentCard developmentCard = DevelopmentCardImplementation.buildingCard("Chapel", 1, payment, immediate, permanent, 2);
		player.setCardUsed(developmentCard);
		player.setTemporaryWarehouse();
		assertTrue(TradeHandler.isPossible(player, 1));
	}
	
	@Test
	public void isPossibleFalseTest() {
		ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResources(0, 0, 0, 0);
		Player player = new Player("Leon", resourcesOrPoints);
		ResourcesOrPoints resourcesOrPoints2 = ResourcesOrPoints.newResources(0, 0, 2, 0);
		Payment payment = new ResourcesPayment(resourcesOrPoints2);
		ResourcesOrPoints resourcesOrPoints3 = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 1, 0);
		Effect immediate = new ReceiveResourcesOrPointsEffect(resourcesOrPoints3);
		ResourcesOrPoints resourcesOrPoints4 = ResourcesOrPoints.newResources(1, 0,0,0);
		Effect permanent = new TradeEffect(resourcesOrPoints4, resourcesOrPoints3, null, null);
		DevelopmentCard developmentCard = DevelopmentCardImplementation.buildingCard("Chapel", 1, payment, immediate, permanent, 2);
		player.setCardUsed(developmentCard);
		player.setTemporaryWarehouse();
		assertFalse(TradeHandler.isPossible(player, 1));
	}
	
	@Test
	public void testRefusedTrade() {
		ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResources(1, 0, 0, 0);
		Player player = new Player("Leon", resourcesOrPoints);
		ResourcesOrPoints resourcesOrPoints2 = ResourcesOrPoints.newResources(0, 0, 2, 0);
		Payment payment = new ResourcesPayment(resourcesOrPoints2);
		ResourcesOrPoints resourcesOrPoints3 = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 1, 0);
		Effect immediate = new ReceiveResourcesOrPointsEffect(resourcesOrPoints3);
		ResourcesOrPoints resourcesOrPoints4 = ResourcesOrPoints.newResources(1, 0,0,0);
		Effect permanent = new TradeEffect(resourcesOrPoints4, resourcesOrPoints3, null, null);
		DevelopmentCard developmentCard = DevelopmentCardImplementation.buildingCard("Chapel", 1, payment, immediate, permanent, 2);
		player.setCardUsed(developmentCard);
		player.setTemporaryWarehouse();
		assertTrue(TradeHandler.isPossible(player, 0));
	}
	
 
	@Test
	public void performTest() {
		ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResources(3, 0, 5, 0);
		Player player = new Player("Leon", resourcesOrPoints);
		ResourcesOrPoints resourcesOrPoints2 = ResourcesOrPoints.newResources(0, 0, 2, 0);
		Payment payment = new ResourcesPayment(resourcesOrPoints2);
		ResourcesOrPoints resourcesOrPoints3 = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 1, 0);
		Effect immediate = new ReceiveResourcesOrPointsEffect(resourcesOrPoints3);
		ResourcesOrPoints resourcesOrPoints4 = ResourcesOrPoints.newResources(1, 0,0,0);
		ResourcesOrPoints resourcesOrPoints5 = ResourcesOrPoints.newPoints(0, 0, 1, 0);		
		Effect permanent = new TradeEffect(resourcesOrPoints4, null,resourcesOrPoints5, null);
		DevelopmentCard developmentCard = DevelopmentCardImplementation.buildingCard("Chapel", 1, payment, immediate, permanent, 2);
		player.setCardUsed(developmentCard);
		player.setTemporaryWarehouse();
		TradeHandler.perform(player, 1);
		assertEquals(1, player.getWarehouse().getFaithPoints());
	}
}
