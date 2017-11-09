package edu.kit.informatik;

/**
 * The Class IllegalNumberException.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class IllegalNumberException extends Exception {

    /**
     * Instantiates a new illegal number exception.
     */
    public IllegalNumberException() {
        super();
    }

    /**
     * Instantiates a new illegal number exception.
     *
     * @param message
     *            the message
     */
    public IllegalNumberException(final String message) {
        super(message);
    }
}
