package it.polimi.ingsw.GC_26_actionsHandlers;

import static org.junit.Assert.*;



import org.junit.Test;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_board.CouncilPalace;
import it.polimi.ingsw.GC_26_board.MarketPosition;
import it.polimi.ingsw.GC_26_board.MultiplePosition;
import it.polimi.ingsw.GC_26_board.SinglePosition;
import it.polimi.ingsw.GC_26_board.Tower;
import it.polimi.ingsw.GC_26_board.TowerPosition;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26_cards.payments.Payment;
import it.polimi.ingsw.GC_26_cards.payments.ResourcesPayment;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

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
	
	//Tip : start from method invocation that suggest you what you need
	
	/*@Test
	public void testCanMemberGoToPositionSinglePositionTrue() {
		ResourcesOrPoints resourcesOrPointsPosition = ResourcesOrPoints.newResources(1, 1, 1, 1);
		SinglePosition position = new TowerPosition(3, resourcesOrPointsPosition, 5);
		ResourcesOrPoints startingResourcesOrPoints = ResourcesOrPoints.newResources(2, 2, 2, 2);
		Player player = new Player("Leon", startingResourcesOrPoints);
		FamilyMember familyMember = new FamilyMember(Colour.BLACK, player);
		Action action = new Action(BoardZone.BUILDINGTOWER, 3, Colour.BLACK, 0);
		familyMember.setValue(7);
		ResourcesOrPoints[] arrayResOrPt = new ResourcesOrPoints[4];
		arrayResOrPt[0]=resourcesOrPointsPosition;
		arrayResOrPt[1]=resourcesOrPointsPosition;
		arrayResOrPt[2]=resourcesOrPointsPosition;
		arrayResOrPt[3]=resourcesOrPointsPosition;
		Tower tower = new Tower(arrayResOrPt);
		tower.clearCardsAndFamilyMembers();
		assertTrue(actionCheckerHandler.canMemberGoToPosition(position, player, familyMember, action));
	}*/
	
	/*@Test
	public void testCanMemberGoToPositionMultiplePositionTrue() {
		ResourcesOrPoints resourcesOrPointsCouncil = ResourcesOrPoints.newResources(0, 0, 0, 0);
		CouncilPalace councilPalace = new CouncilPalace(resourcesOrPointsCouncil, 1);
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 5, 5, 5);
		Player player = new Player("Leon", startingResources);
		FamilyMember familyMember = new FamilyMember(Colour.BLACK,player);
		player.getFamilyMembers().getfamilyMember(Colour.BLACK).setValue(6);
		Action action = new Action(BoardZone.MARKET, 3, familyMember.getColour(), 0);
		assertTrue(actionCheckerHandler.canMemberGoToPosition(councilPalace, player, familyMember, action));
	}*/

	/*@Test
	public void testCanMemberGoToPositionMultiplePositionTrue() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCanMemberGoToPositionMultiplePositionFalse() {
		fail("Not yet implemented");
	}*/

}
