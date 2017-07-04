package it.polimi.ingsw.GC_26.server;

import org.junit.Test;

import it.polimi.ingsw.GC_26.server.Server;

public class ServerTest {
	
	Server server = new Server();
	
	@Test(expected = NullPointerException.class)
	public void testAddClientException() {
		server.addClient(null, null);
	}
	
	
}
