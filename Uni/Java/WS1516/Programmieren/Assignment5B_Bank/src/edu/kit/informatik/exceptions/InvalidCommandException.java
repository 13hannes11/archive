package edu.kit.informatik.exceptions;

/**
 * The Class InvalidCommandException.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class InvalidCommandException extends Exception {

    /**
     * Instantiates a new invalid command exception.
     *
     * @param message
     *            the message
     */
    public InvalidCommandException(final String message) {
        super(message);
    }
}
