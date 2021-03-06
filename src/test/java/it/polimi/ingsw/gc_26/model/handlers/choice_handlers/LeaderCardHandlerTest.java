package it.polimi.ingsw.gc_26.model.handlers.choice_handlers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.gc_26.model.game.game_components.cards.effects.Effect;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.leaderCard.LeaderCard;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.leaderCard.LeaderCardImplementation;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.leaderCard.PointsOrResourcesRequirement;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.leaderCard.Requirement;
import it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.handlers.choice_handlers.LeaderCardHandler;
import it.polimi.ingsw.gc_26.model.player.Player;
import it.polimi.ingsw.gc_26.utilities.exceptions.IllegalActionException;

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
		assertTrue(leaderCardHandler.isPossible(player, 1));
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
		assertFalse(leaderCardHandler.isPossible(player, 2));
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
		assertFalse(leaderCardHandler.isPossible(player, 1));
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
		assertTrue(leaderCardHandler.isPossible(player, 1));
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
		leaderCardHandler.perform(player, 1);
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
		leaderCardHandler.perform(player, 2);
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
		leaderCardHandler.perform(player, 1);
	}
	
}
