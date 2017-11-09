package edu.kit.informatik.exceptions;

/**
 * The Class GameHasEndedException.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class GameHasEndedException extends Exception {

    /**
     * Instantiates a new game has ended exception.
     *
     * @param message
     *            the message
     */
    public GameHasEndedException(final String message) {
        super(message);
    }
}
