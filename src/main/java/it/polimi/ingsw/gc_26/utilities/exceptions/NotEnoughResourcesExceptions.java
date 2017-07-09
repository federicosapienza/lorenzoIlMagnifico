package it.polimi.ingsw.gc_26.utilities.exceptions;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the exception to throw when a player doesn't have enough resources 
 *
 */
public class NotEnoughResourcesExceptions extends RuntimeException{

	private static final long serialVersionUID = 1L;

	/**
     * Constructor: it creates a new NotEnoughResourcesExceptions with no message.
     */
    public NotEnoughResourcesExceptions() {

        super();

    }

    /**
     * Constructor: it creates a new NotEnoughResourcesExceptions with a message which explains why 
     * the exception has been thrown.
     *
     * @param s the message which explains why the exception has been thrown
     */
    public NotEnoughResourcesExceptions(String s) {

        super(s);

    }
}
