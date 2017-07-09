package it.polimi.ingsw.gc_26.server;

import org.junit.Test;

import it.polimi.ingsw.gc_26.server.main.Server;

public class ServerTest {
	
	Server server = new Server();
	
	@Test(expected = NullPointerException.class)
	public void testAddClientException() {
		server.addClient(null, null);
	}
	
	
}
