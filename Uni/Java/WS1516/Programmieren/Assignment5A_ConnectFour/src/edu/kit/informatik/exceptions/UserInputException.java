package edu.kit.informatik.exceptions;

/**
 * The Class UserInputException.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class UserInputException extends Exception {

    /**
     * Instantiates a new user input exception.
     *
     * @param string
     *            the string
     */
    public UserInputException(final String string) {
        super(string);
    }

}
