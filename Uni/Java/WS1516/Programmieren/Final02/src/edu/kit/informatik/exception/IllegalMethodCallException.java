package edu.kit.informatik.exception;

/**
 * The Class IllegalMethodCallException.
 *
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class IllegalMethodCallException extends Throwable {
    /**
     * Instantiates a new illegal method call exception.
     *
     * @param message the message
     */
    public IllegalMethodCallException(final String message) {
        super(message);
    }
}
