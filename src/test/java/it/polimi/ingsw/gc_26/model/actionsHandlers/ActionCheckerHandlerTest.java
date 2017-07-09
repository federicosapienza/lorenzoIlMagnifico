package it.polimi.ingsw.gc_26.model.actionsHandlers;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.gc_26.messages.action.Action;
import it.polimi.ingsw.gc_26.model.game.game_components.board.BoardZone;
import it.polimi.ingsw.gc_26.model.game.game_components.board.positions.CouncilPalace;
import it.polimi.ingsw.gc_26.model.game.game_components.board.positions.MarketPosition;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.effects.Effect;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.payments.ResourcesPayment;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.payments.common.Payment;
import it.polimi.ingsw.gc_26.model.game.game_components.dices.Colour;
import it.polimi.ingsw.gc_26.model.game.game_components.familyMembers.FamilyMember;
import it.polimi.ingsw.gc_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.game.game_logic.GameParameters;
import it.polimi.ingsw.gc_26.model.handlers.action_handlers.ActionCheckerHandler;
import it.polimi.ingsw.gc_26.model.player.Player;

public class ActionCheckerHandlerTest {

	

	ActionCheckerHandler actionCheckerHandler = new ActionCheckerHandler();
	
	@Test
	public void testAreServantsEnoughTrue() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 2, 0, 0);
		Player player = new Player("Leon", startingResources);
		Action action = new Action(BoardZone.BUILDINGTOWER, 1, Colour.BLACK, 1);
		player.setTemporaryWarehouse();
		assertTrue(actionCheckerHandler.areServantsEnough(player, action));
	}
	
	@Test
	public void testAreServantsEnoughFalse() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 2, 0, 0);
		Player player = new Player("Leon", startingResources);
		Action action = new Action(BoardZone.BUILDINGTOWER, 1, Colour.BLACK, 3);
		player.setTemporaryWarehouse();
		assertFalse(actionCheckerHandler.areServantsEnough(player, action));
	}

	@Test
	public void testIsFamilyMemberFreeTrue() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 2, 0, 0);
		Player player = new Player("Leon", startingResources);
		FamilyMember familyMember = new FamilyMember(Colour.BLACK, player);
		familyMember.setFree();
		assertTrue(actionCheckerHandler.isFamilyMemberFree(familyMember, player));
	}
	
	@Test
	public void testIsFamilyMemberFreeFalse() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 2, 0, 0);
		Player player = new Player("Leon", startingResources);
		FamilyMember familyMember = new FamilyMember(Colour.BLACK, player);
		familyMember.setUsed();
		assertFalse(actionCheckerHandler.isFamilyMemberFree(familyMember, player));
	}

	@Test
	public void testCheckMaximumNumberOfCardsNotReachedTrue() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 2, 0, 0);
		Player player = new Player("Leon", startingResources);
		Action action = new Action(BoardZone.BUILDINGTOWER, 1, Colour.BLACK, 3);
		ResourcesOrPoints resourcesOrPointsForPayment = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		Payment payment = new ResourcesPayment(resourcesOrPointsForPayment);
		ResourcesOrPoints resourcesOrPointsForImmediate = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		ResourcesOrPoints resourcesOrPointsForPermanent = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		Effect immediate = new ReceiveResourcesOrPointsEffect(resourcesOrPointsForImmediate);
		Effect permanent = new ReceiveResourcesOrPointsEffect(resourcesOrPointsForPermanent);
		DevelopmentCard territory = DevelopmentCardImplementation.territoryCard("woods", 1, payment, immediate, permanent, 1);
		player.getPersonalBoard().add(territory);
		assertTrue(actionCheckerHandler.checkMaximumNumberOfCardsNotReached(player, action));
	}

	@Test
	public void testCheckMaximumNumberOfCardsNotReachedFalse() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 2, 0, 0);
		Player player = new Player("Leon", startingResources);
		Action action = new Action(BoardZone.TERRITORYTOWER, 1, Colour.BLACK, 3);
		ResourcesOrPoints resourcesOrPointsForPayment = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		Payment payment = new ResourcesPayment(resourcesOrPointsForPayment);
		ResourcesOrPoints resourcesOrPointsForImmediate = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		ResourcesOrPoints resourcesOrPointsForPermanent = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		Effect immediate = new ReceiveResourcesOrPointsEffect(resourcesOrPointsForImmediate);
		Effect permanent = new ReceiveResourcesOrPointsEffect(resourcesOrPointsForPermanent);
		DevelopmentCard territory = DevelopmentCardImplementation.territoryCard("woods", 1, payment, immediate, permanent, 1);
		for(int i=0 ;i < GameParameters.getMaxNumOfCards(); i++){
			player.getPersonalBoard().add(territory);
			}
		assertFalse(actionCheckerHandler.checkMaximumNumberOfCardsNotReached(player,action));
	}
	
	
	
	@Test
	public void testCanMemberGoToPositionSinglePositionTrue() {
		ResourcesOrPoints resourcesOrPointsPosition = ResourcesOrPoints.newResources(1, 1, 1, 1);
		MarketPosition position = new MarketPosition(1, resourcesOrPointsPosition, 1);
		ResourcesOrPoints startingResourcesOrPoints = ResourcesOrPoints.newResources(2, 2, 2, 2);
		Player player = new Player("Leon", startingResourcesOrPoints);
		FamilyMember familyMember = player.getFamilyMembers().getfamilyMember(Colour.BLACK);
		familyMember.setValue(5);
		position.clear();
		Action action = new Action(BoardZone.BUILDINGTOWER, 3, familyMember.getColour(), 0);
		assertTrue(actionCheckerHandler.canMemberGoToPosition(position, player, familyMember, action));
	}
	
	@Test
	public void testCanMemberGoToPositionSinglePositionFalse() {
		ResourcesOrPoints resourcesOrPointsPosition = ResourcesOrPoints.newResources(1, 1, 1, 1);
		MarketPosition position = new MarketPosition(1, resourcesOrPointsPosition, 5);
		ResourcesOrPoints startingResourcesOrPoints = ResourcesOrPoints.newResources(2, 2, 2, 2);
		Player player = new Player("Leon", startingResourcesOrPoints);
		FamilyMember familyMember = player.getFamilyMembers().getfamilyMember(Colour.BLACK);
		familyMember.setValue(3);
		position.clear();
		Action action = new Action(BoardZone.BUILDINGTOWER, 3, familyMember.getColour(), 0);
		assertFalse(actionCheckerHandler.canMemberGoToPosition(position, player, familyMember, action));
	}
	
	@Test
	public void testCanMemberGoToPositionSinglePositionTruePermanentModifiersON() {
		ResourcesOrPoints resourcesOrPointsPosition = ResourcesOrPoints.newResources(1, 1, 1, 1);
		MarketPosition position = new MarketPosition(1, resourcesOrPointsPosition, 5);
		ResourcesOrPoints startingResourcesOrPoints = ResourcesOrPoints.newResources(10, 2, 2, 2);
		Player player = new Player("Leon", startingResourcesOrPoints);
		FamilyMember familyMember = player.getFamilyMembers().getfamilyMember(Colour.BLACK);
		familyMember.setValue(6);
		FamilyMember familyMember2 = player.getFamilyMembers().getfamilyMember(Colour.ORANGE);
		position.setFamilyMember(familyMember2);
		familyMember2.setValue(6);
		player.getPermanentModifiers().setGoingInOccupiedPositionsAllowed();
		Action action = new Action(BoardZone.BUILDINGTOWER, 3, familyMember.getColour(), 0);
		assertTrue(actionCheckerHandler.canMemberGoToPosition(position, player, familyMember, action));
	}
	
	@Test
	public void testCanMemberGoToPositionMultiplePositionTrue() {
		ResourcesOrPoints resourcesOrPointsCouncil = ResourcesOrPoints.newResources(0, 0, 0, 0);
		CouncilPalace councilPalace = new CouncilPalace(resourcesOrPointsCouncil, 1);
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		FamilyMember familyMember = player.getFamilyMembers().getfamilyMember(Colour.BLACK);
		Action action = new Action(BoardZone.COUNCILPALACE, 3, familyMember.getColour(), 0);
		familyMember.setValue(6);
		assertTrue(actionCheckerHandler.canMemberGoToPosition(councilPalace, player, familyMember, action));

	}

	

	@Test
	public void testCanMemberGoToPositionMultiplePositionFalse() {
		ResourcesOrPoints resourcesOrPointsCouncil = ResourcesOrPoints.newResources(0, 0, 0, 0);
		CouncilPalace councilPalace = new CouncilPalace(resourcesOrPointsCouncil, 5);
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		FamilyMember familyMember = player.getFamilyMembers().getfamilyMember(Colour.BLACK);
		familyMember.setValue(1);
		Action action = new Action(BoardZone.COUNCILPALACE, 3, familyMember.getColour(), 0);
		assertFalse(actionCheckerHandler.canMemberGoToPosition(councilPalace, player, familyMember, action));
	}

	
	
	@Test
	public void towerActionValidationTrueTest(){
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		Action action = new Action(BoardZone.TERRITORYTOWER, 1,player.getFamilyMembers().getfamilyMember(Colour.BLACK).getColour(), 0);
		assertTrue(actionCheckerHandler.towerActionValidation(player, action));
	}
	
	@Test
	public void towerActionValidationFalse1Test(){
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		Action action = new Action(BoardZone.TERRITORYTOWER, -1,player.getFamilyMembers().getfamilyMember(Colour.BLACK).getColour(), 0);
		assertFalse(actionCheckerHandler.towerActionValidation(player, action));
	}
	
	@Test
	public void towerActionValidationFalse2Test(){
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		Action action = new Action(BoardZone.TERRITORYTOWER, 5,player.getFamilyMembers().getfamilyMember(Colour.BLACK).getColour(), 0);
		assertFalse(actionCheckerHandler.towerActionValidation(player, action));
	}
	
	@Test
	public void marketActionValidationTrueTest(){
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		Action action = new Action(BoardZone.MARKET, 4,player.getFamilyMembers().getfamilyMember(Colour.BLACK).getColour(), 0);
		assertTrue(actionCheckerHandler.marketActionValidation(player, action, 4));	
	}
	
	@Test
	public void marketActionValidationFalse1Test(){
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		Action action = new Action(BoardZone.MARKET, 0,player.getFamilyMembers().getfamilyMember(Colour.BLACK).getColour(), 0);
		assertFalse(actionCheckerHandler.marketActionValidation(player, action, 4));	
	}
	
	@Test
	public void marketActionValidationFalse2Test(){
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		Action action = new Action(BoardZone.MARKET, 3,player.getFamilyMembers().getfamilyMember(Colour.BLACK).getColour(), 0);
		assertFalse(actionCheckerHandler.marketActionValidation(player, action, 2));	
	}
	
	@Test
	public void productionAndHarvestValidationTrueTest(){
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		Action action = new Action(BoardZone.PRODUCTION, 2,player.getFamilyMembers().getfamilyMember(Colour.BLACK).getColour(), 0);
		assertTrue(actionCheckerHandler.productionAndHarvestValidation(player, action, 4));
	}
	
	@Test
	public void productionAndHarvestValidationFalse1Test(){
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		Action action = new Action(BoardZone.PRODUCTION, 2,player.getFamilyMembers().getfamilyMember(Colour.BLACK).getColour(), 0);
		assertFalse(actionCheckerHandler.productionAndHarvestValidation(player, action, 2));
	}
	
	@Test
	public void productionAndHarvestValidationFalse2Test(){
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		Action action = new Action(BoardZone.PRODUCTION, 3,player.getFamilyMembers().getfamilyMember(Colour.BLACK).getColour(), 0);
		assertFalse(actionCheckerHandler.productionAndHarvestValidation(player, action, 4));
	}
	
	
	
	
	
	
	
	
}
