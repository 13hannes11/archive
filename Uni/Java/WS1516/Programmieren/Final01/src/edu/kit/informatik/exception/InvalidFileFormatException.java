package edu.kit.informatik.exception;

/**
 * The Class InvalidFileFormatException. It Indicates that the file format is
 * not supported.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class InvalidFileFormatException extends Exception {

    /**
     * File format exception.
     */
    public InvalidFileFormatException() {
        super();
    }

    /**
     * Instantiates a new invalid file format exception.
     *
     * @param message
     *            the message
     */
    public InvalidFileFormatException(final String message) {
        super(message);
    }
}
