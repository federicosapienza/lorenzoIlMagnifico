package it.polimi.ingsw.GC_26.client.view;

import static org.junit.Assert.*;



import org.junit.Test;

import it.polimi.ingsw.GC_26.client.view.MainClientView;
import it.polimi.ingsw.GC_26.client.view.PlayerView;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.PlayerWallet;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;

public class MainClientViewTest {

	
	@Test
	public void testCorrectUpdateForEmptyPlayersMap() {
		Player player1 = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		PlayerWallet wallet1 = new PlayerWallet(player1.getWarehouse());
		PlayerView playerview1 = new PlayerView(wallet1);
		MainClientView mainClientView = new MainClientView();
		mainClientView.setPlayerUsername("David");
		mainClientView.updatePlayerWallet(wallet1);
		assertTrue(mainClientView.getThisPlayer().getName() == playerview1.getName() && 
				mainClientView.getThisPlayer().getFamilyMembers() == playerview1.getFamilyMembers() &&
				mainClientView.getPlayer(player1.getName()).getWallet().getCoins() == 5 &&
				mainClientView.getPlayer(player1.getName()).getWallet().getServants() == 3 &&
				mainClientView.getPlayerUsername() == "David");
	}

	@Test
	public void testCorrectUpdateForFullPlayersMap() {
		Player player1 = new Player("David", ResourcesOrPoints.newResources(5, 3, 2, 2));
		PlayerWallet wallet1 = new PlayerWallet(player1.getWarehouse());
		MainClientView mainClientView = new MainClientView();
		mainClientView.setPlayerUsername("David");
		mainClientView.updatePlayerWallet(wallet1);
		ResourcesOrPoints newResOrPts = ResourcesOrPoints.newResourcesOrPoints(1, 2, 3, 4, 1, 2, 3, 4);
		player1.getWarehouse().add(newResOrPts);
		PlayerWallet wallet2 = new PlayerWallet(player1.getWarehouse());
		PlayerView playerView2 = new PlayerView(wallet2);
		mainClientView.updatePlayerWallet(wallet2);
		assertTrue(mainClientView.getThisPlayer().getName() == playerView2.getName() && 
				mainClientView.getThisPlayer().getFamilyMembers() == playerView2.getFamilyMembers() && 
				mainClientView.getPlayer(player1.getName()).getWallet().getCoins() == 6 && 
				mainClientView.getPlayer(player1.getName()).getWallet().getServants() == 5 && !mainClientView.isLoginDone());
	}
}
