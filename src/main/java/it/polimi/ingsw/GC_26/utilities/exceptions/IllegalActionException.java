package it.polimi.ingsw.GC_26.utilities.exceptions;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the exception to throw when an illegal action has occurred
 *
 */
public class IllegalActionException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	/**
     * Constructor: it creates a new IllegalActionException with no message.
     */
    public IllegalActionException() {

        super();

    }

    /**
     * Constructor: it creates a new IllegalActionException with a message which explains why the exception has been thrown.
     *
     * @param s the detailed message which explains why the exception has been thrown
     */
    public IllegalActionException(String s) {

        super(s);

    }
	
}
