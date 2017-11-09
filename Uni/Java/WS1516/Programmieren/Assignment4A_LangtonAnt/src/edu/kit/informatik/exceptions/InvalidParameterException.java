package edu.kit.informatik.exceptions;

/**
 * The Class InvalidParameterException. (Custom class because imports from
 * java.security are not allowed)
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class InvalidParameterException extends Exception {

    /**
     * Instantiates a new invalid parameter exception.
     *
     * @param message
     *            the message
     */
    public InvalidParameterException(String message) {
        super(message);
    }
}
