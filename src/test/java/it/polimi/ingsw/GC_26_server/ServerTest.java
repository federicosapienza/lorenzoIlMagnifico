package it.polimi.ingsw.GC_26_server;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ServerTest {
	
	Server server = new Server();
	Map<String, String> listOfPlayers = new HashMap<>();
	@Test
	public void loginTest() {
		listOfPlayers.put("tizio", "caio");
		server.start();
		assertTrue(server.doLogin("tizio", "caio"));
		assertFalse(server.doLogin("tizio", "caiu"));
		assertTrue(server.doLogin("Bugs", "Bunny"));
	}

}
