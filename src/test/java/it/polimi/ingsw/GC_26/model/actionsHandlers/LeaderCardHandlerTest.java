package it.polimi.ingsw.GC_26.model.actionsHandlers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26.model.actionHandlers.LeaderCardHandler;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.Effect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.LeaderCardImplementation;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.PointsOrResourcesRequirement;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.Requirement;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.utilities.IllegalActionException;

public class LeaderCardHandlerTest {
	
	
	@Test
	public void isPossibleTrueTest() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 0, 0, 0);
		Player player = new Player("Leon", startingResources);
		List<Player> players = new ArrayList<>();
		players.add(player);
		LeaderCardHandler leaderCardHandler = new LeaderCardHandler(players);
		Requirement requirement = new PointsOrResourcesRequirement(ResourcesOrPoints.newPoints(0, 12, 0, 0));
		Effect immediateEffect = null;
		Effect permanentEffect = null;
		LeaderCard leaderCard = new LeaderCardImplementation("Giovanni dalle Bande Nere", requirement, immediateEffect, permanentEffect);
		player.getWarehouse().add(ResourcesOrPoints.newPoints(0, 12, 0, 0));
		player.getPersonalBoard().addLeaderCard(leaderCard);
		assertTrue(leaderCardHandler.isPossible(player, 0));
	}

	
	@Test
	public void isPossibleFalse1Test() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 0, 0, 0);
		Player player = new Player("Leon", startingResources);
		List<Player> players = new ArrayList<>();
		players.add(player);
		LeaderCardHandler leaderCardHandler = new LeaderCardHandler(players);
		Requirement requirement = new PointsOrResourcesRequirement(ResourcesOrPoints.newPoints(0, 12, 0, 0));
		Effect immediateEffect = null;
		Effect permanentEffect = null;
		LeaderCard leaderCard = new LeaderCardImplementation("Giovanni dalle Bande Nere", requirement, immediateEffect, permanentEffect);
		player.getWarehouse().add(ResourcesOrPoints.newPoints(0, 12, 0, 0));
		player.getPersonalBoard().addLeaderCard(leaderCard);
		assertFalse(leaderCardHandler.isPossible(player, 1));
	}
	
	@Test
	public void isPossibleFalse2Test() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 0, 0, 0);
		Player player = new Player("Leon", startingResources);
		List<Player> players = new ArrayList<>();
		players.add(player);
		LeaderCardHandler leaderCardHandler = new LeaderCardHandler(players);
		Requirement requirement = new PointsOrResourcesRequirement(ResourcesOrPoints.newPoints(0, 12, 0, 0));
		Effect immediateEffect = null;
		Effect permanentEffect = null;
		LeaderCard leaderCard = new LeaderCardImplementation("Giovanni dalle Bande Nere", requirement, immediateEffect, permanentEffect);
		player.getWarehouse().add(ResourcesOrPoints.newPoints(0, 10, 0, 0));
		player.getPersonalBoard().addLeaderCard(leaderCard);
		assertFalse(leaderCardHandler.isPossible(player, 0));
	}
	
	@Test
	public void isPossibleTrue1Test() {
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 0, 0, 0);
		Player player = new Player("Leon", startingResources);
		List<Player> players = new ArrayList<>();
		players.add(player);
		LeaderCardHandler leaderCardHandler = new LeaderCardHandler(players);
		Requirement requirement = new PointsOrResourcesRequirement(ResourcesOrPoints.newPoints(0, 12, 0, 0));
		Effect immediateEffect = null;
		Effect permanentEffect = null;
		LeaderCard leaderCard = new LeaderCardImplementation("Giovanni dalle Bande Nere", requirement, immediateEffect, permanentEffect);
		player.getWarehouse().add(ResourcesOrPoints.newPoints(0, 12, 0, 0));
		player.getPersonalBoard().addLeaderCard(leaderCard);
		assertTrue(leaderCardHandler.isPossible(player, 0));
	}
	
	@Test
	public void performTrueTest(){
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 0, 0, 0);
		Player player = new Player("Leon", startingResources);
		List<Player> players = new ArrayList<>();
		players.add(player);
		LeaderCardHandler leaderCardHandler = new LeaderCardHandler(players);
		Requirement requirement = new PointsOrResourcesRequirement(ResourcesOrPoints.newPoints(0, 12, 0, 0));
		Effect immediateEffect = new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newPoints(5, 0, 0, 0));
		Effect permanentEffect = new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResources(5, 0, 0, 0));
		LeaderCard leaderCard = new LeaderCardImplementation("Giovanni dalle Bande Nere", requirement, immediateEffect, permanentEffect);
		player.setTemporaryWarehouse();
		player.getPersonalBoard().addLeaderCard(leaderCard);
		player.getWarehouse().add(ResourcesOrPoints.newPoints(0, 12, 0, 0));
		leaderCardHandler.perform(player, 0);
		assertEquals(5, player.getWarehouse().getCoins());
	}
	
	@Test(expected= IllegalActionException.class)
	public void performFalseTest(){
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 0, 0, 0);
		Player player = new Player("Leon", startingResources);
		List<Player> players = new ArrayList<>();
		players.add(player);
		LeaderCardHandler leaderCardHandler = new LeaderCardHandler(players);
		Requirement requirement = new PointsOrResourcesRequirement(ResourcesOrPoints.newPoints(0, 12, 0, 0));
		Effect immediateEffect = new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newPoints(5, 0, 0, 0));
		Effect permanentEffect = new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResources(5, 0, 0, 0));
		LeaderCard leaderCard = new LeaderCardImplementation("Giovanni dalle Bande Nere", requirement, immediateEffect, permanentEffect);
		player.setTemporaryWarehouse();
		player.getPersonalBoard().addLeaderCard(leaderCard);
		player.getWarehouse().add(ResourcesOrPoints.newPoints(0, 12, 0, 0));
		leaderCardHandler.perform(player, 1);
	}
	
	@Test(expected = IllegalActionException.class)
	public void performFalse1Test(){
		ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(0, 0, 0, 0);
		Player player = new Player("Leon", startingResources);
		List<Player> players = new ArrayList<>();
		players.add(player);
		LeaderCardHandler leaderCardHandler = new LeaderCardHandler(players);
		Requirement requirement = new PointsOrResourcesRequirement(ResourcesOrPoints.newPoints(0, 12, 0, 0));
		Effect immediateEffect = new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newPoints(5, 0, 0, 0));
		Effect permanentEffect = new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResources(5, 0, 0, 0));
		LeaderCard leaderCard = new LeaderCardImplementation("Giovanni dalle Bande Nere", requirement, immediateEffect, permanentEffect);
		player.setTemporaryWarehouse();
		player.getPersonalBoard().addLeaderCard(leaderCard);
		player.getWarehouse().add(ResourcesOrPoints.newPoints(0, 10, 0, 0));
		leaderCardHandler.perform(player, 0);
	}
	
}
