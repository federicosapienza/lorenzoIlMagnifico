package it.polimi.ingsw.GC_26_server;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26_serverView.ClientMainServerView;

public class ServerTest {
	
	Server server = new Server();
	Map<String, String> listOfPlayers = new HashMap<>();
	@Test
	public void loginTest() {
		listOfPlayers.put("tizio", "caio");
		assertTrue(server.doLogin("tizio", "caio"));
		assertFalse(server.doLogin("tizio", "caiu"));
		assertTrue(server.doLogin("Bugs", "Bunny"));
	}
	
	@Test
	public void testNotNullUsername() {
		boolean thrownNullExcep = false;
		String username = null;
		String password = null;
		try {
			server.doLogin(username, password);
		} catch (NullPointerException e) {
			thrownNullExcep = true;
		}
		assertTrue(thrownNullExcep);
	}
	
	@Test
	public void testAddNotNullClient() {
		boolean thrownNullExcep = false;
		String username = "flash";
		ServerConnectionToClient nullConn = null;
		try {
			server.addClient(nullConn, username);
		} catch (NullPointerException e) {
			thrownNullExcep = true;
		}
		assertTrue(thrownNullExcep);
		
		thrownNullExcep = false;
		String nullUser = null;
		try {
			server.addClient(nullConn, nullUser);
		} catch (NullPointerException e) {
			thrownNullExcep = true;
		}
		assertTrue(thrownNullExcep);
	}

	
	@Test
	public void testEndNotNullGame() {
		boolean thrownNullExcep = false;
		GameInitialiserAndController game = null;
		try {
			server.endGame(game);
		} catch (NullPointerException e) {
			thrownNullExcep = true;
		}
		assertTrue(thrownNullExcep);
	}
}
