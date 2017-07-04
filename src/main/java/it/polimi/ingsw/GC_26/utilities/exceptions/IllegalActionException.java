package it.polimi.ingsw.GC_26.utilities.exceptions;

public class IllegalActionException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new IllegalActionException with no  message.
     */
    public IllegalActionException() {

        super();

    }

    /**
     * Constructs a new IllegalActionException with the specified detail
     * message.
     *
     * @param s the detail message
     */
    public IllegalActionException(String s) {

        super(s);

    }
	
}
