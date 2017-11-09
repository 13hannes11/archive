package edu.kit.informatik.exceptions;

/**
 * The Class IllegalMethodCallException.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class IllegalMethodCallException extends RuntimeException {

    /**
     * Instantiates a new illegal method call exception.
     *
     * @param string
     *            the string
     */
    public IllegalMethodCallException(final String string) {
        super(string);
    }

}
