package it.polimi.ingsw.GC_26_server;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the interface for the observer
 */
public interface Observer <C> {
	public void update(C change);


}
