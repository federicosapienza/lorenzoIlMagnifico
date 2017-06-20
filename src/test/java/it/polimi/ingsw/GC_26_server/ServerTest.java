package it.polimi.ingsw.GC_26_server;

import org.junit.Test;

public class ServerTest {
	
	Server server = new Server();
	
	@Test(expected = NullPointerException.class)
	public void testAddClientException() {
		server.addClient(null, null);
	}
	
	
}
