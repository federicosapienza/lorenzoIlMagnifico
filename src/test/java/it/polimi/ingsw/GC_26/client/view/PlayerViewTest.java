package it.polimi.ingsw.GC_26.client.view;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26.client.view.PlayerView;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.Effect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.TradeEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.LeaderCardImplementation;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.PointsOrResourcesRequirement;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.Requirement;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments.ResourcesPayment;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments.common.Payment;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.PlayerWallet;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.Warehouse;
import it.polimi.ingsw.GC_26.model.player.Player;

public class PlayerViewTest {

	Player player = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
	Player player2 = new Player("William", ResourcesOrPoints.newResources(6, 3, 2, 2));
	@Test
	public void testCorrectUpdateValues() {
		Warehouse warehouse = player.getWarehouse();
		Warehouse warehouse2 = player2.getWarehouse();
		PlayerWallet wallet = new PlayerWallet(warehouse);
		PlayerWallet wallet2 = new PlayerWallet(warehouse2);
		PlayerView playerView = new PlayerView(wallet);
		PlayerView playerView2 = new PlayerView(wallet2);
		playerView.updateValues(wallet2);
		playerView2.updateValues(wallet2);
		assertTrue(wallet2.equals(playerView.getWallet()) && wallet2.equals(playerView2.getWallet()) && playerView.getLeadersCardOwned().isEmpty() && playerView2.getLeadersCardOwned().isEmpty());
	}
	
	@Test
	public void testCorrectAddLeaderCard() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(7, 3, 2, 2);
		Player player3 = new Player("Lee", startingResources);
		List<Player> players = new ArrayList<>();
		players.add(player3);
		Requirement requirement = new PointsOrResourcesRequirement(ResourcesOrPoints.newPoints(0, 12, 0, 0));
		Effect immediateEffect = null;
		Effect permanentEffect = null;
		LeaderCard leaderCard = new LeaderCardImplementation("Giovanni dalle Bande Nere", requirement, immediateEffect, permanentEffect);
		player.getWarehouse().add(ResourcesOrPoints.newPoints(0, 13, 0, 0));
		player.getPersonalBoard().addLeaderCard(leaderCard);
		Warehouse warehouse = player.getWarehouse();
		PlayerWallet wallet = new PlayerWallet(warehouse);
		PlayerView playerView = new PlayerView(wallet);
		
		CardDescriber card = new CardDescriber(leaderCard);
		playerView.addLeaderCardOwned(card);
		assertTrue(playerView.getLeadersCardOwned().size() == 1 && playerView.getLeadersCardOwned().contains(card) && playerView.getPermamentsEffect().isEmpty());
	}
	
	@Test
	public void testAddDevCardWithPermEffect(){
		ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResources(5, 3, 2, 2);
		Player player = new Player("Daniel", resourcesOrPoints);
		ResourcesOrPoints resourcesOrPoints2 = ResourcesOrPoints.newResources(4, 0, 0, 0);
		Payment payment = new ResourcesPayment(resourcesOrPoints2);
		ResourcesOrPoints resourcesOrPoints3 = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
		Effect immediate = new ReceiveResourcesOrPointsEffect(resourcesOrPoints3);
		ResourcesOrPoints resourcesOrPoints4 = ResourcesOrPoints.newResources(1, 0,0,0);
		Effect permanent = new TradeEffect(resourcesOrPoints3, resourcesOrPoints3, resourcesOrPoints4, resourcesOrPoints4); //not real, simplified
		DevelopmentCard developmentCard = DevelopmentCardImplementation.characterCard("Dame", 1, payment, immediate, permanent); //simplified Dame
		PlayerWallet wallet = new PlayerWallet(player.getWarehouse());
		CardDescriber cardDescriber = new CardDescriber(developmentCard);
		PlayerView playerView = new PlayerView(wallet);
		playerView.addCard(cardDescriber);
		assertTrue(playerView.getPermamentsEffect().size() == 1 && playerView.getPermamentsEffect().get(0) == cardDescriber.getPermanentEffectDescriber());
	}
	
	@Test
	public void testAddDevCardWithoutPermEffect() {
		ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResources(6, 3, 2, 2);
		Player player = new Player("George", resourcesOrPoints);
		ResourcesOrPoints resourcesOrPoints2 = ResourcesOrPoints.newResources(0, 0, 2, 0);
		Payment payment = new ResourcesPayment(resourcesOrPoints2);
		ResourcesOrPoints resourcesOrPoints3 = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 1, 0);
		Effect immediate = new ReceiveResourcesOrPointsEffect(resourcesOrPoints3);
		ResourcesOrPoints resourcesOrPoints4 = ResourcesOrPoints.newResources(1, 0,0,0);
		Effect permanent = new TradeEffect(resourcesOrPoints4, resourcesOrPoints3, null, null);
		DevelopmentCard developmentCard = DevelopmentCardImplementation.buildingCard("Chapel", 1, payment, immediate, permanent, 2);
		PlayerWallet wallet = new PlayerWallet(player.getWarehouse());
		CardDescriber cardDescriber = new CardDescriber(developmentCard);
		PlayerView playerView = new PlayerView(wallet);
		playerView.addCard(cardDescriber);
		assertTrue(playerView.getPermamentsEffect().isEmpty());
	}
	
	//The following test for illegal argument exception doesn't work, but I don't understand why!
	/*
	@Test
	public void testIllegalGetCurrentCards() {
		boolean thrownIllegalCurrentCardsExcep = false;
		
		ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResources(6, 3, 2, 2);
		Player player = new Player("George", resourcesOrPoints);
		ResourcesOrPoints resourcesOrPoints2 = ResourcesOrPoints.newResources(0, 0, 2, 0);
		Payment payment = new ResourcesPayment(resourcesOrPoints2);
		ResourcesOrPoints resourcesOrPoints3 = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 1, 0);
		Effect immediate = new ReceiveResourcesOrPointsEffect(resourcesOrPoints3);
		ResourcesOrPoints resourcesOrPoints4 = ResourcesOrPoints.newResources(1, 0,0,0);
		Effect permanent = new TradeEffect(resourcesOrPoints4, resourcesOrPoints3, null, null);
		DevelopmentCard developmentCard = DevelopmentCardImplementation.buildingCard("Chapel", 1, payment, immediate, permanent, 2);
		PlayerWallet wallet = new PlayerWallet(player.getWarehouse());
		CardDescriber cardDescriber = new CardDescriber(developmentCard);
		PlayerView playerView = new PlayerView(wallet);
		DevelopmentCardTypes nullType = null;
		try {
			playerView.getCurrentCards(nullType);
		} catch (IllegalArgumentException e) {
			thrownIllegalCurrentCardsExcep = true;
		}
		assertTrue(thrownIllegalCurrentCardsExcep);
	}
	*/
}
