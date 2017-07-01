package it.polimi.ingsw.GC_26_utilities_exceptions;

public class NotEnoughResourcesExceptions extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new NotEnoughResourcesExceptions with no  message.
     */
    public NotEnoughResourcesExceptions() {

        super();

    }

    /**
     * Constructs a new NotEnoughResourcesExceptions with the specified detail
     * message.
     *
     * @param s the detail message
     */
    public NotEnoughResourcesExceptions(String s) {

        super(s);

    }
}
